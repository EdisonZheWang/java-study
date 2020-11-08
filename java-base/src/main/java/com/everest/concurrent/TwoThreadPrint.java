package com.everest.concurrent;

public class TwoThreadPrint {
    static int test = 0;

    public static void main(String[] args) {
        Object obj = new Object();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (test < 10) {
                        synchronized (obj) {
                            System.out.println(Thread.currentThread().getId() + "--" + test++);
                            obj.notify();
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }
}
