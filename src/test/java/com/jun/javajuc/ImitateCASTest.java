package com.jun.javajuc;

public class ImitateCASTest {

    public static void main(String[] args) {
        final CASCalculate casCalculate=new CASCalculate();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int excepNum=casCalculate.getNum();
                    boolean b=casCalculate.compareAndSet(excepNum,(int)(Math.random()*101));
                    System.out.println(b);
                }
            }).start();
        }
    }
}

class CASCalculate{
    private int num;


    public synchronized int getNum(){
        return this.num;
    }

    public synchronized int compareAndSwap(int excepNum,int newNum){
        int oldNum=this.num;
        if(oldNum==excepNum){
            this.num=newNum;
        }
        return oldNum;
    }

    public synchronized boolean compareAndSet(int excepNum,int newNum){
        return excepNum==compareAndSwap(excepNum,newNum);
    }
}
