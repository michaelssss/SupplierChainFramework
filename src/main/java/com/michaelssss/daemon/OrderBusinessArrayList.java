package com.michaelssss.daemon;

import com.michaelssss.RootUser;
import com.michaelssss.scanner.RouterSaver;

import java.util.*;

public class OrderBusinessArrayList implements Iterable<Action> {
    private List<Action> actions = new ArrayList<>();

    public void add(Action action) {
        this.actions.add(action);
    }

    public Action get(int i) {
        return actions.get(i);
    }

    public int size() {
        return actions.size();
    }

    public void clear() {
        actions.clear();
    }

    public void sorted() {
        Map<Class<? extends Action>, Integer> voteMap = new HashMap<>();
        //初始化投票表
        for (Action action : actions) {
            voteMap.put(action.getClass(), 0);
        }
        //遍历所有的depends,对Action做投票
        for (Action action : actions) {
            List<Class<? extends Action>> dependList = action.getDepends();
            for (Class<? extends Action> dependAction : dependList) {
                voteMap.computeIfPresent(RouterSaver.class, (aClass, integer) -> integer + 3);
                voteMap.computeIfPresent(RootUser.class, (aClass, integer) -> integer + 2);
                voteMap.computeIfPresent(dependAction, (aClass, integer) -> integer + 1);
            }
        }
        SortedNode[] sortedNodes = new SortedNode[this.actions.size()];
        int index = 0;
        //Map中的Class一定是与actions中相同或者少于
        for (Map.Entry<Class<? extends Action>, Integer> entry : voteMap.entrySet()) {
            for (Action action : this.actions) {
                if (entry.getKey().equals(action.getClass())) {
                    SortedNode sortedNode = new SortedNode();
                    sortedNode.action = action;
                    sortedNode.order = entry.getValue();
                    sortedNodes[index] = sortedNode;
                }
            }
            index++;
        }
        Arrays.sort(sortedNodes);
        this.actions = new ArrayList<>();
        for (SortedNode sortedNode : sortedNodes) {
            this.actions.add(sortedNode.action);
        }
    }

    @Override
    public Iterator<Action> iterator() {
        return actions.iterator();
    }

    private static class SortedNode implements Comparable<SortedNode> {
        private Action action;
        private int order;

        @Override
        public int compareTo(SortedNode o) {
            return Integer.compare(o.order, this.order);
        }
    }
}

