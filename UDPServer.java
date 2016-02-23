import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Arrays;




public class UDPServer {
	
	// Throws exception merely to focus on goal of program
	public static void main(String[] args) throws Exception {
		
		//Tells java to give UDP socket (path) that uses port 12345
		DatagramSocket serverSocket = new DatagramSocket(12345);
		
		//byte array
		byte[] data = new byte[1024];
		
		//packet will literally contain bytes, therefore needs a byte array & array length as parameters
		DatagramPacket receivedPacket = new DatagramPacket(data, data.length);
		
		//sends packet to the server
		serverSocket.receive(receivedPacket);
		
		//relevant data
		byte[] relevantData = Arrays.copyOf(receivedPacket.getData(), receivedPacket.getLength());
		
		//creates string from byte array inside receivedPacket
		String stringData = new String(relevantData);
		
		//capitalize string at the server
		stringData = stringData.toUpperCase();
		
		//must know client's address as well as client's port
		//There getAddress will find what the packets address is
		InetAddress returnAddress = receivedPacket.getAddress();
		
		//to get port
		int returnPort = receivedPacket.getPort();
		
		//Now we have the packet's address and return port
		// create a new byte Array to send back
		byte[] returnData = stringData.getBytes();
		
		// creates response Packet based on returnData, returnAddress, and returnPort values
		DatagramPacket responsePacket = new DatagramPacket(returnData, returnData.length, returnAddress, returnPort);
		
		//Sends the newly created responsePacket
		serverSocket.send(responsePacket);
		
		//Closes the used server socket (path)
		serverSocket.close();
		
	}

}
