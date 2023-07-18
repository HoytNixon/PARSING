package com.example.PARSING;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import static com.example.PARSING.ParseUrl.*;

public class MultiApplication {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {

		String url = "https://skillbox.ru/";
		Link parent = new Link(url);
		ForkJoinPool pool = new ForkJoinPool();
		CopyOnWriteArrayList<Link> totalLinks= new CopyOnWriteArrayList<Link>();
		RecursiveParsing recursiveParsing = new RecursiveParsing(parent);
		pool.invoke(recursiveParsing);
		print(parent,0);
		System.out.println(sb.toString());



	}

	public static void print(Link link, int tabs){
		sb.append(link.getUrl()).append("\n");
		for (int i = tabs; i>0; i-- ){
			sb.append("\t");
		}
		link.childs.stream().forEach(child->print(child, tabs+1));
	}


}
