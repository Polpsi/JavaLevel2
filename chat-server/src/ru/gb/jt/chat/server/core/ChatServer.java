package ru.gb.jt.chat.server.core;

import ru.gb.jt.network.ServerSocketThread;
import ru.gb.jt.network.ServerSocketThreadListener;
import ru.gb.jt.network.SocketThread;
import ru.gb.jt.network.SocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {

    ServerSocketThread server;
    private Vector allClients = new Vector();

    public void start(int port) {
        if (server == null || !server.isAlive()) {
            System.out.println("Server started at port: " + port);
            server = new ServerSocketThread(this, "Server", port, 2000);
        } else {
            System.out.println("Server already started!");
        }
    }

    public void stop() {
        if (server != null && server.isAlive()) {
            System.out.println("Server stopped");
            server.interrupt(); //null.interrupt();
        } else {
            System.out.println("Server is not running");
        }
    }

    private void putLog(String msg) {
        System.out.println(msg);
    }

    /**
     * Server Socket Thread methods
     */

    @Override
    public void onServerStart(ServerSocketThread thread) {
        putLog("Server started");
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server stopped");
    }

    @Override
    public void onServerSocketCreated(ServerSocketThread thread, ServerSocket server) {
        putLog("Server socket created");
    }

    @Override
    public void onServerTimeout(ServerSocketThread thread, ServerSocket server) {
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket) {
        putLog("Client connected");
        String name = "Socket Thread " + socket.getInetAddress() + ":" + socket.getPort();
        allClients.addElement(new SocketThread(this, name, socket));
    }

    @Override
    public void onServerException(ServerSocketThread thread, Throwable exception) {
        exception.printStackTrace();
    }

    /**
     * Socket Thread methods
     */

    @Override
    public void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Client connected");
    }

    @Override
    public void onSocketStop(SocketThread thread) {
        putLog("Client disconnected");
        thread.close();
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Client is ready to chat");
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String msg) {
        if (msg.equals("1q1q1q1q")) {
            this.onSocketStop(thread);
        } else {
            //thread.sendMessage("Echo: " + msg);
            //по умолчанию отправляем всем в чат, пока нет приватных бесед.
            for (int i = 0; i < allClients.size(); i++) {
                SocketThread client = (SocketThread) allClients.get(i);
                client.sendMessage(msg);
            }
        }
    }

    @Override
    public void onSocketException(SocketThread thread, Exception exception) {
        exception.printStackTrace();
    }
}
