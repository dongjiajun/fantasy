package com.fantasy.brace.network.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 简单客户端
 *
 * @author DongJiaJun
 */
public class SimpleClient implements Runnable {

    /**
     * 客户端插口
     */
    private Socket sock;

    /**
     * 服务器主机(域名 或 ip 都应该支持)
     */
    private final String serverHost;

    /**
     * 服务器端口号
     */
    private final int serverPort;

    public SimpleClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.sock = null;
    }

    private void execute() throws IOException {
        sock = new Socket(serverHost, serverPort);
        InputStream input = sock.getInputStream();
        OutputStream output = sock.getOutputStream();

        try {
            handle(input, output);
        } catch (IOException e) {
            try {
                this.sock.close();
            } catch (IOException ioe) {
            }
            System.out.println("和服务器的连接被意外终止！");
            return;
        }
        sock.close();
        System.out.println("已断开连接.");
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        System.out.println("[server] " + reader.readLine());
        for (; ; ) {
            System.out.print(">>> ");
            String s = scanner.nextLine();
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            if ("bye".equals(resp)) {
                break;
            }
        }
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
