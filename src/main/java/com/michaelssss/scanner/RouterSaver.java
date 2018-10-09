package com.michaelssss.scanner;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.account.FunctionName;
import com.michaelssss.account.FunctionNameCatalog;
import com.michaelssss.daemon.Action;
import com.michaelssss.daemon.BusinessInitialActionCenter;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

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
    FunctionNameCatalog catalog = SpringContextHolder.getBean(FunctionNameCatalog.class);
    Set<FunctionName> authorities = new HashSet<>();
    Set<FunctionName> strings = routeScanner.getAllFunctionNames("com.michaelssss");
    for (FunctionName functionName : strings) {
      if (!catalog.exists(Example.of(functionName))) {
        authorities.add(functionName);
      }
    }
    catalog.saveAll(authorities);
    catalog.flush();
  }
}
