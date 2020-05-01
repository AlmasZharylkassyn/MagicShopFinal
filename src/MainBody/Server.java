package MainBody;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.LinkedList;

public class Server {
    public static final int PORT = 8080;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException{

        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server started");
        try {
            while (true) {
                System.out.println("Waiting for client");
                Socket socket = server.accept();
                System.out.println("Client accept");
                try {
                    serverList.add(new ServerSomthing(socket));
                } catch (IOException e) {
                    //e.printStackTrace();
                    System.out.println("serverList.add error line 19 (Server)");
                }
            }
        }
        finally {
            server.close();
        }
    }
}

class ServerSomthing extends Thread {

    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private static Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    /**
     * для общения с клиентом необходим сокет (адресные данные)
     * @param socket
     * @throws IOException
     */

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        start();
    }
    @Override
    public void run() {
        connection = Connect.ConnectDb();
        //String word;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            // первое сообщение отправленное сюда - это никнейм
            /*word = in.readLine();
            try {
                out.write(word + "\n");
                out.flush(); // flush() нужен для выталкивания оставшихся данных
                // если такие есть, и очистки потока для дьнейших нужд
            } catch (IOException ignored) {}
            */
            User user = new User();
            try {
                while (true) {
                    try {
                        user = (User) inputStream.readObject();
                        //System.out.println(user);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    if (user.getChoose().equalsIgnoreCase("login")) {
                        try {
                            String query = "select * from account where login=? and password=?";

                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, user.getName());

                            preparedStatement.setString(2, user.getPass());

                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                resultSet.close();
                                System.out.println("Success SQL");
                                preparedStatement.close();
                                try {
                                    user.setChoice(true);
                                    outputStream.writeObject(user);
                                    outputStream.flush();
                                } catch (IOException ignored) {
                                }
                            } else {
                                System.out.println("Not Success SQL");
                                try {
                                    user.setChoice(false);
                                    outputStream.writeObject(user);
                                    outputStream.flush();
                                } catch (IOException ignored) {
                                }
                            }
                        } catch (SQLException et) {
                            et.printStackTrace();
                        }
                    }
                    else if (user.getChoose().equalsIgnoreCase("ForgotSearch")) {
                        try {
                            String query = "select * from account where Login ='" + user.getName() + "'" ;
                            Statement statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            if (resultSet.next()){
                                System.out.println("Success SQL");
                                String secretP = resultSet.getString("sec_q");
                                user.setChoice(true);
                                user.setOrder(secretP);
                                outputStream.writeObject(user);
                                outputStream.flush();
                                resultSet.close();
                            }else {
                                System.out.println("Not Success SQL");
                                try {
                                    user.setChoice(false);
                                    user.setOrder("");
                                    outputStream.writeObject(user);
                                    outputStream.flush();
                                    resultSet.close();
                                } catch (IOException ignored) {
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (user.getChoose().equalsIgnoreCase("ForgotRetrive")) {
                        try {
                            String a2 = user.getAnswer();
                            String n2 = user.getName();
                            String query = "select * from Account where answer = '" + a2 + "' and login ='"+ n2 + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.executeQuery();

                            Statement statement = connection.createStatement();

                            resultSet = statement.executeQuery(query);

                            if (resultSet.next()) {
                                System.out.println("Success SQL");
                                String pas = resultSet.getString("password");
                                try {
                                    user.setChoice(true);
                                    user.setPass(pas);
                                    outputStream.writeObject(user);
                                    outputStream.flush();
                                } catch (IOException ignored) {
                                }
                                resultSet.close();
                            } else {
                                System.out.println("Not Success SQL");
                                try {
                                    user.setChoice(false);
                                    outputStream.writeObject(user);
                                    outputStream.flush();
                                } catch (IOException ignored) {
                                }
                                resultSet.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (user.getChoose().equalsIgnoreCase("Registration")) {
                        try {
                            String name = user.getName();
                            String pass = user.getPass();
                            String sec_q = user.getSec_q();
                            String answer = user.getAnswer();
                            String query = "select * from account where login=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, name);
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                user.setChoice(false);
                                outputStream.writeObject(user);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            } else {
                                try {
                                    String query1 = "insert into Account (Login, password, sec_q, answer) values (?,?,?,?)";
                                    preparedStatement = connection.prepareStatement(query1);
                                    preparedStatement.setString(1, name);
                                    preparedStatement.setString(2, pass);
                                    preparedStatement.setString(3, sec_q);
                                    preparedStatement.setString(4, answer);
                                    preparedStatement.execute();
                                    user.setChoice(true);
                                    outputStream.writeObject(user);
                                    outputStream.flush();
                                    resultSet.close();
                                    preparedStatement.close();
                                    System.out.println("Success SQL");
                                } catch (Exception eg) {
                                    eg.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Registration no success");
                        }
                    }
                    if(user.getName().equals("stop")) {
                        this.downService(); // харакири
                        break; // если пришла пустая строка - выходим из цикла прослушки
                    }
                }
            } catch (NullPointerException ignored) {}
        } catch (IOException e) {
            //this.downService();
            System.out.println("Client lost");
        }
    }
    /**
     * закрытие сервера
     * прерывание себя как нити и удаление из списка нитей
     */
    private void downService() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                //in.close();
                //out.close();
                outputStream.close();
                inputStream.close();
                for (ServerSomthing vr : Server.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }
}
