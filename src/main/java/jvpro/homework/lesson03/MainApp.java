package jvpro.homework.lesson03;

import java.util.concurrent.*;

public class MainApp {

    //Попробуйте реализовать собственный пул потоков.
    // В качестве аргументов конструктора пулу передается его емкость (количество рабочих потоков).
    // Как только пул создан, он сразу инициализирует и запускает потоки.
    // Внутри пула очередь задач на исполнение организуется через LinkedList<Runnable>.
    // При выполнении у пула потоков метода execute(Runnable r), указанная задача должна попасть в очередь исполнения, и как только появится свободный поток – должна быть выполнена.
    // Также необходимо реализовать метод shutdown(), после выполнения которого новые задачи больше не принимаются пулом (при попытке добавить задачу можно бросать IllegalStateException),
    //         и все потоки для которых больше нет задач завершают свою работу.
    //Дополнительно можно добавить метод awaitTermination() без таймаута, работающий аналогично стандартным пулам потоков

    public static void main(String[] args) {

        ThreadPool newPool = new ThreadPool(2);
        for (int i = 0; i < 10; i++) {
            int count = i;
            newPool.execute(() -> {
                System.out.println("Task step1 - " + count);
                System.out.println("Task step2 - " + count);
                System.out.println("Task step3 - " + count);
                System.out.println("End of task - " + count);
            });
        }
        newPool.shutdown();
    }

}
