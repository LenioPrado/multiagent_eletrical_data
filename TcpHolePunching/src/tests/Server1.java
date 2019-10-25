package tests;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

	public static int serverPort = 10801;

	public static void main(String[] args) {
		try {
			while (true) {
				System.out.println("Openning server on port: " + serverPort);
				Socket socket;
				ServerSocket serverSocket = new ServerSocket(serverPort);
				serverSocket.setReuseAddress(true);				
				String msg = "";
				socket = serverSocket.accept();
				socket.setReuseAddress(true);
				socket.setSoTimeout(1000);
				String data = String.format("IP: %s : %d : %d", socket.getInetAddress().getHostAddress(),
						socket.getLocalPort(), socket.getPort());
				System.out.println("Connection received from: " + data);
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);
				//			while (!msg.equals("q")) {				
								msg = br.readLine();
								System.out.println("Received: " + msg);
				//				//Thread.sleep(5000);				
				//			}
				System.out.println("Ending...");
				br.close();				
				if(socket != null) {
					socket.close();
				}
				
				serverSocket.close();
				socket = null;
				serverSocket = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
