package com.jun.javajuc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步锁（三种方式）
 *
 * 1、synchronized 隐式锁
 *      同步代码块
 *      同步方法
 *
 * 2、Lock 显示锁
 *
 */
public class LockTest {

    public static void main(String[] args) {
        LockThread thread = new LockThread();
        new Thread(thread, "窗口1").start();
        new Thread(thread, "窗口2").start();
        new Thread(thread, "窗口3").start();
    }
}

class LockThread implements Runnable {
    private Integer num = 100;
    private Lock lock=new ReentrantLock();
    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            if (num > 0) {
                num--;
                System.out.println(Thread.currentThread().getName() + "成功卖出票，剩余" + num);
            }
            lock.unlock();
        }
    }
}
