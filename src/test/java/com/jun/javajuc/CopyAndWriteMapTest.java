package com.jun.javajuc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyAndWriteMapTest {

    public static void main(String[] args) {
        CopyThread thread = new CopyThread();
        for (int i = 0; i < 10; i++) {
            new Thread(thread).start();
        }
    }
}

class CopyThread implements Runnable {

    //    private static List<String> list= Collections.synchronizedList(new ArrayList<>());
    private static List<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("bb");
        list.add("cc");
        list.add("dd");
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("eee");
        }
    }
}
