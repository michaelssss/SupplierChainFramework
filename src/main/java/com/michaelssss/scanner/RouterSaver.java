package com.michaelssss.scanner;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.account.Authority;
import com.michaelssss.account.AuthorityCatalog;
import com.michaelssss.daemon.Action;
import com.michaelssss.daemon.BusinessInitialActionCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class RouterSaver extends Action {

    @PostConstruct
    public void init() {
        BusinessInitialActionCenter.registeredAction(new RouterSaver());
    }

    @Override
    public void act() {
        RouteScanner routeScanner = new RouteScanner("com.michaelssss");
        AuthorityCatalog catalog = SpringContextHolder.getBean(AuthorityCatalog.class);
        Set<Authority> authorities = new HashSet<>();
        Set<String> strings = routeScanner.getAllUrl();
        for (String url : strings) {
            Authority authority = new Authority();
            authority.setPath(url);
            authority.setName(url);
            if (!catalog.exists(Example.of(authority))) {
                authorities.add(authority);
            }
        }
        catalog.save(authorities);
        catalog.flush();
    }
}
