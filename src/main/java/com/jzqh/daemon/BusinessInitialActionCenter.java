package com.jzqh.daemon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 如何使用，首先要实现Action接口，因为Java不支持指针函数
 * 其次是将需要执行的任务调用BusinessInitialActionCenter.registeredAction(Action yourAction)
 * 为了避免Spring初始化完成前执行任务，需要等待Spring容器发布Context执行完毕后才可以进行自定义任务的执行
 * ！！！！！！！！！！！！！！！！！！！！！！请不要执行要异步返回的任务，不支持，不支持
 */
@Slf4j
@Service
public class BusinessInitialActionCenter implements ApplicationListener<ApplicationEvent> {
    private final static BlockingQueue<Action> queue = new LinkedBlockingQueue<>(32);
    private final static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static boolean initFlag = false;

    /**
     * 业务任务注册至任务执行中心
     *
     * @param action 无返回值任务
     */
    public static void registeredAction(Action action) {
        try {
            queue.put(action);
        } catch (InterruptedException e) {
            log.warn("BusinessInitialActionCenter.registeredAction has been interrupt, data may lose");
        }
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            if (!initFlag) {
                log.info("======================System daemon started=============================");
                initFlag = true;
                executor.execute(() -> {
                    while (true) {
                        try {

                            Action action = queue.take();
                            action.act();
                            log.info("clazz" + action.getClass().getSimpleName() + " has been init and act ");
                        } catch (InterruptedException e) {
                            log.warn("Business Thread had been interrupt ,because of ", e);
                        }
                    }
                });
            }
        }
    }
}
