package org.GROUPNAME.io;

import org.GROUPNAME.model.Person;

import java.util.ArrayList;

public class PrettyPrinter {

    public static void printPeople(ArrayList<Person> persone) {
        for (Person p : persone) {
            System.out.println();
            System.out.println(p);
            System.out.println();
        }
    }

}
