package by.matskevich.wtimer.thread;


import by.matskevich.wtimer.domain.Timer;

import java.util.concurrent.locks.ReentrantLock;

public class TickThread extends Thread {

    private Timer timer;
    private boolean isActive = true;
    ReentrantLock locker;

    public TickThread(Timer timer, ReentrantLock locker) {
        this.timer = timer;
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            while (isActive) {
                Thread.sleep(100);
                timer.tick();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void cancel() {
        isActive = false;
    }
}
