package tests;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class OneThousandSockets {

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		for (int i = 0; i < 1000; i++) {
		    Thread t = new Thread(new Runnable() {
		        @Override
		        public void run() {
		            try {
		                final ServerSocket ss = new ServerSocket();
		                ss.setReuseAddress(true);
		                ss.bind(new InetSocketAddress(12345));
		                Socket s = ss.accept();
		                System.out.println((char) s.getInputStream().read());
		                ss.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    });
		    t.start();
		    Thread.sleep(50);
		    Socket s = new Socket("localhost", 12345);
		    s.getOutputStream().write('c');
		    t.join();
		    
		}

	}

}
