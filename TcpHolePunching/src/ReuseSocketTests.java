import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ReuseSocketTests {

	public static void main(String[] args) {
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
		    try {
				Thread.sleep(50);
			    Socket s = new Socket("localhost", 12345);
			    s.getOutputStream().write('c');
			    t.join();
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
