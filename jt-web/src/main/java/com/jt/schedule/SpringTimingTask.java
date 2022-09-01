package com.jt.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class SpringTimingTask {

    @Scheduled(cron = "0/5 * *  * * ? ") // 每5秒执行一次
    public void doTask(){
        /* 开启一个异步线程执行任务 **/
        // 从线程池获取线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("定时任务开始执行。。。");
            }
        });
        thread.start();
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName+"===当前线程已准备就绪。。。");
        try {
            System.out.println(threadName+"===线程2秒后开始执行。。。");
            thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.run();
        thread.stop();
    }
}
