package com.jun.javajuc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] args) {
        Clerk1 clerk=new Clerk1();
        Producter1 producter1=new Producter1(clerk);
        Consumer1 consumer1=new Consumer1(clerk);

        new Thread(producter1,"生产者A").start();
        new Thread(consumer1,"消费者B").start();
        new Thread(producter1,"生产者C").start();
        new Thread(consumer1,"消费者D").start();

    }
}

class Clerk1{
    private int product=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void get(){
        lock.lock();
        try {
            while (product >= 1) {
                System.out.println(Thread.currentThread().getName() + ":" +"进货已满");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void sale(){
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println(Thread.currentThread().getName() + ":" +"销售完毕");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}

/**
 * 生产者
 */
class Producter1 implements Runnable{
    private Clerk1 clerk;

    public Producter1(Clerk1 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

class Consumer1 implements Runnable{
    private Clerk1 clerk;

    public Consumer1(Clerk1 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            clerk.sale();
        }
    }
}