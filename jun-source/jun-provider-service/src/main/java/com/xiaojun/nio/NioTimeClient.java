package com.xiaojun.nio;

public class NioTimeClient {
	public static void main(String[] args) {
		new Thread(new TimeClientHandle("127.0.0.1", 8080),"TimeClient-001").start();;
	}
}
