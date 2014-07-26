package oioudp;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPServer {

	public static void main(String[] args) throws IOException, InterruptedException{
		DatagramSocket server = new DatagramSocket(1812);
		
		DatagramSocket server2 = new DatagramSocket(1813);
		
		byte[] recvBuf = new byte[100];
		DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);

//		UDPServer.pathSample();

		server.receive(recvPacket);
		
		String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
		System.out.println("Hello World!client " + recvStr);
		
		int port = recvPacket.getPort();
		InetAddress addr= recvPacket.getAddress();
		String sendStr = "Hello ! I'm Server";
		byte[] sendBuf;
		sendBuf = sendStr.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, addr,port);
		//wry11
		server2.send(sendPacket);
		Thread.sleep(600000*10);
		//wry11
//		server.send(sendPacket);
//		Thread.sleep(60000*10);
//		server.close();
	}
	private static void pathSample () {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		System.out.println(UDPServer.class.getClassLoader().getResource(""));
		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(UDPServer.class.getResource(""));
		System.out.println(UDPServer.class.getResource("/"));
		System.out.println(new File("").getAbsolutePath());
		System.out.println(System.getProperty("user.dir"));
	}
}
