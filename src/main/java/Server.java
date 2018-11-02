import beans.Course;
import beans.Teacher;
import org.json.JSONObject;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final int PORT = 12345;

    public static void main(String[] args) {
        new Server().init();
    }

    public void init(){
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true){
                Socket client = serverSocket.accept();
                new Thread(new ClientHandler(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器异常:"+e.getMessage());
        }
    }

    private class ClientHandler implements Runnable{
        private Socket socket;
        public ClientHandler(Socket socket){
            this.socket = socket;
        }
        public void run() {
            try {
                InputStream inStram = socket.getInputStream();
                OutputStream outStream = socket.getOutputStream();
                System.out.println("客户端已连接");
                StringBuilder request = new StringBuilder();
                Scanner input = new Scanner(inStram);
                while (input.hasNextLine()){
                    request.append(input.nextLine());
                }
                System.out.println("客户端请求："+request.toString());
                JSONObject jsonObject = new JSONObject(request.toString());
                JSONObject teacherObj = jsonObject.getJSONObject("teacher");
                JSONObject netObj = jsonObject.getJSONObject("net");
                Teacher teacher = new Teacher();
                teacher.setName(teacherObj.getString("teacher"));
                teacher.setNumber(teacherObj.getString("teacherNum"));
                String cookie = netObj.getString("cookies");
                System.out.println("开始获取课程");
                Course course = new MainServer().getCourse(teacher,cookie.substring(0,cookie.length()-8),
                        netObj.getString("verification"));
                System.out.println("返回数据为："+course.toString());
                JSONObject courseObj = new JSONObject();
                courseObj.put("teacher",course.getTeacher());
                courseObj.put("teacherNum",course.getTeacherNum());
                courseObj.put("text",course.getText());
                String response = courseObj.toString();
                PrintWriter out = new PrintWriter(outStream);
                String len = String.format("%08d", response.getBytes().length);
                out.print(len+response);
                out.flush();
            } catch (IOException e) {
                System.out.println("服务器 run 异常: " + e.getMessage());
            }  finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }
}
