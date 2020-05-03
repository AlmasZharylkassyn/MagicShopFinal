package MainBody;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends Container { //изменить раположение функционала кнопки exit

    private JLabel MainImage;

    private JTextField username;
    private JPasswordField pass;

    private JButton buttonLogin;
    private JButton buttonSignUp;
    private JButton buttonForgot;
    private JButton exit;

    private JRadioButton adminButton;

    private Font font;

    public LoginMenu() {
        setSize(1280, 720);
        setLayout(null);

        font = new Font(null, Font.CENTER_BASELINE, 18);

        username = new JTextField();
        username.setBounds(545, 150, 180, 40);

        username.setFont(font);
        Color textFieldColor = new Color(128, 87, 61);
        username.setForeground(new Color(0, 0, 0));
        username.setBackground(textFieldColor);
        add(username);

        pass = new JPasswordField();
        pass.setBounds(545, 265, 180, 40);
        pass.setFont(font);
        pass.setBackground(textFieldColor);
        pass.setForeground(Color.BLACK);
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

        adminButton = new JRadioButton("ADMIN", false);
        adminButton.setForeground(Color.BLACK);
        //adminButton.setFont(font);
        adminButton.setBounds(1170, 625, 120, 50);
        Color myColor = new Color(94, 42, 9);
        adminButton.setBackground(myColor);
        add(adminButton);

        buttonLogin = new JButton();
        buttonLogin.setBounds(550, 340, 180, 68);
        ImageIcon imageLogin = Main.mf.createIcon("/images/ButtonImageLogin.png");
        buttonLogin.setIcon(imageLogin);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                User user = new User(null, username.getText(), pass.getText(), null, null);
                Data data = new Data("login", user);
                if (!adminButton.isSelected()) {
                    if (!username.getText().equals("admin")) {
                        if (user.getName().equalsIgnoreCase("") || user.getPass().equalsIgnoreCase("")) {
                            dialogError(false);
                        } else {
                            try {
                                //ClientSomthing cs = new ClientSomthing(Main.ipAddr, Main.port, user);
                                Main.login(data);
                            } catch (Exception y) {
                                System.out.println("No connection to server, pls run server");
                                JOptionPane.showMessageDialog(null, "Run server first");
                            }
                        }
                    } else {
                        //System.out.println("kek");
                        dialogError(false);
                    }
                }
                else if (adminButton.isSelected() && username.getText().equals("admin")){
                    if (user.getName().equalsIgnoreCase("") || user.getPass().equalsIgnoreCase("")) {
                        dialogError(false);
                    } else {
                        try {
                            Main.login(data);
                        } catch (Exception y) {
                            System.out.println("No connection to server, pls run server");
                            JOptionPane.showMessageDialog(null, "Run server first");
                        }
                    }
                }
                else if (adminButton.isSelected() && !username.getText().equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Уберите галочку с радиобаттона ADMIN");
                }
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
    }

    public void dialogError(Boolean choice1) {
        if (!username.getText().equals("admin")) {
            if (choice1 == true) {
                JDialog dialog = Main.mf.createDialog("MESSAGE", true, 310, 160);
                JButton b2 = new JButton();
                b2.setBounds(0, 0, 310, 160);
                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        username.setText("");
                        pass.setText("");
                        dialog.dispose();
                        setVisible(false);
                        Main.mf.windowWithTheSeller.setVisible(true);
                    }
                });
                ImageIcon errorImage1 = Main.mf.createIcon("/images/ShablonButtonsBlue.jpg");
                b2.setIcon(errorImage1);
                dialog.add(b2);
                dialog.setVisible(true);
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
            }
        }
        else if (username.getText().equals("admin") && adminButton.isSelected() && choice1) {
            MainAdmin.main(null);
            Main.mf.dispose();
        }
        else if (username.getText().equals("admin") && !adminButton.isSelected()) {
            JOptionPane.showMessageDialog(null,"Click on the radioButton ADMIN first");
        }
        else if (username.getText().equals("admin") && adminButton.isSelected() && !choice1) {
            JOptionPane.showMessageDialog(null, "Неправильный пароль для админа");
        }
    }

}
