package com.xiaojun.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio服务端
 * @author xiaojun
 * @email  lxjluoxiaojun@163.com
 * @date   2017年3月9日
 */
public class NioServer {

	public static void main(String[] args) throws Exception {
		//打开ServerScoketChannel
		int port=8080;
		ServerSocketChannel acceptorSvr=ServerSocketChannel.open();
		acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"), port));
		acceptorSvr.configureBlocking(false);
		
		Selector selector=Selector.open();
		new Thread().start();
		
		SelectionKey key=acceptorSvr.register(selector,SelectionKey.OP_ACCEPT);
		
		int num=selector.select();
		
		Set selectedKeys=selector.selectedKeys();
		
		Iterator it=selectedKeys.iterator();
		while (it.hasNext()) {
			SelectionKey key1=(SelectionKey) it.next();
		}
		
		
	}
}
