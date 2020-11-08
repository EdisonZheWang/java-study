package com.everest.concurrent;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

public class MutilThreadPrintV2 {

    public static void main(String[] args) {
        int num = 20;
        int threadNum = 3;
        ArrayBlockingQueue<Integer> integers = new ArrayBlockingQueue<>(num, Boolean.TRUE);
        for (int i = 1; i <= num; i++) {
            try {
                integers.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Printer(integers)).start();
        }
    }

}


class Printer implements Runnable {

    private ArrayBlockingQueue<Integer> queue;

    public Printer() {
    }

    public Printer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!queue.isEmpty()) {
            final Integer poll = queue.poll();
            if (Objects.isNull(poll)) {
                System.exit(0);
            }
            System.out.println(Thread.currentThread().getName().concat("==>").concat(poll.toString()));
        }
    }
}
