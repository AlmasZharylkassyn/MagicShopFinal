package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpWindow extends Container {

    private JLabel MainImage;

    private Connection connection;
    private ResultSet resultSet;

    private PreparedStatement preparedStatement;

    private JTextField username;
    private JTextField pass;
    private JComboBox sec_questions;
    private JTextField secQuestion;
    private JTextField answer;

    private Font font;

    private JButton back;
    private JButton create;

    private String[] questions = {"Your lovely country", "Your lovely football team", "Your first car", "Your best friend from childhood"};
    public SignUpWindow() {
        setSize(1280, 720);
        setLayout(null);

        connection = Connect.ConnectDb();

        font = new Font(null, Font.CENTER_BASELINE, 18);

        username = new JTextField();
        username.setBounds(610, 193, 250, 40);
        username.setFont(font);
        //ImageIcon imageUsername = Main.mf.createIcon("ForUsername.png");
        //JLabel tempLabel = new JLabel();
        //tempLabel.setBounds(550, 100, 180, 68);
        //tempLabel.setIcon(imageUsername);
        //add(tempLabel);
        username.setBackground(Color.LIGHT_GRAY);
        add(username);

        pass = new JTextField();
        pass.setBounds(610, 292, 250, 40);
        pass.setFont(font);
        pass.setBackground(Color.LIGHT_GRAY);
        add(pass);

        sec_questions = new JComboBox(questions);
        sec_questions.setBounds(610, 386, 250, 40);
        sec_questions.setFont(font);
        sec_questions.setBackground(Color.LIGHT_GRAY);
        add(sec_questions);

        //secQuestion = new JTextField();
        //secQuestion.setBounds(610, 386, 250, 40);
        //secQuestion.setFont(font);
        //secQuestion.setBackground(Color.LIGHT_GRAY);
        //add(secQuestion);

        answer = new JTextField();
        answer.setBounds(610, 486, 250, 40);
        answer.setFont(font);
        answer.setBackground(Color.LIGHT_GRAY);
        add(answer);


        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////
        JLabel tempLabel = new JLabel("WORK IN PROGRESS");
        tempLabel.setBounds(1000, 600, 200, 100);
        tempLabel.setFont(font);
        tempLabel.setForeground(Color.RED);
        add(tempLabel);

        //JLabel temp = new JLabel();
        //temp.setBounds(-200, -100, 643, 643);
        //ImageIcon imageIcon = Main.mf.createIcon("xia-pu-.jpg"); //ButtonImage.png
        //temp.setIcon(imageIcon);
        //add(temp);
        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////

        create = new JButton();
        ImageIcon imageCreate = Main.mf.createIcon("/images/SignUpWindowButton2.png");
        create.setIcon(imageCreate);
        create.setBounds(585, 560, 313, 95);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!username.getText().equalsIgnoreCase("") && !pass.getText().equalsIgnoreCase("") &&
                !answer.getText().equalsIgnoreCase("")) {
                    User user = new User(null, username.getText(), pass.getText(), (String) sec_questions.getSelectedItem(), answer.getText());
                    user.setChoose("Registration");
                    System.out.println(user + " kek");
                    try {
                        ClientSomthing cs = new ClientSomthing(Main.ipAddr, Main.port, user);
                    } catch (Exception eq) {
                        System.out.println("No connection to server, pls run server");
                        JOptionPane.showMessageDialog(null, "Run server first");
                    }
                    /*try {
                        String query = "select * from account where login=?";

                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, username.getText());

                        //preparedStatement.setString(2,pass.getText());

                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            //resultSet.close();
                            //preparedStatement.close();
                            //System.out.println();
                            JOptionPane.showMessageDialog(null, "There is already the user with the same login");
                            //setVisible(false);
                            //Loading l = new Loading();
                            //l.setVisible(true);

                        } else {
                            //JOptionPane.showMessageDialog(null, "Incorrect login and password");
                            try {
                                String query1 = "select * from account where password=?";

                                preparedStatement= connection.prepareStatement(query1);
                                preparedStatement.setString(1, pass.getText());

                                resultSet = preparedStatement.executeQuery();
                                if (resultSet.next()) {
                                    JOptionPane.showMessageDialog(null, "Correct pass");
                                } else {
                                    System.out.println("kek");
                                }
                            } catch (Exception eg) {
                                eg.printStackTrace();
                            }

                        }
                    } catch (Exception et) {
                        et.printStackTrace();
                    } */
                } else {
                    JOptionPane.showMessageDialog(null, "Joldardy toltyr");
                }
            }
        });
        add(create);

        back = new JButton();
        ImageIcon imageBack = Main.mf.createIcon("/images/SignUpWindowButton.png");
        back.setIcon(imageBack);
        back.setBounds(170, 560, 313, 95);


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Main.mf.loginMenu.setVisible(true);
                username.setText("");
                pass.setText("");
                //secQuestion.setText("");
                answer.setText("");
            }
        });
        add(back);


        ImageIcon image = Main.mf.createIcon("/images/SignUpWindow2.png"); //изменить фоновую картинку
        //ImageIcon image = Main.mf.createIcon("SignUpImage.png"); //второй вариант
        MainImage = new JLabel();
        MainImage.setBounds(0, 0, 1280, 720);
        MainImage.setIcon(image);
        add(MainImage);
    }

    public void signUpButton(Boolean choice1) {
        if (choice1 == true) {
            JOptionPane.showMessageDialog(null, "Account has been created");
        } else {
            JOptionPane.showMessageDialog(null, "There is already the user with the same login");
        }
    }
}
