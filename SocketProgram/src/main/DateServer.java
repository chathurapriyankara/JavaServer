package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateServer {
	private static final int PORT = 9090;

	public void server() throws IOException {
		ServerSocket listner = new ServerSocket(PORT);
		System.out.println("Server started on: " + PORT);
		try {
			while (true) {
				Socket socket = listner.accept();

				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					System.out.println("A client request recieved at " + socket);
					out.println(new Date().toString());
				} finally {
					listner.close();
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			listner.close();
		}
	}

	public static void main(String[] args) {
		try {
			ServerSocket socket = new ServerSocket(9090);
			while (true) {
				ServerThread thread = new ServerThread(socket.accept());
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			new DateServer().server();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
