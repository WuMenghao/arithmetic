package com.wmh.arithmetic.aliinterview;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class AliInterview005 {

  /**
   * （JAVA）有 3 个独立的线程，一个只会输出 A，一个只会输出 L，一个只会输出 I。 在三个线程同时启动的情况下，请用合理的方式让他们按顺序打印 ALIALI。
   * 三个线程开始正常输出后，主线程若检测到用户任意的输入则停止三个打印线程的工 作，整体退出。
   *
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException {
    // 工作线程 负责进行输出调度
    Thread workerStarter = new Thread(new Worker());
    workerStarter.start();

    // 键入 如果有输入就推出程序
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      System.out.println(scanner.next());
      workerStarter.interrupt();
      System.exit(0);
    }
  }

  /**
   * @author menghaowu
   *     <p>工作线程
   */
  static class Worker implements Runnable {
    private final AtomicInteger i = new AtomicInteger(0);
    private Thread thread1;
    private Thread thread2;
    private Thread thread3;

    public Worker() {
      thread1 =
          new Thread(
              () -> {
                while (true) {
                  LockSupport.park();
                  System.out.println("A");
                }
              });
      thread2 =
          new Thread(
              () -> {
                while (true) {
                  LockSupport.park();
                  System.out.println("L");
                }
              });
      thread3 =
          new Thread(
              () -> {
                while (true) {
                  LockSupport.park();
                  System.out.println("I");
                }
              });
    }

    @Override
    public void run() {
      thread1.start();
      thread2.start();
      thread3.start();
      while (true) {
        if (i.get() % 3 == 0) {
          LockSupport.unpark(thread1);
        } else if (i.get() % 3 == 1) {
          LockSupport.unpark(thread2);
        } else if (i.get() % 3 == 2) {
          LockSupport.unpark(thread3);
        }
        i.getAndIncrement();
        try {
          Thread.sleep(1000);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
