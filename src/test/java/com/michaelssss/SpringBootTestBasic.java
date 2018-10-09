package com.michaelssss;

import com.michaelssss.daemon.BusinessInitialActionCenter;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@Ignore
@SpringBootTest(classes = SupplierChainFramework.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class SpringBootTestBasic {

  @Before
  public void beforeLoad() {
    log.info(
        "start wait BusinessInitialActionCenter.status=" + BusinessInitialActionCenter.getStatus());
    while (true) {
      if (BusinessInitialActionCenter.getStatus().equals("Idle")) {
        log.info("BusinessInitialActionCenter.status=" + BusinessInitialActionCenter.getStatus());
        break;
      } else {
        try {
          TimeUnit.SECONDS.sleep(1);
          log.info("BusinessInitialActionCenter.status=" + BusinessInitialActionCenter.getStatus());
        } catch (InterruptedException e) {
          log.info("init", e);
        }
      }
    }
  }
}
