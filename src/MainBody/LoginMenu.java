package MainBody;

//import com.mysql.jdbc.Connection;

import com.mysql.jdbc.log.Log;
import java.sql.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class LoginMenu extends Container { //изменить раположение функционала кнопки exit

    private JLabel MainImage;

    private JTextField username;
    private JPasswordField pass;

    private JButton buttonLogin;
    private JButton buttonSignUp;
    private JButton buttonForgot;
    private JButton exit;

    private Font font;

    public LoginMenu() {
        setSize(1280, 720);
        setLayout(null);

        font = new Font(null, Font.CENTER_BASELINE, 18);

        username = new JTextField();
        username.setBounds(545, 150, 180, 40);

        username.setFont(font);
        //ImageIcon imageUsername = Main.mf.createIcon("ForUsername.png");
        //JLabel tempLabel = new JLabel();
        //tempLabel.setBounds(550, 100, 180, 68);
        //tempLabel.setIcon(imageUsername);
        //add(tempLabel);
        username.setBackground(Color.LIGHT_GRAY);
        add(username);

        pass = new JPasswordField();
        pass.setBounds(545, 265, 180, 40);
        pass.setFont(font);
        pass.setBackground(Color.LIGHT_GRAY);
        add(pass);

        exit = new JButton();
        exit.setBounds(550, 500, 180, 68);
        ImageIcon imageExit = Main.mf.createIcon("/images/ButtonImageExit.png");
        exit.setIcon(imageExit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exit);

        buttonLogin = new JButton();
        buttonLogin.setBounds(550, 340, 180, 68);
        ImageIcon imageLogin = Main.mf.createIcon("/images/ButtonImageLogin.png");
        buttonLogin.setIcon(imageLogin);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                User user = new User(null, username.getText(), pass.getText(), null, null);
                user.setChoose("login");
                if (user.getName().equalsIgnoreCase("") || user.getPass().equalsIgnoreCase("")) {
                    dialogError(false);
                }
                else {
                    try {
                        ClientSomthing cs = new ClientSomthing(Main.ipAddr, Main.port, user);
                    } catch (Exception y) {
                        System.out.println("No connection to server, pls run server");
                        JOptionPane.showMessageDialog(null, "Run server first");
                    }
                }
                /*
                try{

                    String query = "select * from account where login=? and password=?";

                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,username.getText());

                    preparedStatement.setString(2,pass.getText());

                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()){
                        resultSet.close();

                        preparedStatement.close();

                        username.setText("");
                        pass.setText("");
                        setVisible(false);
                        Main.mf.windowWithTheSeller.setVisible(true);

                    } else {
                        //JOptionPane.showMessageDialog(null, "Incorrect login or password");
                        JDialog dialog = Main.mf.createDialog("MESSAGE", true, 310, 160);

                        JButton b2 = new JButton();
                        b2.setBounds(0, 0, 310, 160);
                        b2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                            }
                        });

                        ImageIcon errorImage2 = Main.mf.createIcon("ErrorDialogFon.png");
                        b2.setIcon(errorImage2);

                        dialog.add(b2);
                        dialog.setVisible(true);
                    }
                }catch (Exception et){
                    et.printStackTrace();
                }
                */
            }
        });
        add(buttonLogin);

        buttonSignUp = new JButton();
        buttonSignUp.setBounds(660, 420, 180, 68);
        ImageIcon imageSignUp = Main.mf.createIcon("/images/ButtonImageSignUp.png");
        buttonSignUp.setIcon(imageSignUp);
        buttonSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.mf.loginMenu.setVisible(false);
                Main.mf.signUpWindow.setVisible(true);
            }
        });
        add(buttonSignUp);

        buttonForgot = new JButton();
        buttonForgot.setBounds(450, 420, 180, 68);
        ImageIcon imageForgot = Main.mf.createIcon("/images/ButtonImageForgot.png");
        buttonForgot.setIcon(imageForgot);
        buttonForgot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.mf.loginMenu.setVisible(false);
                Main.mf.forgotWindow.setVisible(true);
                try {
                    JDialog firstDialog = Main.mf.createDialog("Librarian", true, 400, 250);
                    JButton buttonFirstDialog = new JButton();
                    buttonFirstDialog.setBounds(0, 0, 400, 250);
                    ImageIcon FirstDialogImage = Main.mf.createIcon("/images/LibraryDialogFirst.png");
                    buttonFirstDialog.setIcon(FirstDialogImage);
                    buttonFirstDialog.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            firstDialog.dispose();
                        }
                    });
                    firstDialog.add(buttonFirstDialog);
                    firstDialog.setLocation(400, 300);
                    firstDialog.setVisible(true);
                } catch (Exception ea) {
                    ea.printStackTrace();
                }
            }
        });
        add(buttonForgot);


        ImageIcon imageIcon = Main.mf.createIcon("/images/LoginMenuImageNewTemp2.png"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, 0, 1280, 720);
        MainImage.setIcon(imageIcon);
        add(MainImage);

        //Main.mf.MainMenu();

       // JButton jButton = new JButton("kek");
        //jButton.setBounds(20, 20, 100, 100);
        //add(jButton);


        //Image img1 = new ImageIcon(MainFrame.class.getResource("LoginMenuImage.png")).getImage();

        //Main.mf.setContentPane(new JLabel(new ImageIcon(img1)));

        //setLayout(new FlowLayout());
    }

    public void dialogError(Boolean choice1) {
        if(choice1 == true) {
            username.setText("");
            pass.setText("");
            setVisible(false);
            Main.mf.windowWithTheSeller.setVisible(true);
        }
        else if (choice1 == false) {
            JDialog dialog = Main.mf.createDialog("MESSAGE", true, 310, 160);

            JButton b2 = new JButton();
            b2.setBounds(0, 0, 310, 160);
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });

            ImageIcon errorImage2 = Main.mf.createIcon("/images/ErrorDialogFon.png");
            b2.setIcon(errorImage2);

            dialog.add(b2);
            dialog.setVisible(true);

           //JOptionPane.showMessageDialog(dialog, "");
        }
    }

}
