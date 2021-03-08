package com.interpreter.lox;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ScannerTest {
    @Test
    public void test1() {
        String test = "Name";
        assertEquals(false,false);
        assertEquals(test, "Name");
    }

    List<Integer> list = new ArrayList<Integer>();
    List<Integer> list2 = new ArrayList<Integer>();
    @Before
    public void initalize() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (int i = 0; i < 10; i++) {
            list2.add(i);
        }
    }

    @Test
    public void test_list() {
        for (int i = 0; i < 10; i++) {
            assertEquals(list.get(i), list2.get(i));
        }
    }
}
