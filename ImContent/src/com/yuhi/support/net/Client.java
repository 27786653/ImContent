package com.yuhi.support.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.yuhi.Constant;
/**
 * 聊天客户端基于简单的socket套接字实现
 * @author 李森林
 *
 */
public class Client {
	private Socket s;
	private BufferedReader br; 
	private PrintWriter out;
	private static boolean flag = true;
	
	public static boolean getContentType(){
		return flag;
	}
	public void closeContentThread(){
		flag = false;
	}
	public void sendMes(String str){
		out.println(str);
	}
	public void stratUp(final MsgCallback call) {
		flag = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					s = new Socket(Constant.CLIENT_IP, Constant.CLIENT_PORT);
					out = new PrintWriter(s.getOutputStream(), true);
					br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					out.println(Constant.NICK_NAME);
					new Thread(new ClientThread(call)).start();
					while (flag) {
						if (!flag) break;
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if(br!=null)br.close();
						if (s != null) s.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	private void receive(MsgCallback call) {
		try {
			String rs = br.readLine();
			if (rs.contains("exit!")) {
				flag = false;
				System.out.println("客户端退出！");
				rs="";
			}
			call.getContent(rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class ClientThread implements Runnable {
		private MsgCallback call;
		public ClientThread(MsgCallback call){
			this.call=call;
		}
		@Override
		public void run() {
			while (true) {
				if (!flag) break;
				receive(call);
			}
		}
		
	}
	
	
	/**
	 * 消息回调
	 * @author 李森林
	 *
	 */
	public static interface MsgCallback{
		void getContent(String content);
	}

}