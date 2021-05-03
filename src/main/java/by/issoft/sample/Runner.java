package by.issoft.sample;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Runner {

    static Statistic statistic = new Statistic();

    public static void main(String[] args) throws InterruptedException {
//        http://tutorials.jenkov.com/java-concurrency/index.html

        final Queue<Integer> queue = new LinkedList<>();

        final List<Producer> producers = IntStream.range(0, 100)
                .mapToObj(i -> new Producer(queue))
                .collect(Collectors.toList());
        producers.forEach(Thread::start);

        final List<Consumer> consumers = IntStream.range(0, 5)
                .mapToObj(i -> new Consumer(queue))
                .collect(Collectors.toList());
        consumers.forEach(Thread::start);
    }

    public static void methodA() {
        log.info("methodA started");
        ///
        methodB();
        //
        log.info("methodA ended");
    }

    @SneakyThrows
    public static void methodB() {
        log.info("methodB started");

        TimeUnit.SECONDS.sleep(5);

        log.info("methodB ended");
    }

    public static void methodC() {
        statistic.incrementNumberOfMethodCExecutions();
    }

}

class Statistic {

    @Getter
    private int numberOfMethodCExecutions;

    public synchronized void incrementNumberOfMethodCExecutions() {
        int k = numberOfMethodCExecutions + 1;
        numberOfMethodCExecutions = k;
    }

}

@Slf4j
@RequiredArgsConstructor
class StatisticOutputWriter extends Thread {

    private final Statistic statistic;
    private final int rateInSeconds;


    private boolean needRun = true;
    private boolean paused = false;

    @SneakyThrows
    @Override
    public void run() {
        while (needRun) {
            // if NumberOfMethodCExecutions > 1000, set to 0 and show alert
            checkIfPausedAndWait();

            log.info("C executed {} time", statistic.getNumberOfMethodCExecutions());
            TimeUnit.SECONDS.sleep(rateInSeconds);
        }
    }

    public void finish() {
        log.info("finished");
        needRun = false;
    }

    private void checkIfPausedAndWait() throws InterruptedException {
        while (paused) {
            waitUntilResume();
        }
    }

    private synchronized void waitUntilResume() throws InterruptedException {
        this.wait();
    }

    public void pause() {
        log.info("pause");
        paused = true;
    }

    public synchronized void unpause() {
        log.info("unpause");
        paused = false;
        this.notify();
    }
}

@Slf4j
class Producer extends Thread {

    private final Queue<Integer> queue;

    public Producer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                log.info("producing");
                IntStream.range(0, 30).forEach(i -> {
                    queue.offer(new Random().nextInt());
                });
                queue.notifyAll();
            }
            Thread.sleep(5000);
        }
    }
}

@Slf4j
class Consumer extends Thread {
    private final Queue<Integer> queue;

    Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Integer element = null;
            synchronized (queue) {
                element = queue.poll();
                while (element == null) {
                    queue.wait();
                    element = queue.poll();
                }
            }
            log.info("polled = {}", element);
        }
    }
}

