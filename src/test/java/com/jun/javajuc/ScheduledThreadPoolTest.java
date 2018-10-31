package com.jun.javajuc;

import java.util.concurrent.*;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService pool=Executors.newScheduledThreadPool(5);
        for(int i=0;i<10;i++) {
            Future<Integer> future =pool.schedule(() -> {
                int sum=0;
                for (int j = 0; j < 10; j++) {
                    sum+=j;
                }
                return sum;
            }, 1, TimeUnit.SECONDS);
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }
}
