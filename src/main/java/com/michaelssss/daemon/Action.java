package com.michaelssss.daemon;

import java.util.ArrayList;
import java.util.List;

/**
 * 若启动需要执行一些任务，则实例化这个 如果你初始化时候需要依赖一些其他Action，那么再Depend中增加既可，会自动计算初始化顺序的
 */
public abstract class Action {

  public abstract void act();

  public List<Class<? extends Action>> getDepends() {
    return new ArrayList<>();
  }
}
