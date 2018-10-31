package com.jun.javajuc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for(int j=0;j<10;j++) {
//            pool.submit(() -> {
//                int sum = 0;
//                for (int i = 1; i <= 100; i++) {
//                    sum += i;
//                }
//                System.out.println(Thread.currentThread().getName() + ":" + sum);
//            });
            List<Future<Integer>> list=new ArrayList<>();
            Future<Integer> task=pool.submit(()->{
                int sum=0;
                for(int i=1;i<=100;i++){
                    sum+=i;
                }
                return sum;
            });
            list.add(task);
            list.forEach((item)-> {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" +item.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });

        }

        pool.shutdown();
    }
}

