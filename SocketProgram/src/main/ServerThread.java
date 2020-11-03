package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	public ServerThread(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("A client request recived at " + socket);
			out.println("Hello.....");
			out.close();
			socket.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try {
			ServerSocket socket = new ServerSocket(9090);
			while(true) {
				Socket soc = socket.accept();
				new ServerThread(soc).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
