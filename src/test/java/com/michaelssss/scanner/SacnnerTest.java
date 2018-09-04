package com.michaelssss.scanner;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.account.Authority;
import com.michaelssss.account.AuthorityCatalog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author Tongch
 * @version 1.0
 * @time 2018/7/30 10:58
 */
@Slf4j
public class SacnnerTest extends SpringBootTestBasic {

    @Autowired
    private AuthorityCatalog authorityCatalog;

    @Test
    public void getAllUrlTest() throws ClassNotFoundException {
        RouteScanner routeScanner = new RouteScanner("com.michaelssss.rzzl2");
        Set<String> urlSet = routeScanner.getAllUrl();
        for (String url : urlSet) {
            log.info(url);
            Authority authority = new Authority();
            authority.setPath(url);
            authorityCatalog.saveAndFlush(authority);
        }

    }

}
