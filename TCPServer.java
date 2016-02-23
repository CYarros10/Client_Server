import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class TCPServer {

	public static void main(String[] args) throws Exception {
		//server socket listening at port 54321
		ServerSocket listenSocket = new ServerSocket(54321);

		//socket needs to be remade every different connection

		while (true) {
			//block until accepting connection
			Socket connectionSocket = listenSocket.accept();

			//data communication
			//Set up streams (no communication yet)
			//input stream is FROM client
			
			InputStream readStream = connectionSocket.getInputStream();
			OutputStream writeStream = connectionSocket.getOutputStream();

			byte[] buffer = new byte[1024];

			//takes byte array and inputs data
			int bytesRead = readStream.read(buffer);

			String receivedString = new String(Arrays.copyOf(buffer, bytesRead));

			System.out.println("Received: " + receivedString);

			// modify data
			String responseString = receivedString.toUpperCase();
			// output data
			writeStream.write(responseString.getBytes());


			//when done, clean up
			connectionSocket.close();
			listenSocket.close();
		}

	}


}
