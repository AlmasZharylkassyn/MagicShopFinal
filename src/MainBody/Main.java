package MainBody;

import com.sun.security.jgss.GSSUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;

public class Main {
    public static MainFrame mf;
    private static String ipAddr = "localhost";
    private static int port = 8080;
    private static Socket socket;
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;
    private static User userData;

    private static String myLogin;
    private static String myPassword;
    private static Integer myId;

    public static void main(String[] args) {
        connectServer();
        mf = new MainFrame();
        mf.setVisible(true);
    }
    public static void connectServer() {
        try{
            socket = new Socket(ipAddr, port);
            outputStream=new ObjectOutputStream(socket.getOutputStream());
            inputStream=new ObjectInputStream((socket.getInputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void login(Data data) {
        //System.out.println("Ura");

        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            myId = data.getUser().getInteger();
            myLogin = data.getUser().getName();
            if (data.getChoiceOfServer()) {
                mf.loginMenu.dialogError(true);
                myLogin = data.getUser().getName();
                myPassword = data.getUser().getPass();
            }
            else {
                mf.loginMenu.dialogError(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void forgotSearch(Data data) {
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                //System.out.println(user.getSec_q() + "kek");
                mf.forgotWindow.dialog1(true, data.getUser().getSec_q());
            }
            else {
                mf.forgotWindow.dialog1(false, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void forgotRetrive(Data data) {
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                mf.forgotWindow.dialog2(true, data.getUser().getPass());
            }
            else {
                mf.forgotWindow.dialog2(false, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void registration(Data data) {
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                mf.signUpWindow.signUpButton(true);
            }
            else {
                mf.signUpWindow.signUpButton(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listProducts() {
        Data data = new Data();
        data.setOrder("listProducts");
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            ArrayList<Product> products = data.getProducts();
            mf.windowWithTheProduct.listProducts(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void checkCash() {
        Data data = new Data();
        data.setOrder("checkCash");
        data.setUser(new User(null, myLogin, null, null, null, null));
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                mf.windowWithTheProduct.checkCash(data.getUser().getCash());
            }
            else {
                mf.windowWithTheProduct.checkCash(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void buyProduct(Data data) {
        if (data != null) {
            try {
                data.getUser().setName(myLogin);
                data.getUser().setInteger(myId);
                outputStream.writeObject(data);
                data = (Data) inputStream.readObject();
                mf.windowWithTheProduct.dialog1(data.getChoiceOfServer(), data.getOrder(), data.getUser().getCash());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            mf.windowWithTheProduct.dialog1(false, null, null);
        }
    }

}
