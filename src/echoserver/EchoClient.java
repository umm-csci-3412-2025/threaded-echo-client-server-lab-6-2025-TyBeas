package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();

		int inputByte;
		while ((inputByte = System.in.read()) != -1) {
			socketOutputStream.write(inputByte);
		}

		socketOutputStream.flush();
		socket.shutdownOutput();
		System.out.write(socketInputStream.readAllBytes());

		socketInputStream.close();
		socketOutputStream.close();
		socket.close();
		// Put your code here.
	}
}