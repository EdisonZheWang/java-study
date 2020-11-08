package com.everest.concurrent;

import java.util.concurrent.Semaphore;

public class MutilThreadPrint {

    static int index = 1;
    static int num = 20;

    public static void main(String[] args) throws InterruptedException {

        int threadNum = 3;

        Semaphore[] semaphores = new Semaphore[threadNum];
        Thread[] threads = new Thread[threadNum];
        for (int i = semaphores.length - 1; i >= 0; i--) {
            semaphores[i] = new Semaphore(1);
            if (i != semaphores.length - 1) {
                semaphores[i].acquire();
            }
        }

        for (int i = 0; i < threadNum; i++) {
            Semaphore lastSemaphore = i == 0 ? semaphores[threadNum - 1] : semaphores[i - 1];
            Semaphore curSemaphore = semaphores[i];
            threads[i] = new Thread(() -> {
                while (true) {
                    try {
                        lastSemaphore.acquire();
                        if (index > num) {
                            System.exit(0);
                        }
                        System.out.println(Thread.currentThread().getName() + "==" + index++);
                        curSemaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
    }
}
