package app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The Server controller.
 */
public class ServerController implements Initializable {


    public static ArrayList<PairSocketUser> clients;
    private HashMap<Integer, List<Email>> mailLists;
    public User myUser = null;
    @FXML
    public ListView listLog;
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * Start the socket for the server and starts accepting all the clients.
     * Create a new thread for each app.client.
     */
    public void socketThreadStart() {
        try {
            ServerSocket s = new ServerSocket(4445);
            clients = new ArrayList<>();
            System.out.println("Creating socket on " + s.getLocalSocketAddress());
            int i = 1;
            while (true) {

                int finalI = i;
                Socket incoming = s.accept(); // it waits for connection request and opens it
                new Thread() {
                    @Override
                    public void run() {

                        Platform.runLater(() -> {
                            try {
                                clients.add(new PairSocketUser(incoming, null));
                                ThreadHandler(incoming, finalI);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }.start();

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> socketThreadStart()).start();
    }

    /**
     * How the server handles app.client requests.
     * ReentrantReadWriteLock are for mutex. You can't do multiple operations on the same file at the same time.
     * Switch case changes to the number request from the app.client in the pair:
     * <1- Login request> <2 - Request mail list> <3 - Request to send a new mail> <4 - Request to delete a mail>
     *
     * @param incoming the incoming Socket
     * @param index    the number of the socket
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void ThreadHandler(Socket incoming, int index) throws IOException, ClassNotFoundException {

        ObjectInputStream inStream = new ObjectInputStream(incoming.getInputStream());
        ObjectOutputStream outStream = new ObjectOutputStream(incoming.getOutputStream());
        outStream.flush();

        if (!incoming.isClosed()) {
            try {
                while (true) {
                    Object obj = inStream.readObject();

                    if (obj instanceof Pair) {
                        Pair p = (Pair) obj;
                        switch ((Integer) p.getObj1()) {
                            case 1:       //Login case --> given mail and pwd by the app.client, check if user exists. if id == 0 user not exists.
                                String[] split = ((String) p.getObj2()).split(",");
                                int id = FileQuery.getUserId(split[0], split[1]);
                                User user = null;
                                if (id != 0) {
                                    user = new User(id, split[0], split[1]);
                                    myUser = user;
                                    ServerController.clients.set(index - 1, new PairSocketUser(ServerController.clients.get(index - 1).socket, user));
                                    printOnLog("Login by " + incoming.getInetAddress());
                                    outStream.writeObject(user);
                                } else {
                                    outStream.writeObject(false);
                                    System.out.println("No user found");
                                }
                                break;
                            case 2:         //Request mails --> Given the user read the mail list from the file and return it to the app.client
                                Pair pReq = (Pair) p.getObj2();
                                myUser = (User) pReq.getObj1();
                                String lastDate = (String) pReq.getObj2();
                                List<Email> newMails = new ArrayList<Email>();
                                if(lastDate.equals("")){
                                    List<Email> listMail = FileQuery.readMailJSON(myUser);
                                    newMails = listMail;
                                }else{
                                    Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(lastDate);
                                    List<Email> listMail = FileQuery.readMailJSON(myUser);
                                    Date date2 = null;
                                    for (Email e: listMail) {
                                       date2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(e.getData());
                                       if(date2.after(date1)){
                                           newMails.add(e);
                                       }
                                    }
                                }

                                outStream.writeObject(newMails);
                                break;

                            case 3:     //Send mail method --> Given the mail to be sent read all the mails from the json, add the one to be sent and write all to the film
                                Email newMail = (Email) p.getObj2();
                                List<String> dests = newMail.getDestinatari();
                                boolean allSent = true;
                                Pair result = null;
                                List<String> notSentDests = new ArrayList<>();
                                List<String> sentDests = new ArrayList<>();
                                List<User> sentUser = new ArrayList<>();
                                List<Email> emails = null;
                                Object resUser =  null;
                                for (String s : dests) {
                                    resUser = FileQuery.getUserByMail(s);
                                    if(resUser instanceof User){
                                        sentUser.add((User) resUser);
                                        sentDests.add(s);
                                    }
                                    else if(resUser instanceof Boolean){ //User not found
                                        allSent = false;
                                        notSentDests.add(s);
                                    }
                                }
                                newMail.setDestinatario(sentDests);
                                for (User u : sentUser){
                                        emails = FileQuery.readMailJSON(u);
                                        int lastID = 0;
                                        if(emails.size() > 0)
                                            lastID = emails.get(emails.size()-1).getId() + 1;
                                        newMail.setId(lastID);
                                        emails.add(newMail);
                                        FileQuery.writeMailListJSON(emails, u);
                                }

                                result = new Pair(allSent, notSentDests);
                                printOnLog(newMail.getMittente() + " sent a new mail.");
                                for (String s: sentDests) {
                                    printOnLog(s + " received a new mail.");
                                }
                                if(!allSent)printOnLog(newMail.destinatariToString() + " not found");
                                outStream.flush();
                                outStream.writeObject(result);
                                break;

                            case 4:                                                 //Delete method --> Given a mail Id it read all the mails, find the one with the given id, delete it from the list and rewrite on the json file
                                int mailId = (int) p.getObj2();
                                Boolean res = false;
                                res = FileQuery.deleteMail(myUser, mailId);
                                printOnLog("Mail deleted by " + incoming.getInetAddress());
                                outStream.flush();
                                outStream.writeObject(res);
                                break;

                            default:
                                outStream.close();
                                inStream.close();
                        }


                    }
                }
            } catch (EOFException | ParseException e) {
            } finally {
                incoming.close();
                outStream.close();
                inStream.close();
            }
        } else System.out.println("Socket not closed");

    }

    /**
     * Print on the log list the message.
     *
     * @param msg the message to be printed.
     */
    private void printOnLog(String msg) {
        listLog.getItems().add(msg);
    }

}