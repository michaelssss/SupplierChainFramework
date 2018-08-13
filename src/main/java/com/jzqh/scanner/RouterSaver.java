package com.jzqh.scanner;

import com.jzqh.SpringContextHolder;
import com.jzqh.account.Authority;
import com.jzqh.account.AuthorityCatalog;
import com.jzqh.daemon.Action;
import com.jzqh.daemon.BusinessInitialActionCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
class RouterSaver extends Action {

    @Override
    public int compareTo(Action o) {
        return Integer.compare(this.getOrder(), o.getOrder());
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @PostConstruct
    public void init() {
        BusinessInitialActionCenter.registeredAction(new RouterSaver());
    }

    @Override
    public void act() {
        RouteScanner routeScanner = new RouteScanner("com.jzqh");
        AuthorityCatalog catalog = SpringContextHolder.getBean(AuthorityCatalog.class);
        Set<Authority> authorities = new HashSet<>();
        Set<String> strings = routeScanner.getAllUrl();
        for (String url : strings) {
            Authority authority = new Authority();
            authority.setUrl(url);
            authority.setName(url);
            if (!catalog.exists(Example.of(authority))) {
                authorities.add(authority);
            }
        }
        catalog.save(authorities);
        catalog.flush();
    }
}
