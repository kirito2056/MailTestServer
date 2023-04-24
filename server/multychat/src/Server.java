import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Server {
    private static int uniqueId; // 번호
    private ArrayList<ClientThread> clientThreads; // 클라이언트 쓰레드 배열
    private SimpleDateFormat simpleDateFormat; // 특정 문자열 포맷으로 날짜 표현
    private int port; // 서버 포트
    private boolean isRunning; // 서버 실행 여부
    private String notif = "***";

    public Server ( int port) {
        this.port = port;
        this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        this.clientThreads = new ArrayList<>();
    }

    public void start() {
        isRunning = true;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            display("[서버 시작]");
            while (isRunning) {
                Socket socket = serverSocket.accept(); // 클라이언트 요청 수락
                if (!isRunning) break;
                ClientThread clientThread = new ClientThread(socket);
                    clientThreads.add(clientThread);
                    clientThread.start(); // 스레드 시작
            } // while
        } catch (IOException e) {
            display("서버 예외 발생 " + e);
        }
        System.out.println("[서버 종료]");
    }


    private void display(String message) {
        String time = simpleDateFormat.format(new Date()) + " " + message; // HH:mm:ss message
        System.out.println(time);
    }

    class ClientThread extends Thread {
        Socket socket;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        int id; // 고유 아이디
        String userName;
        ChatMessage chatMessage; // 메시지 타입
        boolean keepGoing; // Thread 진행 여부

        public ClientThread(Socket socket) {
            id = ++uniqueId;
            keepGoing = true;
            this.socket = socket;
            display("클라이언트 " + socket.getInetAddress() + ":" + socket.getPort() + " 접속");
            try {
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());
                userName = inputStream.readUTF();
            } catch (IOException e) {
                display(" " + e);
            }
        }
    }
}
