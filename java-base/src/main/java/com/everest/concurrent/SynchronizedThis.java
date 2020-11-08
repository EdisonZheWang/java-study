package com.everest.concurrent;

/**
 * @Date: 12/31/19
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class SynchronizedThis {

    private Integer workerSeq = 100;

    public static void main(String[] args) {

        new SynchronizedThis().run();

    }

    private void run() {
        Worker worker = new Worker();
        new Thread(worker).start();
        new Thread(worker).start();
    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                while (workerSeq > 0) {
                    System.out.println(
                            Thread.currentThread().getName().concat("-").concat(String.valueOf(workerSeq)));
                    workerSeq--;
                }
            }
        }
    }
}
