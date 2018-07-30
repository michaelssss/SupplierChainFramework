package com.jzqh;

import com.jzqh.daemon.BusinessInitialActionCenter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public abstract class SpringBootTestBasic {
    @Before
    public void beforeLoad() {
        log.info("start wait BusinessInitialActionCenter.status=" + BusinessInitialActionCenter.getStatus());
        while (true) {
            if (BusinessInitialActionCenter.getStatus().equals("Idle")) {
                log.info("BusinessInitialActionCenter.status=" + BusinessInitialActionCenter.getStatus());
                break;
            } else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.info("init", e);
                }
            }
        }
    }
}
