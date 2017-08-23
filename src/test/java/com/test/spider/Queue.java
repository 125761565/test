package com.test.spider;

import java.util.LinkedList;

public class Queue {
	private LinkedList<Object> queue=new LinkedList<Object>();//入队列
	
	//加入队列
	public void enQueue(Object t) {
		queue.add(t);
	}
	//移除队列
	public Object deQueue() {
		return queue.removeFirst();
	}
	
	//返回队列为空
	public boolean isQueueEmpty() {
		return queue.isEmpty();
	}
	
	//判断并返回队列是否包含 t
	public boolean contians(Object t) {
		return queue.contains(t);
	}
	
	//判断并返回队列是否为空
	public boolean empty() {
		return queue.isEmpty();
	}
}
