package com.example.PARSING;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class Link implements Comparable<Link> {
    private String url;
    public CopyOnWriteArrayList<Link> childs = new CopyOnWriteArrayList<>();
    public Link(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public void addChild (Link child){
        childs.add(child);
    }
    public CopyOnWriteArrayList<Link> getChilds(){
        return childs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link link)) return false;

        return getUrl().equals(link.getUrl());
    }

    @Override
    public int hashCode() {
        return getUrl().hashCode();
    }

    @Override
    public int compareTo(Link o) {
        return this.url.compareTo(o.getUrl());
    }

    @Override
    public String toString() {
        return url;
    }
}
