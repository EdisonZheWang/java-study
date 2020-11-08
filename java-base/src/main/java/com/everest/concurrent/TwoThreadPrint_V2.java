package com.everest.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TwoThreadPrint_V2 {
    static int test = 0;
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition  = lock.newCondition();
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (test < 10) {
                        try{
                            lock.lock();
                            System.out.println(Thread.currentThread().getId() + "--" + test++);
                            condition.signal();
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }

                    }
                }
            }).start();
        }

    }

}
