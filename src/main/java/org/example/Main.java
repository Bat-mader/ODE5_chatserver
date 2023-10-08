package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket server = new ServerSocket(4711);
            System.out.println("Server: waiting for connection");
            Socket client = server.accept();
            System.out.println("Server: connected to Client " +
                    client.getInetAddress());
            InputStream is = client.getInputStream();
            BufferedReader p_IN = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder content = new StringBuilder();
            content.append("Client:   ");
            while ((line = p_IN.readLine()) != null) {
                if (line.contains("**TERM**")) {
                    is.close();
                    client.close();
                    return;
                }
                content.append(line);
                content.append(System.lineSeparator());
            }
            System.out.println(content);
            content.setLength(0);


        } catch (IOException e) {
// Generelles Exception-Handling zu Demo-Zwecken
            e.printStackTrace();
        }
    }


}
