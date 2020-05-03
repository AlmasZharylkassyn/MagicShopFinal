package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpWindow extends Container {

    private JLabel MainImage;

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

        font = new Font(null, Font.CENTER_BASELINE, 18);

        username = new JTextField();
        username.setBounds(610, 193, 250, 40);
        username.setFont(font);
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
                    Data data = new Data("Registration", user);
                    //System.out.println(user + " kek");
                    try {
                        Main.registration(data);
                    } catch (Exception eq) {
                        System.out.println("No connection to server, pls run server");
                        JOptionPane.showMessageDialog(null, "Run server first");
                    }
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
