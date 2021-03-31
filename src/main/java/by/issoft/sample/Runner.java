package by.issoft.sample;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Runner {

    static Statistic statistic = new Statistic();

    public static void main(String[] args) throws InterruptedException {

//        http://tutorials.jenkov.com/java-concurrency/index.html

        final StatisticOutputWriter statisticOutputWriter = new StatisticOutputWriter(statistic, 1);
        statisticOutputWriter.start();

        final List<Thread> cExecutionThreads = IntStream.range(0, 20)
                .mapToObj(i -> new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        methodC();
                    }
                })).collect(Collectors.toList());
        cExecutionThreads.forEach(Thread::start);

        TimeUnit.SECONDS.sleep(1);

        statisticOutputWriter.pause();

        TimeUnit.SECONDS.sleep(3);

        statisticOutputWriter.unpause();

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

    public void incrementNumberOfMethodCExecutions() {
        numberOfMethodCExecutions++;
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
           while (paused) {
               TimeUnit.SECONDS.sleep(5);
           }

           log.info("C executed {} time", statistic.getNumberOfMethodCExecutions());
           TimeUnit.SECONDS.sleep(rateInSeconds);
       }
    }

    public void finish() {
        log.info("finished");
        needRun = false;
    }

    public void pause() {
        log.info("pause");
        paused = true;
    }

    public void unpause() {
        log.info("unpause");
        paused = false;
    }
}
