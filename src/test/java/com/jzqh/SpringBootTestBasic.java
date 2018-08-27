package com.jzqh;

import com.jzqh.daemon.BusinessInitialActionCenter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest(classes = Rzzl2Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
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
                    log.info("BusinessInitialActionCenter.status=" + BusinessInitialActionCenter.getStatus());
                } catch (InterruptedException e) {
                    log.info("init", e);
                }
            }
        }
    }
}
