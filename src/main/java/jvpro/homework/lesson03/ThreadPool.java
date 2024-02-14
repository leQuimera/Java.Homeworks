package jvpro.homework.lesson03;

import java.util.LinkedList;


public class ThreadPool {

    private boolean isStopped = false;
    private int capacity;
    private LinkedList<Runnable> tasksPool;
    private NewPoolThread[] newPoolThreads;

    public ThreadPool(int capacity) {
        this.capacity = capacity;
        this.tasksPool = new LinkedList<>();
        newPoolThreads = new NewPoolThread[capacity];

        for (int i = 0; i < capacity; i++) {
            newPoolThreads[i] = new NewPoolThread();
            newPoolThreads[i].start();
        }
    }

    public synchronized void execute(Runnable r) {
        if (isStopped) {
            throw new IllegalStateException("Потоки уже остановлены");
        }
        tasksPool.addLast(r);
        notify();
    }

    public synchronized void shutdown() {
        isStopped = true;
        for (NewPoolThread newPoolThread : newPoolThreads) {
            newPoolThread.interrupt();
        }
    }


    private class NewPoolThread extends Thread {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                synchronized (ThreadPool.this) {
                    while (tasksPool.isEmpty() && !isStopped) {
                        try {
                            ThreadPool.this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    if (tasksPool.isEmpty() && isStopped) {
                        return;
                    }
                    task = tasksPool.removeFirst();
                }
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
