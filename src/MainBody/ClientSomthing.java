package MainBody;

import java.io.*;
import java.net.Socket;

class ClientSomthing {

    private Socket socket;
    private BufferedReader inputUser; // поток чтения с консоли
    private String addr; // ip адрес клиента
    private int port; // порт соединения
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private User user1;

    public ClientSomthing(String addr, int port, User userData) {
        this.addr = addr;
        this.port = port;
        this.user1 = userData;

        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }

        try {
            // потоки чтения из сокета / записи в сокет, и чтения с консоли
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            WriteObj writeObj = new WriteObj();
            writeObj.start(); // нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле
        } catch (IOException e) {
            // Сокет должен быть закрыт при любой
            // ошибке, кроме ошибки конструктора сокета:
            ClientSomthing.this.downService();
        }
        // В противном случае сокет будет закрыт
        // в методе run() нити.
    }

    public class WriteObj extends Thread {
        @Override
        public void run() {
            //while (true) {
            if (user1.getName().equalsIgnoreCase("stop")) {
                ClientSomthing.this.downService();
            }
                try {
                    outputStream.writeObject(user1); //отправка на сервер объект User
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    User user2 = (User) inputStream.readObject(); //принятие объекта с сервера

                    if (user2.getChoice() == true && user2.getChoose().equalsIgnoreCase("login")) {
                        Main.mf.loginMenu.dialogError(true);
                        //ClientSomthing.this.downService();
                        //break;
                    } else if (user2.getChoice() == false && user2.getChoose().equalsIgnoreCase("login")) {
                        Main.mf.loginMenu.dialogError(false);
                        //break;
                    } else if (user2.getChoice() == false && user2.getChoose().equalsIgnoreCase("ForgotSearch")) {
                        Main.mf.forgotWindow.dialog1(false, "");
                        //break;
                    } else if (user2.getChoice() == true && user2.getChoose().equalsIgnoreCase("ForgotSearch")) {
                        try {
                            Main.mf.forgotWindow.dialog1(true, user2.getOrder());
                        } catch (NullPointerException ea) {
                            System.out.println("Clientsomthing 72 строка");
                        }
                        //break;
                    } else if (user2.getChoice() == true && user2.getChoose().equalsIgnoreCase("ForgotRetrive")) {
                        Main.mf.forgotWindow.dialog2(true, user2.getPass());
                        //break;
                    } else if (user2.getChoice() == false && user2.getChoose().equalsIgnoreCase("ForgotRetrive")) {
                        Main.mf.forgotWindow.dialog2(false, "");
                        //break;
                    } else if (user2.getChoice() == true && user2.getChoose().equalsIgnoreCase("Registration")) {
                        Main.mf.signUpWindow.signUpButton(true);
                    } else if (user2.getChoice() == false && user2.getChoose().equalsIgnoreCase("Registration")) {
                        Main.mf.signUpWindow.signUpButton(false);
                    }
                    ClientSomthing.this.downService();
                    //break;
                    //ClientSomthing.this.downService();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            //}
        }
    }

    /**
     * закрытие сокета
     */

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                System.out.println("Socket is closed");
                //in.close();
                //out.close();
                outputStream.close();
                inputStream.close();
            }
        } catch (IOException ignored) {}
    }

    // нить чтения сообщений с сервера
    /*
    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine(); // ждем сообщения с сервера
                    if (str.equals("stop")) {
                        ClientSomthing.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    }
                    System.out.println(str); // пишем сообщение с сервера на консоль
                }
            } catch (IOException e) {
                ClientSomthing.this.downService();
            }
        }
    }*/
    // нить отправляющая сообщения приходящие с консоли на сервер
    /*public class WriteMsg extends Thread {
        //public String userData;
        public User user1;
        public String choose;

        public void setUser1(User user1) {
            this.user1 = user1;
        }

        public void setChoose(String choose) {
            this.choose = choose;
        }

        @Override
        public void run() {
            //System.out.println(user1);
            while (true) {
                //String userWord;
                try {
                    //User user = new User(null ,"Almas", "153759","111", "Kz");
                    String s ="";
                    s += choose + " ";
                    s += String.valueOf(user1);

                    if (String.valueOf(s).equals("stop")) {
                        out.write("stop" + "\n");
                        ClientSomthing.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    } else {
                       // out.write( + "\n"); // отправляем на сервер
                        out.write(String.valueOf(s) + "\n");
                    }
                    out.flush(); // чистим
                } catch (IOException e) {
                    ClientSomthing.this.downService(); // в случае исключения тоже харакири

                }

                String str;
                try {
                    while (true) {
                        str = in.readLine(); // ждем сообщения с сервера
                        String temp[];
                        String del = " ";
                        temp = str.split(del);
                        //System.out.println(temp[0]);
                        //if (str.equals("stop")) {
                          //  ClientSomthing.this.downService(); // харакири
                            //break; // выходим из цикла если пришло "stop"
                        //}
                        if (str.equalsIgnoreCase("Right") && choose.equalsIgnoreCase("login")) {
                            //Main.mf.loginMenu.setVisible(false);
                            //Main.mf.windowWithTheSeller.setVisible(true);
                            //Main.mf.loginMenu.choice = true;
                            Main.mf.loginMenu.dialogError(true);

                        }
                        else if (str.equalsIgnoreCase("NotRight") && choose.equalsIgnoreCase("login")) {
                            //Main.mf.loginMenu.choice = false;
                            choice = false;


                            Main.mf.loginMenu.dialogError(false);
                        }

                        else if (temp[0].equalsIgnoreCase("NotRight") && choose.equalsIgnoreCase("ForgotSearch")) {
                            //System.out.println("kek1");
                            System.out.println("raz");
                            Main.mf.forgotWindow.dialog1(false, "");
                        }
                        else if (!temp[0].equalsIgnoreCase("NotRight") && choose.equalsIgnoreCase("ForgotSearch")) {
                            //System.out.println("kek1");
                            //String sec_q ="";
                            //for (int i = 1; i < temp.length; i++) {
                             //   sec_q += temp[i] + " ";
                            //}
                            System.out.println(str);
                            try {
                                Main.mf.forgotWindow.dialog1(true, str);
                            } catch (NullPointerException ea) {
                                System.out.println("kekError");
                            }
                            System.out.println("notkke");
                        }

                        System.out.println(str); // пишем сообщение с сервера на консоль
                        break;
                    }
                    break;
                } catch (IOException e) {
                    ClientSomthing.this.downService();
                }

            }
        }
    }*/
}
