package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
    Socket socket = null;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        InputStream socketInputStream = null;

        InputStreamReader inputStreamReader = null;

        BufferedReader bufferedReader = null;

        OutputStream socketOutputStream = null;

        PrintWriter printWriter = null;

        try {
            socketInputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(socketInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            socketOutputStream = socket.getOutputStream();
            printWriter = new PrintWriter(socketOutputStream);

            int inputByte;
            while ((inputByte = bufferedReader.read()) != -1) {
                printWriter.write(inputByte);
                printWriter.flush();
            }

            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketInputStream != null) {
                    socketInputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (socketOutputStream != null) {
                    socketOutputStream.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                if (socket != null)  {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
