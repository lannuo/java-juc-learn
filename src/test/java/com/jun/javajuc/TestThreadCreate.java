package com.jun.javajuc;

public class TestThreadCreate {

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            System.out.println("main:"+Thread.currentThread().getName()+";i:"+i);
            if(i==30){
                MyThread thread1=new MyThread();
                thread1.start();
                MyThread thread2=new MyThread();
                thread2.start();
            }
        }
    }
}
class MyThread extends Thread{
    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     */
    @Override
    public void run() {
      for(int i=0;i<100;i++){
          System.out.println("MyThread:"+Thread.currentThread().getName()+"i:"+i);
      }
    }
}
