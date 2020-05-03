package MainBody;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainAdmin {
    public static AdminFrame af;
    public static String ipAddr = "localhost";
    public static int port = 8080;
    public static Socket socket;
    public static ObjectOutputStream outputStream;
    public static ObjectInputStream inputStream;
    public static User userData;

    private static String myLogin;
    private static String myPassword;
    public static void main(String[] args) {
        connectServer();
        af = new AdminFrame();
        af.setVisible(true);
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
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                af.adminPage1.dialogError(true);
                myLogin = data.getUser().getName();
                myPassword = data.getUser().getPass();
            }
            else {
                af.adminPage1.dialogError(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(Data data) {
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                af.adminAddProduct.dialogError(true);
            }
            else {
                af.adminAddProduct.dialogError(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchTopUp(Data data) {
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                af.adminTopUpProduct.dialog1(true, data.getProduct().getAmount());
            }
            else {
                af.adminTopUpProduct.dialog1(false, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void topUpProduct(Data data) {
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                af.adminTopUpProduct.dialog2(true, data.getProduct().getAmount());
            }
            else {
                af.adminTopUpProduct.dialog2(false, null);
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
            af.adminDeleteProduct.listProducts(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteProduct(Data data) {
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                af.adminDeleteProduct.deleteProduct(true);
            }
            else {
                af.adminDeleteProduct.deleteProduct(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchAddCash(Data data) {
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer()) {
                af.adminChangeCash.dialog1(true, data.getUser().getCash());
            }
            else {
                af.adminChangeCash.dialog1(false, null);
            }
        } catch (Exception e) {

        }
    }

    public static void adminAddCash(Data data) {
        try {
            outputStream.writeObject(data);
            data = (Data) inputStream.readObject();
            if (data.getChoiceOfServer())
                af.adminChangeCash.dialog2(true, data.getInteger());
            else
                af.adminChangeCash.dialog2(false, null);
        } catch (Exception e) {
            e.printStackTrace();
            af.adminChangeCash.dialog2(false, null);
        }
    }
}
