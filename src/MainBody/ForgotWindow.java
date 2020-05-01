package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ForgotWindow extends Container {

    //Connection connection;

   // ResultSet resultSet;

    //PreparedStatement preparedStatement;

    private ClientSomthing cs;


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

        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////
        font = new Font(null, Font.CENTER_BASELINE, 18);

        JLabel tempLabel = new JLabel("WORK IN PROGRESS");
        tempLabel.setBounds(1000, 600, 250, 100);
        tempLabel.setFont(font);
        tempLabel.setForeground(Color.GREEN);
        add(tempLabel);
        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////

        //connection = Connect.ConnectDb();

        font = new Font(null, Font.CENTER_BASELINE, 15);

        usernameL = new JLabel("Username");
        usernameL.setForeground(Color.green);
        usernameL.setFont(font);
        usernameL.setBounds(450, 100, 100, 30);
        add(usernameL);

        secretQuestionL = new JLabel("Secret question:");
        secretQuestionL.setForeground(Color.green);
        secretQuestionL.setFont(font);
        secretQuestionL.setBounds(450, 150, 200, 30);
        add(secretQuestionL);

        answerL = new JLabel("Answer:");
        answerL.setBounds(500, 200, 100, 30);
        add(answerL);

        passwordL = new JLabel("Password:");
        passwordL.setBounds(500, 250, 100, 30);
        add(passwordL);

        username = new JTextField();
        username.setBounds(600, 100, 120, 30);
        add(username);

        secretQuestion = new JTextField();
        secretQuestion.setBounds(600, 150, 120, 30);
        secretQuestion.setEditable(false);
        add(secretQuestion);

        answer = new JTextField();
        answer.setBounds(600, 200, 120, 30);
        add(answer);

        password = new JTextField();
        password.setBounds(600, 250, 120, 30);
        password.setEditable(false);
        add(password);

        search = new JButton("Search");
        search.setBounds(750, 100, 100, 30);

        /*dialog1Label = new JLabel();
        dialog1Label.setBounds(300, 250, 180, 68);
        ImageIcon dialog1 = Main.mf.createIcon("LibraryDialog1.png");
        dialog1Label.setIcon(dialog1);
        add(dialog1Label);*/

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search();
            }
        });
        add(search);

        retrieve = new JButton("Retrieve");
        retrieve.setBounds(750, 200, 100, 30);

        retrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Retrive();
            }
        });
        add(retrieve);

        back = new JButton("BACK");
        back.setBackground(Color.BLUE);
        back.setBounds(750, 250, 100, 50);
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
        user.setChoose("ForgotSearch");

        if (!username.getText().equalsIgnoreCase("")) {
            try {
                cs = new ClientSomthing(Main.ipAddr, Main.port, user);
            } catch (Exception e) {
                System.out.println("No connection to server, pls run server");
                JOptionPane.showMessageDialog(null, "Run server first");
            }
        } else {
            dialog1(false, "");
        }
/*
        try {
            String text = username.getText();

            String query = "select * from account where Login ='" + text + "'" ;

            Statement statement = connection.createStatement();


            resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                //JOptionPane.showMessageDialog(null,"It is login true");
                ImageIcon DialogImage1 = Main.mf.createIcon("LibraryDialog1.png");
                b1.setIcon(DialogImage1);

                String secretP = resultSet.getString("sec_q");

                //System.out.println(secretP);

                secretQuestion.setText(secretP);
//                resultSet.close();
//                preparedStatement.close();

            }else {
                //JOptionPane.showMessageDialog(null, "Incorerct login");
                ImageIcon DialogImage2 = Main.mf.createIcon("LibraryDialog2.png");
                secretQuestion.setText("");
                answer.setText("");
                password.setText("");
                b1.setIcon(DialogImage2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.add(b1);
        dialog.setVisible(true);*/
    }

    public void dialog1(Boolean choice, String secretP) {
        JDialog dialog = Main.mf.createDialog("Librarian:", true, 310, 160);

        try {
            b1 = new JButton();
            if (choice == true) {
                //JOptionPane.showMessageDialog(null,"It is login true");
                try {
                    ImageIcon DialogImage1 = Main.mf.createIcon("/images/LibraryDialog1.png");

                    b1.setIcon(DialogImage1);
                    secretQuestion.setText(secretP);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                //System.out.println("suka");
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
        user.setChoose("ForgotRetrive");

        if (!username.getText().equalsIgnoreCase("") && !answer.getText().equalsIgnoreCase("")) {
            try {
                cs = new ClientSomthing(Main.ipAddr, Main.port, user);
            } catch (Exception e) {
                System.out.println("No connection to server, pls run server");
                JOptionPane.showMessageDialog(null, "Run server first");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Joldardy toltyr");
        }

        /*if (username.getText().equalsIgnoreCase("") || answer.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Joldardy toltyr");
        } else {

            try {
                String a2 = answer.getText();
                String n2 = username.getText();
                String query = "select * from Account where answer = '" + a2 + "' and login ='"+ n2 + "'";
                //select * from account where login=? and password=?
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeQuery();

                Statement statement = connection.createStatement();


                resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    String ans = resultSet.getString("answer");

                    String pas = resultSet.getString("password");

                    if (a2.equalsIgnoreCase(ans)) {
                        JOptionPane.showMessageDialog(null, "Correct");

                        password.setText(pas);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect answer");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/
    }

    public void dialog2(Boolean choice, String password) {
        if (choice == true) {
            JOptionPane.showMessageDialog(null, "Correct");
            this.password.setText(password);
        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect answer");
        }
    }

}
