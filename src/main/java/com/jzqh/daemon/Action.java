package com.jzqh.daemon;

/**
 * 若启动需要执行一些任务，则实例化这个
 */
public abstract class Action implements Comparable<Action> {
    public abstract void act();

    public abstract int getOrder();

    @Override
    public int compareTo(Action o) {
        return Integer.compare(this.getOrder(), o.getOrder());
    }
}
