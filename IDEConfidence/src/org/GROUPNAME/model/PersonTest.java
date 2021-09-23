package org.GROUPNAME.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @org.junit.jupiter.api.Test
    void totalValue() {
        Person fabbbio = new Person("Fabio", 23);
        Item spada = new Item("Spada", 10);
        Item scu = new Item("Scudo", 15);
        fabbbio.addItem(spada);
        fabbbio.addItem(scu);

        assertEquals(fabbbio.totalValue(), 24);
    }
}