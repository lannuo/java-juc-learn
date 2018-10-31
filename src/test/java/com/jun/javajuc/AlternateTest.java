package com.jun.javajuc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 需求 三个线程 id分别为A、B、C。输出十次 ABC
 *
 * 通过变量 i和 condition实现线程通信
 */
public class AlternateTest {
    public static void main(String[] args) {
        AlternateDemo demo=new AlternateDemo();
        new Thread(()->{
            for(int i=1;i<=10;i++){
                demo.loopA(i);
            }
        },"A").start();
        new Thread(()->{
            for(int i=1;i<=10;i++){
                demo.loopB(i);
            }
        },"B").start();
        new Thread(()->{
            for(int i=1;i<=10;i++){
                demo.loopC(i);
            }
        },"C").start();
    }
}

class AlternateDemo{
    private int i=1;
    private Lock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();

    public void loopA(int total){
        lock.lock();
        try {
            while (i!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t 第"+total);
            i=2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void loopB(int total){
        lock.lock();
        try {
            while (i!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t 第"+total);
            i=3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void loopC(int total){
        lock.lock();
        try {
            while (i!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t 第"+total);
            i=1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
