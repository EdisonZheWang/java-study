package com.everest.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {


    private Player[] players;
    private CountDownLatch begin = new CountDownLatch(1);
    private CountDownLatch end;

    public CountDownLatchDemo(int sum) {
        this.end = new CountDownLatch(sum);
        players = new Player[sum];
        for (int i = 0; i < sum; i++) {
            players[i] = new Player(i, this.begin, this.end);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo race = new CountDownLatchDemo(5);
        race.start();
    }

    public void start() throws InterruptedException {
        for (int i = 0; i < players.length; i++) {
            new Thread(players[i]).start();
        }
        begin.countDown();
        end.await();
        System.out.println("比赛结束");
    }

}


class Player implements Runnable {

    private int index;
    private CountDownLatch begin;
    private CountDownLatch end;

    public Player(int index, CountDownLatch begin, CountDownLatch end) {
        this.index = index;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            begin.await();
            System.out.println("Racing......");
            Thread.sleep(new Random().nextInt(10000));
            System.out.println("第" + index + "号选手已经到达");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            end.countDown();
        }
    }
}
