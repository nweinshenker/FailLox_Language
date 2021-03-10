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
    public void testAddComments() {
        String line = "/*How's it going sire*/";
        List<Token> list;
        
        Scanner scan = new Scanner(line);
        list = scan.scanTokens();

        assertEquals("How's it going sire", list.get(0).lexeme);
    }

}
