package com.michaelssss.daemon;

import com.michaelssss.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 如何使用，首先要实现Action接口，因为Java不支持指针函数 其次是将需要执行的任务调用BusinessInitialActionCenter.registeredAction(Action
 * yourAction) 为了避免Spring初始化完成前执行任务，需要等待Spring容器发布Context执行完毕后才可以进行自定义任务的执行
 * ！！！！！！！！！！！！！！！！！！！！！！请不要执行要异步返回的任务，不支持，不支持
 */
@Slf4j
@Service
public class BusinessInitialActionCenter implements ApplicationListener<ApplicationEvent> {

  private static final OrderBusinessArrayList ACTIONS = new OrderBusinessArrayList();
  private static boolean initFlag = false;

  /**
   * 业务任务注册至任务执行中心
   *
   * @param action 无返回值任务
   */
  public static void registeredAction(Action action) {
    synchronized (BusinessInitialActionCenter.class) {
      ACTIONS.add(action);
    }
  }

  public static String getStatus() {
    return ACTIONS.size() == 0 && initFlag ? "Idle" : "Busy";
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof ContextRefreshedEvent) {
      if (!initFlag) {
        ACTIONS.sorted();
        log.info("======================System daemon started=============================");
        initFlag = true;
        PlatformTransactionManager transactionManager =
            SpringContextHolder.getBean(PlatformTransactionManager.class);
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        ((DefaultTransactionDefinition) transactionDefinition)
            .setName("BusinessInitialActionCenter");
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        for (Action action : ACTIONS) {
          synchronized (BusinessInitialActionCenter.class) {
            try {
              action.act();
              log.info("clazz " + action.getClass().getSimpleName() + " has been init and act ");
            } catch (Exception e) {
              log.error("start " + action.getClass().getSimpleName() + " task failed", e);
            }
          }
        }
        ACTIONS.clear();
        transactionManager.commit(status);
      }
    }
  }
}
