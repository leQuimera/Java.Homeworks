package jvpro.homework.lesson03;

import java.util.LinkedList;


public class ThreadPool {

    private boolean isStopped = false;   //Чтоб выйти
    private int capacity;
    private LinkedList<Runnable> tasksPool;
    private NewPoolThread[] newPoolThreads;  //пучок потоков, запускаемых в конструкторе

    public ThreadPool(int capacity) {
        this.capacity = capacity;
        this.tasksPool = new LinkedList<>();
        newPoolThreads = new NewPoolThread[capacity];

        //Создаем пучок
        for (int i = 0; i < capacity; i++) {
            int finalI = i;
            newPoolThreads[i] = new NewPoolThread();
            newPoolThreads[i].start();
        }
    }

    public synchronized void execute(Runnable r) {
        if (isStopped) {
            throw new IllegalStateException("Потоки уже остановлены");
        }
        tasksPool.addLast(r);
        //System.out.println("Выполняем");
        notify();
    }

    public synchronized void shutdown() {
        isStopped = true;
        for (NewPoolThread newPoolThread : newPoolThreads) {
            newPoolThread.interrupt();
        }
    }


    private class NewPoolThread extends Thread {
        //Тут обработчик
        @Override
        public void run() {
            System.out.println("Выполняем");

        }
    }


}
