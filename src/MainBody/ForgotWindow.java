package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotWindow extends Container {

    private JLabel MainImage;

    private JLabel usernameL;
    private JLabel secretQuestionL;
    private JLabel answerL;
    private JLabel passwordL;

    private JLabel dialog1Label;

    private JTextField username;
    private JTextField secretQuestion;
    private JTextField answer;
    private JTextField password;

    private JTextArea jtextArea = new JTextArea();

    private JButton search;
    private JButton retrieve;
    private JButton back;

    private JButton b1;
    private JButton b2;

    private Font font;

    public ForgotWindow() {
        setSize(1280, 720);
        setLayout(null);

        Color textFieldColor = new Color(128, 87, 61);

        font = new Font(null, Font.CENTER_BASELINE, 15);

        usernameL = new JLabel();
        ImageIcon nameImage = Main.mf.createIcon("/images/forgotWindow1.png");
        //usernameL.setForeground(Color.green);
        //usernameL.setFont(font);
        usernameL.setIcon(nameImage);
        usernameL.setBounds(400, 95, 180, 74);
        add(usernameL);

        secretQuestionL = new JLabel();
        ImageIcon sqImage = Main.mf.createIcon("/images/forgotWindow2.png");
        secretQuestionL.setIcon(sqImage);
        secretQuestionL.setBounds(400, 195, 180, 74);
        add(secretQuestionL);

        answerL = new JLabel();
        ImageIcon ansImage = Main.mf.createIcon("/images/forgotWindow3.png");
        answerL.setIcon(ansImage);
        answerL.setBounds(400, 295, 180, 74);
        add(answerL);

        passwordL = new JLabel("Password:");
        ImageIcon passImage = Main.mf.createIcon("/images/forgotWindow4.png");
        passwordL.setIcon(passImage);
        passwordL.setBounds(400, 395, 180, 74);
        add(passwordL);

        font = new Font(null, Font.CENTER_BASELINE, 17);

        username = new JTextField();
        username.setForeground(Color.BLACK);
        username.setFont(font);
        username.setBackground(textFieldColor);
        username.setBounds(590, 105, 220, 50);
        add(username);

        secretQuestion = new JTextField();
        secretQuestion.setForeground(Color.BLACK);
        secretQuestion.setFont(font);
        secretQuestion.setBackground(textFieldColor);
        secretQuestion.setBounds(590, 205, 220, 50);
        secretQuestion.setEditable(false);
        add(secretQuestion);

        answer = new JTextField();
        answer.setForeground(Color.BLACK);
        answer.setFont(font);
        answer.setBackground(textFieldColor);
        answer.setBounds(590, 305, 220, 50);
        add(answer);

        password = new JTextField();
        password.setForeground(Color.BLACK);
        password.setFont(font);
        password.setBackground(textFieldColor);
        password.setBounds(590, 405, 220, 50);
        password.setEditable(false);
        add(password);

        search = new JButton();
        ImageIcon searchImage = Main.mf.createIcon("/images/forgotWindow5.png");
        search.setIcon(searchImage);
        search.setBounds(820, 100, 180, 68);

        /*dialog1Label = new JLabel();
        dialog1Label.setBounds(300, 250, 180, 68);
        ImageIcon dialog1 = Main.mf.createIcon("LibraryDialog1.png");
        dialog1Label.setIcon(dialog1);
        add(dialog1Label);*/

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!username.getText().equalsIgnoreCase("admin"))
                    Search();
                else
                    dialog1(false, "");
            }
        });
        add(search);

        retrieve = new JButton();
        ImageIcon retrieveImage = Main.mf.createIcon("/images/forgotWindow6.png");
        retrieve.setIcon(retrieveImage);
        retrieve.setBounds(820, 300, 180, 68);

        retrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!username.getText().equalsIgnoreCase("admin"))
                    Retrive();
                else
                    dialog2(false, "");
            }
        });
        add(retrieve);

        back = new JButton();
        ImageIcon backImage = Main.mf.createIcon("/images/forgotWindow7.png");
        back.setIcon(backImage);
        back.setBounds(610, 490, 180, 68);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                secretQuestion.setText("");
                answer.setText("");
                password.setText("");
                Main.mf.forgotWindow.setVisible(false);
                Main.mf.loginMenu.setVisible(true);
            }
        });
        add(back);


        ImageIcon imageIcon = Main.mf.createIcon("/images/ImageForgot.jpg"); //изменить фоновую картинку
        MainImage = new JLabel();
        MainImage.setBounds(0, 0, 1280, 720);
        MainImage.setIcon(imageIcon);
        add(MainImage);

    }

    public void Search(){
        User user = new User(null, username.getText(), null, null, null);
        Data data = new Data("ForgotSearch", user);
        if (!username.getText().equalsIgnoreCase("")) {
            try {
                Main.forgotSearch(data);
            } catch (Exception e) {
                System.out.println("No connection to server, pls run server");
                JOptionPane.showMessageDialog(null, "Run server first");
            }
        } else {
            dialog1(false, "");
        }
    }

    public void dialog1(Boolean choice, String secretP) {
        JDialog dialog = Main.mf.createDialog("Librarian:", true, 310, 160);
        try {
            b1 = new JButton();
            if (choice == true) {
                try {
                    ImageIcon DialogImage1 = Main.mf.createIcon("/images/LibraryDialog1.png");
                    b1.setIcon(DialogImage1);
                    secretQuestion.setText(secretP);
                    //System.out.println("Hm");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                //System.out.println("ллл");
                ImageIcon DialogImage2 = Main.mf.createIcon("/images/LibraryDialog2.png");
                secretQuestion.setText("");
                answer.setText("");
                password.setText("");
                b1.setIcon(DialogImage2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.add(b1);
        dialog.setVisible(true);

    }

    public void Retrive() {
        User user = new User(null, username.getText(), null, null, answer.getText());
        Data data = new Data("forgotRetrive", user);

        if (!username.getText().equalsIgnoreCase("") && !answer.getText().equalsIgnoreCase("")) {
            try {
                Main.forgotRetrive(data);
            } catch (Exception e) {
                System.out.println("No connection to server, pls run server");
                JOptionPane.showMessageDialog(null, "Run server first");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Joldardy toltyr");
        }
    }

    public void dialog2(Boolean choice, String password) {
        JDialog dialog = Main.mf.createDialog("Librarian:", true, 310, 160);
        b1 = new JButton();
        if (choice == true) {
            //JOptionPane.showMessageDialog(null, "Correct");
            this.password.setText(password);
            try {
                ImageIcon DialogImage1 = Main.mf.createIcon("/images/forgotWindow8.png");
                b1.setIcon(DialogImage1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            ImageIcon DialogImage2 = Main.mf.createIcon("/images/forgotWindow9.png");
            b1.setIcon(DialogImage2);
            //JOptionPane.showMessageDialog(null, "Incorrect answer");
        }
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.add(b1);
        dialog.setVisible(true);
    }

}
