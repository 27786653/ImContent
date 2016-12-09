package com.yuhi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket s;
	private BufferedReader br; 
	private PrintWriter out;
	private boolean flag = true;
	
	public static void main(String[] args) {
		new Client().stratUp();
	}

	private void stratUp() {
		BufferedReader sbr = null;
		try {
			s = new Socket("127.0.0.1", 11118);
			out = new PrintWriter(s.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out.println("老林");
			sbr = new BufferedReader(new InputStreamReader(System.in));
			new Thread(new ClientThread()).start();//消息发送线程
			String str = null;
			while (flag && (str=sbr.readLine())!=null) { //消息接受线程
				if (!flag) break;
				out.println(str);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (s != null) s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (sbr != null) s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void receive() {
		try {
			String rs = br.readLine();
			if (rs.equalsIgnoreCase("exit")) {
				flag = false;
				System.out.println("点击回车退出");
			}
			System.out.println(rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class ClientThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				if (!flag) break;
				receive();
			}
		}
		
	}

}