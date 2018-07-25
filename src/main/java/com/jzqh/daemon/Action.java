package com.jzqh.daemon;

/**
 * 若启动需要执行一些任务，则实例化这个
 */
public interface Action {
    void act();
}
