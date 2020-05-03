package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage1 extends Container {

    private Font font;

    private JLabel MainImage;

    private JTextField username;
    private JPasswordField pass;

    private JLabel adminLabel;

    private JLabel usernameL;
    private JLabel passL;

    private JButton loginButton;
    private JButton exit;

    public AdminPage1() {
        setSize(768, 432);
        setLayout(null);

        font = new Font(null, Font.CENTER_BASELINE, 60);

        adminLabel = new JLabel("Admin Page");
        adminLabel.setForeground(Color.GREEN);
        adminLabel.setBounds(210, 0, 500, 100);
        adminLabel.setFont(font);
        add(adminLabel);

        font = new Font(null, Font.BOLD, 15);

        usernameL = new JLabel("Username");
        usernameL.setForeground(Color.GREEN);
        usernameL.setFont(font);
        usernameL.setBounds(190, 100, 80, 40);
        add(usernameL);

        passL = new JLabel("Password");
        passL.setForeground(Color.GREEN);
        passL.setFont(font);
        passL.setBounds(190, 165, 80, 40);
        add(passL);

        font = new Font(null, Font.BOLD, 20);

        username = new JTextField();
        username.setBounds(275, 100, 220, 40);
        username.setFont(font);
        username.setBackground(Color.LIGHT_GRAY);
        add(username);

        pass = new JPasswordField();
        pass.setBounds(275, 165, 220, 40);
        pass.setFont(font);
        pass.setBackground(Color.LIGHT_GRAY);
        add(pass);

        font = new Font(null, Font.BOLD, 30);

        loginButton = new JButton("LOGIN");
        loginButton.setFont(font);
        loginButton.setBounds(295, 220, 180, 68);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User(null, username.getText(), pass.getText(), null, null);
                //user.setChoose("login");
                Data data = new Data("login", user);
                //user.setOrder("ADMIN");
                if (username.getText().equals("admin")) {
                    MainAdmin.login(data);
                }
                else {
                    dialogError(false);
                }
                //setVisible(false);
                //MainAdmin.af.adminPage2.setVisible(true);
            }
        });
        add(loginButton);

        exit = new JButton("EXIT");
        exit.setFont(font);
        exit.setBounds(295, 300, 180, 68);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exit);

        ImageIcon imageIcon = MainAdmin.af.createIcon("/images/fonAdmin2.jpg"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, -10, 768, 432);
        MainImage.setIcon(imageIcon);
        add(MainImage);

    }
    public void dialogError(Boolean choice1) {
        if(choice1) {
            JDialog dialog = MainAdmin.af.createDialog("MESSAGE", true, 310, 160);
            JButton b2 = new JButton();
            b2.setBounds(0, 0, 310, 160);
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    username.setText("");
                    pass.setText("");
                    dialog.dispose();
                    setVisible(false);
                    MainAdmin.af.adminPage2.setVisible(true);
                }
            });
            ImageIcon errorImage1 = MainAdmin.af.createIcon("/images/ShablonButtonsBlue.jpg");
            b2.setIcon(errorImage1);
            dialog.add(b2);
            dialog.setLocation(250, 200);
            dialog.setVisible(true);
            //System.out.println("works");
            MainAdmin.af.setVisible(true);
        }
        else {
            JDialog dialog = MainAdmin.af.createDialog("MESSAGE", true, 310, 160);

            JButton b2 = new JButton();
            b2.setBounds(0, 0, 310, 160);
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });

            ImageIcon errorImage2 = MainAdmin.af.createIcon("/images/ErrorDialogFon.png");
            b2.setIcon(errorImage2);

            dialog.setLocation(250, 200);
            dialog.add(b2);
            dialog.setVisible(true);
        }
    }

}
