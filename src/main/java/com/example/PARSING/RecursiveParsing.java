package com.example.PARSING;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;
import static com.example.PARSING.ParseUrl.*;
import java.util.concurrent.RecursiveTask;

public class RecursiveParsing extends RecursiveAction {
    //importmehanika
    private Link parent;
    private static CopyOnWriteArrayList<Link> totalLinks= new CopyOnWriteArrayList<>();
    public RecursiveParsing(Link parent) {
        this.parent = parent;
    }

    @Override
    protected void compute() {
            totalLinks.add(parent);
            List<RecursiveParsing> tasks = new ArrayList<>();
            for (Link child : parseUrl(parent)) {
                if (!totalLinks.contains(child)) {
                    totalLinks.add(child);
                    System.out.println(child);
                    parent.addChild(child);
                }
            }
            for (Link child: parent.getChilds()){
                RecursiveParsing task = new RecursiveParsing(child);
                task.fork();
                tasks.add(task);
            }
            for (RecursiveParsing task : tasks) {
                task.join();
            }
    }
}