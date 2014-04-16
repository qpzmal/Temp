package threadTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpServer implements Runnable {

	private static TcpServer server = new TcpServer();
	private TcpServer() {}
	public TcpServer getInstance() {
		return this.server;
	}
	public void run() {
		TcpServer server = getInstance();
	}

	public static void main (String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(8888, 1 , InetAddress.getByName("192.168.1.115"));
		String commond = "";
		while (!"bye".equals(commond)) {
			try {
				Socket socket = ss.accept();
				InputStream ins = socket.getInputStream();
				InputStreamReader insReader = new InputStreamReader(ins, "utf-8");
				BufferedReader bfReader = new BufferedReader(insReader);
				commond = bfReader.readLine();
				System.out.println("测试：" + socket.getInetAddress() + commond);
//				while ((commond = bfReader.readLine()) != null) {
//					System.out.println("测试：" + socket.getInetAddress() + commond);
//				}
				// 返回值
				OutputStream os = socket.getOutputStream();
				// FIXME
				byte[] b = new byte[1024];
	//			b.
				os.write(("Hello"+System.lineSeparator()).getBytes("utf-8"));
				os.write(("Hello2"+System.lineSeparator()).getBytes("utf-8"));
				os.write(("Hello3"+System.lineSeparator()).getBytes("utf-8"));
//				PrintWriter pw = new PrintWriter(os, true);
//				pw.println();
			} catch(IOException e) {
				e.printStackTrace();
			}
					
		}
	
	}
}
class Server {
	public static void creatServer() throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		String commond = "";
		while (!"bye".equals(commond)) {
			Socket socket = ss.accept();
			InputStream ins = socket.getInputStream();
			InputStreamReader insReader = new InputStreamReader(ins, "utf-8");
			BufferedReader bfReader = new BufferedReader(insReader);
			while ((commond = bfReader.readLine()) != null) {
				System.out.println("测试：" + socket.getInetAddress() + commond);
			}
			// 返回值
			OutputStream os = socket.getOutputStream();
			// FIXME
			byte[] b = new byte[1024];
//			b.
			os.write("Hello".getBytes("utf-8"));
					
		}
	}
}