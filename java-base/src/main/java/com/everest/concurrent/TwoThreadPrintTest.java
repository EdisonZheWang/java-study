package com.everest.concurrent;

public class TwoThreadPrintTest implements Runnable {

    static int i;
    static int num = 20;
    static Object lock = new Object();

    public static void main(String[] args) {
        TwoThreadPrintTest bt1 = new TwoThreadPrintTest();
        TwoThreadPrintTest bt2 = new TwoThreadPrintTest();
        new Thread(bt1).start();
        new Thread(bt2).start();
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (i <= num) {
                System.out.println(Thread.currentThread().getName() + "===" + i++);
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.exit(0);
    }

}
