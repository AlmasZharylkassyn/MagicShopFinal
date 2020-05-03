package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminChangeCash extends Container {

    private JLabel MainImage;

    private Font font;

    private JLabel nameL;
    private JLabel cashCountL;
    private JLabel cashForAddingL;

    private JTextField cashCount;
    private JTextField name;
    private JTextField cashForAdding;

    private JButton search;
    private JButton addCash;
    private JButton back;

    public AdminChangeCash() {
        setSize(768, 432);
        setLayout(null);

        font = new Font(null, Font.BOLD, 17);

        nameL = new JLabel("Client's name");
        nameL.setBounds(15, 15, 120, 50);
        nameL.setForeground(Color.green);
        nameL.setFont(font);
        add(nameL);

        cashCountL = new JLabel("Cash count");
        cashCountL.setBounds(15, 85, 120, 50);
        cashCountL.setForeground(Color.green);
        cashCountL.setFont(font);
        add(cashCountL);

        cashForAddingL = new JLabel("Cash for adding:");
        cashForAddingL.setBounds(2, 155, 150, 50);
        cashForAddingL.setForeground(Color.BLUE);
        cashForAddingL.setFont(font);
        add(cashForAddingL);

        name = new JTextField();
        name.setBounds(135, 15, 200, 50);
        add(name);

        cashCount = new JTextField();
        cashCount.setEditable(false);
        cashCount.setBounds(135, 85, 200, 50);
        add(cashCount);

        cashForAdding = new JTextField();
        cashForAdding.setBounds(135, 155, 200, 50);
        add(cashForAdding);

        search = new JButton("SEARCH");
        search.setBounds(365, 15, 180, 40);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search();
            }
        });
        add(search);

        addCash = new JButton("ADD CASH");
        addCash.setBounds(365, 155, 180, 40);
        addCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAddCash();
            }
        });
        add(addCash);


        back = new JButton("BACK");
        //back.setFont(font);
        back.setBounds(565, 320, 180, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainAdmin.af.adminPage2.setVisible(true);
            }
        });
        add(back);

        ImageIcon imageIcon = MainAdmin.af.createIcon("/images/AdminCash.jpg"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, -10, 768, 432);
        MainImage.setIcon(imageIcon);
        add(MainImage);
    }
    public void Search() {
        User user = new User(null, name.getText(), null, null, null);
        Data data = new Data("searchAddCash", user);
        if (!name.getText().equalsIgnoreCase("")) {
            try {
                MainAdmin.searchAddCash(data);
            } catch (Exception e) {
                System.out.println("No connection to server, pls run server");
                JOptionPane.showMessageDialog(null, "Run server first");
            }
        } else {
            dialog1(false, null);
        }
    }
    public void dialog1(Boolean choice, Integer count) {
        if (choice) {
            String cashTemp = String.valueOf(count);
            JOptionPane.showMessageDialog(null, "Correcr name");
            cashCount.setText(cashTemp);
        }
        else {
            JOptionPane.showMessageDialog(null, "Can`t find this name");
        }
    }


    public void AdminAddCash() {
        try {
            Integer cashCount = Integer.parseInt(cashForAdding.getText());
            User user = new User();
            user.setName(name.getText());
            Data data = new Data("adminAddCash", user);
            data.setInteger(cashCount);
            MainAdmin.adminAddCash(data);
        } catch (Exception e) {
            dialog2(false, null);
        }
    }
    public void dialog2(Boolean choice, Integer cash) {
        if (choice) {
            JOptionPane.showMessageDialog(null, "Success");
            cashCount.setText(String.valueOf(cash));
            //Search();
        }
        else  {
            JOptionPane.showMessageDialog(null, "Not success");
        }

    }

}
