package com.jun.javajuc;

import java.util.concurrent.atomic.AtomicInteger;

public class TestCalculate {
    public static void main(String[] args) {
        MyCalculate thread=new MyCalculate();
        for(int i=0;i<20;i++) {
            new Thread(thread).start();
//            System.out.println(thread.getNum());
//            System.out.println(thread.getJ());
        }
    }

}

class MyCalculate implements Runnable{
    private volatile int i=0;
    private AtomicInteger j=new AtomicInteger();

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
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getJ());

    }
    public int getNum(){
        return i++;
    }
    public int getJ(){
        return j.getAndIncrement();
    }
}