package com.jun.javajuc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 *  CountDown 闭锁，作用  等待其它线程完成以后的操作，和前台 Asyns await相似
 */
public class CountDownTest {

    public static void main(String[] args) {
        CountDownLatch latch=new CountDownLatch(5);
        CountDownCalculate demo=new CountDownCalculate(latch);
        Instant start= Instant.now();
        for(int i=0;i<5;i++){
            new Thread(demo).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end=Instant.now();

        System.out.println("时间为："+Duration.between(start,end).toMillis());

    }
}


class CountDownCalculate implements Runnable{
    private CountDownLatch latch;

    public CountDownCalculate(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        for(int i=0;i<5000;i++){
            if(i%2==0){
                System.out.println(i);
            }
        }
        latch.countDown();
    }
}