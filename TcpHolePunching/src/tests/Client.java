package tests;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.stream.Stream;

public class Client {

	public static void main(String args[]) throws Exception {
		//while (true) {
			InetAddress address = InetAddress.getByName("localhost");
			int port=0;
			Socket s = null;
			try {
				s = new Socket();
				s.setSoTimeout(5000);
				s.setReuseAddress(true);
				
				s.connect(new InetSocketAddress(address, Server.serverPort));
				//System.out.println("Is Bound? " + s.isBound());
				port = s.getLocalPort();
				System.out.println("First created socket on port " + s.getPort() + " - " + port);

				InputStream is = s.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);

				OutputStream os = s.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);

				s.setTcpNoDelay(true);
				BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
				String msg = "";
				//while (!msg.equals("q")) {
				System.out.print("Type a Message: ");
				//msg = buff.readLine();
				msg = "queto";
				bw.write(msg);
				bw.write("\n");
				bw.flush();
				//}
				s.close();
				s = null;
				System.out.println("closed socket, giving time to reuse port (hopefully)");
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			

				boolean isBind = false;
				//while (!isBind) {
					Thread.sleep(15000);
					s = new Socket();
					s.setReuseAddress(true);
					//System.out.println("Is Bound? " + s.isBound());
					System.out.println("trying to bind to port " + port);
					s.bind(new InetSocketAddress(address, port));
					
					try {
						s.connect(new InetSocketAddress(address, Server1.serverPort));
						isBind = true;
					} catch (BindException ex) {
						// TODO Auto-generated catch block
						System.out.println("Error...................");
						ex.printStackTrace();
					} 
				//}
				
					System.out.println("Secondly created socket on port " + s.getPort() + " - " + s.getLocalPort());
				s.close();
				System.out.println("closed socket");

		//}
	}
}