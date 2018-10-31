package com.jun.javajuc;

/**
 * 生产者消费者模式
 * 等待唤醒机制
 */
public class ProducterAndConsumerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producter producter = new Producter(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(producter, "生产者A").start();
        new Thread(consumer, "消费者B").start();
        new Thread(producter, "生产者C").start();
        new Thread(consumer, "消费者D").start();
    }
}

/**
 * 店员
 */
class Clerk {
    private int product = 0;

    /**
     * 进货
     */
    public synchronized void get() {
        while (product >= 1) {
            System.out.println("进货已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + ++product);
        this.notifyAll();

    }

    public synchronized void sale() {
        while (product <= 0) {
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + --product);
        this.notifyAll();

    }
}

/**
 * 生产者
 */
class Producter implements Runnable {
    private Clerk clerk;

    public Producter(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
