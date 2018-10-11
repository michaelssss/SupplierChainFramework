package com.michaelssss.scanner;

import com.michaelssss.account.FunctionName;
import com.michaelssss.account.FunctionNameCatalog;
import com.michaelssss.daemon.Action;
import com.michaelssss.daemon.BusinessInitialActionCenter;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * 本类用于初始化Url功能路径，并将其保存至数据库
 *
 * @author Michaelssss
 * @date 2018年10月11日
 */
@Slf4j
@Component
public class RouterSaver extends Action {

  private FunctionNameCatalog catalog;

  @Autowired
  public RouterSaver(FunctionNameCatalog catalog) {
    this.catalog = catalog;
  }

  @PostConstruct
  public void init() {
    BusinessInitialActionCenter.registeredAction(this);
  }

  @Override
  public void act() {
    RouteScanner routeScanner = new RouteScanner();
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
