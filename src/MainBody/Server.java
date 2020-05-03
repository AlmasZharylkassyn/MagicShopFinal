package MainBody;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
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

    private static Connection connection = Connect.ConnectDb();
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Statement statement;

    private Data data; //объект для передачи данных между сервером и клиентом

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        start();
    }
    @Override
    public void run() {
        //int i = 0;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            data = new Data();
            try {
                while (true) {
                    try {
                        data = (Data) inputStream.readObject();
                        //i++;
                        //System.out.println("принял " + i);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    if (data.getOrder().equalsIgnoreCase("login")) {
                        try {
                            String query = "select * from account where login=? and password=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, data.getUser().getName());
                            preparedStatement.setString(2, data.getUser().getPass());
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                Integer in = resultSet.getInt("id");
                                String qq = resultSet.getString("login");
                                data.getUser().setInteger(in);
                                data.getUser().setName(qq);
                                try {
                                    data.setChoiceOfServer(true);
                                    outputStream.writeObject(data);
                                    outputStream.flush();
                                    preparedStatement.close();
                                    resultSet.close();
                                } catch (IOException ignored) {
                                }
                            } else {
                                try {
                                    data.setChoiceOfServer(false);
                                    outputStream.writeObject(data);
                                    outputStream.flush();
                                } catch (IOException ignored) {
                                }
                            }
                        } catch (SQLException et) {
                            et.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equalsIgnoreCase("ForgotSearch")) {
                        try {
                            String query = "select * from account where Login ='" + data.getUser().getName() + "'" ;
                            Statement statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            if (resultSet.next()){
                                String secretP = resultSet.getString("sec_q");
                                data.setChoiceOfServer(true);
                                data.getUser().setSec_q(secretP);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                            }else {
                                try {
                                    data.setChoiceOfServer(false);
                                    data.getUser().setSec_q("");
                                    outputStream.writeObject(data);
                                    outputStream.flush();
                                    resultSet.close();
                                } catch (IOException ignored) {
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equalsIgnoreCase("ForgotRetrive")) {
                        try {
                            String a2 = data.getUser().getAnswer();
                            String n2 = data.getUser().getName();
                            String query = "select * from Account where answer = '" + a2 + "' and login ='"+ n2 + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.executeQuery();
                            Statement statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            if (resultSet.next()) {
                                String pas = resultSet.getString("password");
                                try {
                                    data.setChoiceOfServer(true);
                                    data.getUser().setPass(pas);
                                    outputStream.writeObject(data);
                                    outputStream.flush();
                                } catch (IOException ignored) {
                                }
                                resultSet.close();
                            } else {
                                try {
                                    data.setChoiceOfServer(false);
                                    outputStream.writeObject(data);
                                    outputStream.flush();
                                } catch (IOException ignored) {
                                }
                                resultSet.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equalsIgnoreCase("Registration")) {
                        try {
                            String name = data.getUser().getName();
                            String pass = data.getUser().getPass();
                            String sec_q = data.getUser().getSec_q();
                            String answer = data.getUser().getAnswer();
                            String query = "select * from account where login=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, name);
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                data.setChoiceOfServer(false);
                                outputStream.writeObject(data);
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
                                    data.setChoiceOfServer(true);
                                    outputStream.writeObject(data);
                                    outputStream.flush();
                                    resultSet.close();
                                    preparedStatement.close();
                                } catch (Exception eg) {
                                    eg.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equals("addProduct")) {
                        try {
                            String query = "select * from product where name=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, data.getProduct().getName());
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                data.setChoiceOfServer(false);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            }
                            else {
                                try {
                                    String query1 = "insert into product (Name, amount, price) values (?,?,?)";
                                    preparedStatement = connection.prepareStatement(query1);
                                    preparedStatement.setString(1, data.getProduct().getName());
                                    preparedStatement.setInt(2, data.getProduct().getAmount());
                                    preparedStatement.setInt(3, data.getProduct().getPrice());
                                    preparedStatement.execute();
                                    data.setChoiceOfServer(true);
                                    outputStream.writeObject(data);
                                    outputStream.flush();
                                    resultSet.close();
                                    preparedStatement.close();
                                } catch (Exception eg) {
                                    eg.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("addprod 201 line server");
                        }
                    }
                    else if (data.getOrder().equals("searchTopUp")) {
                        try {
                            String query = "select * from product where name=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, data.getProduct().getName());
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {

                                Integer amount = resultSet.getInt("amount");
                                data.getProduct().setAmount(amount);
                                data.setChoiceOfServer(true);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            }
                            else {
                                data.setChoiceOfServer(false);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equals("topUpProduct")) {
                        try{
                            String query = "select * from product where name=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, data.getProduct().getName());
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                Integer id1 = resultSet.getInt("id");
                                //System.out.println(id1);
                                statement = connection.createStatement();
                                Integer totalAmount = resultSet.getInt("amount");
                                try {
                                    totalAmount += +data.getProduct().getAmount();
                                    if (totalAmount >= 0) {
                                        statement.execute("UPDATE product SET amount =" + totalAmount + " WHERE id =" + id1);
                                        data.getProduct().setAmount(totalAmount);
                                        data.setChoiceOfServer(true);
                                        outputStream.writeObject(data);
                                        outputStream.flush();
                                        statement.close();
                                    }
                                    else {
                                        data.setChoiceOfServer(false);
                                        outputStream.writeObject(data);
                                        outputStream.flush();
                                        statement.close();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    data.setChoiceOfServer(false);
                                    outputStream.writeObject(data);
                                    outputStream.flush();
                                }
                            }
                            else {
                                data.setChoiceOfServer(false);
                                outputStream.writeObject(data);
                                outputStream.flush();
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equals("listProducts")) {
                        ArrayList<Product> list = new ArrayList<>();
                        try {
                            PreparedStatement ps = connection.prepareStatement("SELECT * from product");
                            ResultSet rs = ps.executeQuery();
                            while(rs.next()){
                                list.add(new Product((long) rs.getInt("id"), rs.getString("name"), rs.getInt("amount"), rs.getInt("price")));
                            }
                            ps.close();
                            data.setProducts(list);
                            outputStream.writeObject(data);
                            outputStream.flush();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            data.setProducts(null);
                            outputStream.writeObject(data);
                            outputStream.flush();
                        }
                    }
                    else if (data.getOrder().equals("deleteProduct")) {
                        try {
                            statement = connection.createStatement();
                            String query = "delete from product where id= " + data.getInteger();
                            statement.executeUpdate(query);
                            statement.close();
                            data.setChoiceOfServer(true);
                            outputStream.writeObject(data);
                            outputStream.flush();
                        } catch (Exception e) {
                            data.setChoiceOfServer(false);
                            outputStream.writeObject(data);
                            outputStream.flush();
                            e.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equals("checkCash")) {
                        try {
                            String query = "select * from account where login=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, data.getUser().getName());
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                Integer amount = resultSet.getInt("cash");
                                data.getUser().setCash(amount);
                                //System.out.println(data.getUser().getCash());
                                data.setChoiceOfServer(true);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            }
                            else {
                                data.setChoiceOfServer(false);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equals("buyProduct")) {
                        try {
                            String query = "select * from product where id=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, data.getInteger());
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                Integer amount = resultSet.getInt("amount");
                                Integer price = resultSet.getInt("price");
                                Integer totalAmount = data.getProduct().getAmount();
                                Integer cash = data.getUser().getCash();
                                int itogCash = cash - price * totalAmount;
                                int itogAmount = amount - totalAmount;
                                Integer id1 = data.getInteger();
                                if (itogAmount >= 0 && itogCash >= 0) {
                                    //System.out.println("kek");
                                    try {
                                        statement = connection.createStatement();
                                        statement.execute("UPDATE product SET amount =" + itogAmount + " WHERE id =" + id1);
                                        data.setChoiceOfServer(true);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                        data.setChoiceOfServer(false);
                                    }
                                    //statement.close();
                                    try {
                                        //String query = "select * from product where id=?";
                                        statement = connection.createStatement();
                                        //System.out.println(data.getUser().getId());
                                        Integer id2 = data.getUser().getInteger();
                                        //System.out.println(id2);
                                        statement.execute("UPDATE account SET cash =" + itogCash + " WHERE id =" + id2);
                                        data.setChoiceOfServer(true);
                                        statement.close();
                                        resultSet.close();
                                        preparedStatement.close();
                                    } catch (Exception ea) {
                                        ea.printStackTrace();
                                        data.setChoiceOfServer(false);
                                    }
                                    data.getUser().setCash(itogCash);
                                    data.getProduct().setAmount(itogAmount);
                                }
                                else if (itogAmount >=0 && itogCash < 0){
                                    //System.out.println();
                                    data.setOrder("NotEnoughCash");
                                    data.setChoiceOfServer(false);
                                }
                                else if (itogAmount < 0 && itogCash >= 0) {
                                    data.setOrder("NotEnoughAmountOfProduct");
                                    data.setChoiceOfServer(false);
                                }
                                else if (itogAmount < 0 && itogCash < 0) {
                                    data.setOrder("NotEnoughCash");
                                    data.setChoiceOfServer(false);
                                }
                                outputStream.writeObject(data);
                                outputStream.flush();
                                //statement.close();
                                preparedStatement.close();
                                resultSet.close();
                                //System.out.println(id1);
                            }
                            else {
                                data.setChoiceOfServer(false);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equals("searchAddCash")) {
                        try {
                            String query = "select * from account where login=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, data.getUser().getName());
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                Integer amount = resultSet.getInt("cash");
                                data.getUser().setCash(amount);
                                data.setChoiceOfServer(true);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            }
                            else {
                                data.setChoiceOfServer(false);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if (data.getOrder().equals("adminAddCash")) {
                        try {
                            String query = "select * from account where login=?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, data.getUser().getName());
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                //System.out.println("kek1");
                                Integer amount = resultSet.getInt("cash");
                                Integer amount2 = data.getInteger();
                                Integer id1 = resultSet.getInt("id");
                                Integer totalAmount = amount + amount2;
                                if (totalAmount >= 0) {
                                    try {
                                        statement = connection.createStatement();
                                        statement.execute("UPDATE account SET cash =" + totalAmount + " WHERE id =" + id1);
                                        data.setChoiceOfServer(true);
                                        data.setInteger(totalAmount);
                                    } catch (SQLException ea) {
                                        ea.printStackTrace();
                                    }
                                }
                                else {
                                    data.setChoiceOfServer(false);
                                }
                                outputStream.writeObject(data);
                                outputStream.flush();
                                preparedStatement.close();
                                resultSet.close();
                                //System.out.println(id1);
                            }
                            else {
                                //System.out.println("kek2");
                                data.setChoiceOfServer(false);
                                outputStream.writeObject(data);
                                outputStream.flush();
                                resultSet.close();
                                preparedStatement.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    /**
                     * Харакири сервера
                    if(data.getUser().getName().equals("stop") && data.getUser().getPass().equals("stop")) {
                        this.downService();
                        break;
                    }
                     */
                }
            } catch (NullPointerException ignored) {}
        } catch (IOException e) {
            System.out.println("Client lost");
        }
    }

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
            System.out.println("Server is down");
        } catch (IOException ignored) {}
    }
}
