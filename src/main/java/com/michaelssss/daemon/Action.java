package com.michaelssss.daemon;

import java.util.ArrayList;
import java.util.List;

/**
 * 若启动需要执行一些任务，则实例化这个
 */
public abstract class Action {
    public abstract void act();

    public List<Class<? extends Action>> getDepends() {
        return new ArrayList<>();
    }
}