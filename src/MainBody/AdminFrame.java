package MainBody;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AdminFrame extends JFrame {

    public static AdminPage1 adminPage1;
    public static AdminPage2 adminPage2;
    public static AdminAddProduct adminAddProduct;
    public static AdminTopUpProduct adminTopUpProduct;
    public static AdminDeleteProduct adminDeleteProduct;
    public static AdminChangeCash adminChangeCash;

    public AdminFrame() {
        setSize(768, 432);
        setTitle("ADMIN PAGE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        try {
            Image img = new ImageIcon(MainFrame.class.getResource("/images/iconAdmin.png")).getImage(); //MainIcon.png
            this.setIconImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }

        adminPage1 = new AdminPage1();
        adminPage1.setLocation(0, 0);
        //adminPage1.setVisible(false); ////////////// убрать
        add(adminPage1);

        adminPage2 = new AdminPage2();
        adminPage2.setLocation(0, 0);
        adminPage2.setVisible(false);
        add(adminPage2);

        adminAddProduct = new AdminAddProduct();
        adminAddProduct.setLocation(0, 0);
        adminAddProduct.setVisible(false);
        add(adminAddProduct);

        adminTopUpProduct = new AdminTopUpProduct();
        adminTopUpProduct.setLocation(0, 0);
        adminTopUpProduct.setVisible(false);
        add(adminTopUpProduct);

        adminDeleteProduct = new AdminDeleteProduct();
        adminDeleteProduct.setLocation(0, 0);
        adminDeleteProduct.setVisible(false);
        add(adminDeleteProduct);

        adminChangeCash = new AdminChangeCash();
        adminChangeCash.setLocation(0, 0);
        adminChangeCash.setVisible(false);
        add(adminChangeCash);
    }
    public JDialog createDialog(String title, boolean modal, int width, int height) {
        try {
            JDialog dialog = new JDialog(MainAdmin.af, title, modal);
            dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            dialog.setLocation(550, 350);
            dialog.setSize(width, height);
            return dialog;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon createIcon(String path) {
        URL imgURL = AdminPage1.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("File not found " + path);
            return null;
        }
    }
}
