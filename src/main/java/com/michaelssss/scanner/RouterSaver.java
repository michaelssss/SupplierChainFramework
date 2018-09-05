package com.michaelssss.scanner;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.account.AuthorityCatalog;
import com.michaelssss.account.FunctionName;
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
        RouteScanner routeScanner = new RouteScanner();
        AuthorityCatalog catalog = SpringContextHolder.getBean(AuthorityCatalog.class);
        Set<FunctionName> authorities = new HashSet<>();
        Set<FunctionName> strings = routeScanner.getAllFunctionNames("com.michaelssss");
        for (FunctionName functionName : strings) {
            if (!catalog.exists(Example.of(functionName))) {
                authorities.add(functionName);
            }
        }
        catalog.save(authorities);
        catalog.flush();
    }
}
