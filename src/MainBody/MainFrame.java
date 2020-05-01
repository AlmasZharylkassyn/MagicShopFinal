package MainBody;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainFrame extends JFrame {
    public static LoginMenu loginMenu;
    public static WindowWithTheSeller windowWithTheSeller;
    public static SignUpWindow signUpWindow;
    public static ForgotWindow forgotWindow;

    public MainFrame() {
        setSize(1280, 720);
        setTitle("Magic Shop Version-0.3 Include sockets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Image img = new ImageIcon(MainFrame.class.getResource("/images/xia-pu-new.png")).getImage(); //MainIcon.png
            this.setIconImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //setLayout(null);

        loginMenu = new LoginMenu();
        loginMenu.setLocation(0, 0);
        add(loginMenu);

        windowWithTheSeller = new WindowWithTheSeller();
        windowWithTheSeller.setLocation(0, 0);
        windowWithTheSeller.setVisible(false);
        add(windowWithTheSeller);

        signUpWindow = new SignUpWindow();
        signUpWindow.setLocation(0, 0);
        signUpWindow.setVisible(false);
        add(signUpWindow);

        forgotWindow = new ForgotWindow();
        forgotWindow.setLocation(0, 0);
        forgotWindow.setVisible(false);
        add(forgotWindow);

        //loginMenuImage();

        //Image img1 = new ImageIcon(MainFrame.class.getResource("LoginMenuImage.png")).getImage();

        //setContentPane(new JLabel(new ImageIcon(img1)));

        //setLayout(new FlowLayout());

        //JLabel label1 = new JLabel("Test");
        //add(label1);
        //setVisible(true);
        //setTitle("This is my Title");
        setLayout(null);
    }

    public void loginMenuImage() {
        Image img1 = new ImageIcon(MainFrame.class.getResource("LoginMenuImageNew.png")).getImage();

        setContentPane(new JLabel(new ImageIcon(img1)));

        setLayout(new FlowLayout());
    }

    public JDialog createDialog(String title, boolean modal, int width, int height) {
        try {
            JDialog dialog = new JDialog(Main.mf, title, modal);
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
        URL imgURL = LoginMenu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("File not found " + path);
            return null;
        }
    }
}