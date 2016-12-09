package com.yuhi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {
	private List<ServerThread> clients = null;
	private static int port=11118;
	private static boolean showOnLineflag=true;
	
	public static void main(String[] args) {
		try {
			if(initSetting(args))
			new Server().startUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static boolean initSetting(String[] args){
		boolean flag=true;
		try {
			if(args.length>0){
				for (int i = 0; i < args.length; i++) {
					int index=args[i].indexOf("-");
					if(index!=-1){
						switch (args[i]) {
						case "-p":
							port=Integer.valueOf(args[i+1]);
							break;
						case "-s":
							showOnLineflag=true;
							break;
						default:
							flag=false;
							System.err.println("未能识别该命令！");
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}
	private void startUp() {
		ServerSocket ss = null;
		Socket s = null;
		try {
			ss = new ServerSocket(port);
			System.out.println("服务开启成功！");
			clients = new ArrayList<ServerThread>();
			while (true) {
				s = ss.accept();
				ServerThread st = new ServerThread(s);
				new Thread(st).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ss != null) 
					ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class ServerThread implements Runnable {
		private Socket s = null;
		private BufferedReader br;
		private PrintWriter out;
		private String name;
		private boolean flag = true;
		
		public ServerThread(Socket socket) throws IOException {
			this.s = socket;
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			String str = br.readLine();
			name = "("+str+")";
			clients.add(this);
			if(showOnLineflag)System.out.println(name+" : ["+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+"]"+": is online!!");
			send(name+" : ["+socket.getInetAddress().getHostAddress()+":"+socket.getPort()+"]"+": is online!!");
		}
		
		private void send(String msg) {
			try {
				for (Iterator it = clients.iterator(); it.hasNext();) {
					ServerThread str = (ServerThread)it.next();
					str.out.println(msg);
				}
			} catch (Exception e) {
				//e.printStackTrace();
			} 
		}
		
		private void receive() throws IOException {
			String str = null;
			while ((str=br.readLine()) != null) {
				if (str.equalsIgnoreCase("exit!")) {
					stop();
					out.println("exit!");
					break;
				}
				if(str!=null&&str.length()>0)send(name+" : ["+str+"]");
			}
		}
		
		private void stop() {
			clients.remove(this);
			flag = false;
			if(showOnLineflag)System.out.println(name+" is lost!!");
			send(name+" is lost!!");
		}

		@Override
		public void run() {
			try {
				while (true) {
					if (!flag) break;
					receive();
				}
			} catch (SocketException e) {
				stop();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (s != null) 
						s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}