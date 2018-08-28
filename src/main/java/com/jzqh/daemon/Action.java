package com.jzqh.daemon;

/**
 * 若启动需要执行一些任务，则实例化这个
 */
public abstract class Action implements Comparable<Action> {
    public abstract void act();

    /**
     * 这个是一个小坑，算是一个临时解决方案
     * 完整的逻辑应该是计算Action之间的依赖关系，形成一个执行序列
     *
     * @return
     */
    public abstract int getOrder();

    @Override
    public int compareTo(Action o) {
        return Integer.compare(this.getOrder(), o.getOrder());
    }
}
