package oioudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPClient {

	public static void main(String[] args) throws IOException, InterruptedException {
		DatagramSocket client = new DatagramSocket();
		String sendStr = "Hello! I'm Client";
		byte[] sendBuf;
		sendBuf = sendStr.getBytes();
//		InetAddress addr = InetAddress.getByName("172.21.36.34");
//		InetAddress addr = InetAddress.getByName("localhost");
		InetAddress addr = InetAddress.getByName("192.168.0.105");
		int port = 1812;
		DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, addr,port);
		
		client.send(sendPacket);
		
		byte[] recvBuf = new byte[100];
		DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
		client.receive(recvPacket);
		
		String recvStr = new String(recvPacket.getData(), 0 ,recvPacket.getLength());
		System.out.println("receive : " + recvStr);
		Thread.sleep(60000*10);
		client.close();
		
	}

}
