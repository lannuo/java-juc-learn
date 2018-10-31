package com.jun.javajuc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * 写写/读写 需要互赤
 * 读读 不需要互赤
 */
public class ReadAndWriteLockTest {

    public static void main(String[] args) {
        ReadAndWriteDemo demo=new ReadAndWriteDemo();
        new Thread(()->{
            for(int i=0;i<10;i++){
                demo.get();
            }
        },"read").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                demo.set((int)(Math.random()*101));
            }
        },"write").start();
    }
}
class ReadAndWriteDemo{
    private int i;
    private ReadWriteLock lock=new ReentrantReadWriteLock();

    public int get(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+":"+i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return 0;
    }

    public void set(int num){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+":"+i);
            this.i=num;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}