package Lesson4.chat.server.core;

public class ChatServer {

    public void start(int port) {
        System.out.println("Server started at port: " + port);
    }

    public void stop() {
        System.out.println("Server stopped");
    }

    //Здесь, в методе обрабатывающем получение-передачу сообщений - писать в серверный history.log историю сообщений.
}
