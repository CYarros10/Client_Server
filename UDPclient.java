import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UDPclient {

	public static void main(String[] args) throws Exception {
		
		//nothing to do with networking code
		Scanner dataScanner = new Scanner(System.in);
		
		String dataToSend = dataScanner.nextLine();
		
		//create new socket (path) to send modified data
		//client doesn't need specific port to use, server will know where to reach client once it receives data from client
		DatagramSocket clientSocket = new DatagramSocket();
		
		//create data packet to send
		byte[] StringDataToSend = dataToSend.getBytes();
		
		//create Inet address
		InetAddress toAddress = InetAddress.getByName(args[0]);
		
		int toPort = 12345;
		
		//Creating new packet to send requires data, data length, toAddress, and toPort
		DatagramPacket sendPacket = new DatagramPacket(StringDataToSend, StringDataToSend.length, toAddress, toPort);
		
		//send the packet through created client socket
		clientSocket.send(sendPacket);
		
		byte[] receivedData = new byte[1024];
		
		DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
		
		clientSocket.receive(receivedPacket);
		
		//relevant data
		byte[] relevantData = Arrays.copyOf(receivedPacket.getData(), receivedPacket.getLength());
		
		String receivedString = new String(relevantData);
		
		System.out.println("Server Response: " + receivedString);
	}

}
