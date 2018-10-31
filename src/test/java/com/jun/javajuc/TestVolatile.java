package com.jun.javajuc;

public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo demo=new ThreadDemo();
        new Thread(demo).start();

//        while (true){
//            synchronized (demo) {
//                if (demo.isFlag()) {
//                    System.out.println("线程改变了执行");
//                    break;
//                }
//            }
//        }
        while (true){

            if (demo.isFlag()) {
                System.out.println("线程改变了执行");
                break;
            }

        }
    }



}
class ThreadDemo implements Runnable {

    private volatile boolean flag=false;
    private int i=0;
    private int j=0;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

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
    public void run(){
        i++;
        ++j;
        System.out.println("i"+i+"j"+j);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setFlag(true);
        System.out.println("flag 改变为true");
    }
}
