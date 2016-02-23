import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;

public class TCPclient {

	public static void main(String[] args) throws Exception {
		// create the communication socket
		Socket connectSocket = new Socket(args[0], 54321);
		
		//input stream is FROM server
		InputStream readStream = connectSocket.getInputStream();
		OutputStream writeStream = connectSocket.getOutputStream();
		
		//read the message to send
		Scanner lineScanner = new Scanner(System.in);
		System.out.print("Enter the message to send: ");
		String messageToSend = lineScanner.nextLine();
		
		//send the message to the server
		//converts string to binary
		writeStream.write(messageToSend.getBytes());
		
		//receive the response from the server
		byte[] buffer = new byte[1024];
		
		int bytesRead = readStream.read(buffer);
		
		String responseString = new String(Arrays.copyOf(buffer, bytesRead));
		
		System.out.println("Response: "+ responseString);
		
		connectSocket.close();
		
	}

}
