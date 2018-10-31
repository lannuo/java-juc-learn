package com.jun.javajuc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable  实现第三种声明线程的方式，有返回只，并且可以抛出异常
 */
public class CallableTest {

    public static void main(String[] args) {
        CallAbleThread thread=new CallAbleThread();
        FutureTask<Integer> task=new FutureTask<>(thread);

        new Thread(task).start();

        //获取结果
        try {
            System.out.println(task.get());
            System.out.println("-----------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class CallAbleThread implements Callable<Integer>{

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        Integer sum=0;
        for(int i=0;i<1000000000;i++){
            sum+=i;
        }
        return sum;
    }
}