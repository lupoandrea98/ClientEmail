package org.GROUPNAME.model;

import java.util.ArrayList;

public class Person {
    private String name;
    private int age;
    private ArrayList<Item> own_items;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.own_items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addItem(Item obj) {
        this.own_items.add(obj);
    }

    public void removeItem(Item obj) {
        this.own_items.remove(obj);
    }

    public int totalValue() {
        int sum = 0;
        for (Item I : this.own_items) {
            sum += I.getValue();
        }
        return sum;
    }

    @Override
    public String toString() {
        return  "NOME" + name + '\'' +
                ", ETÀ" + age +
                ", TOTAL VALUE" + this.totalValue() +
                '}';
    }
}
