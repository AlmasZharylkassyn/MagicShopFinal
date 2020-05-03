package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminTopUpProduct extends Container {

    private JLabel MainImage;

    private Font font;

    private JLabel nameL;
    private JLabel amountL;
    private JLabel topUpL;

    private JButton search;
    private JButton topUpProduct;
    private JButton back;

    private JTextField nameOfProduct;
    private JTextField amount;

    private JSpinner topUpAmount;

    public AdminTopUpProduct() {
        setSize(768, 432);
        setLayout(null);

        font = new Font(null, Font.BOLD, 25);

        nameL = new JLabel("Name");
        nameL.setBounds(170, 20, 100, 40);
        nameL.setForeground(Color.WHITE);
        nameL.setFont(font);
        add(nameL);

        amountL = new JLabel("Amount");
        amountL.setBounds(170, 80, 100, 40);
        amountL.setForeground(Color.WHITE);
        amountL.setFont(font);
        add(amountL);

        font = new Font(null, Font.BOLD, 15);

        topUpL = new JLabel("Пополнить на:");
        topUpL.setBounds(170, 140, 120, 40);
        topUpL.setForeground(Color.WHITE);
        topUpL.setFont(font);
        add(topUpL);

        JLabel topUpL2 = new JLabel("или уменьшить на:");
        topUpL2.setBounds(135, 170, 200, 40);
        topUpL2.setFont(font);
        topUpL2.setForeground(Color.WHITE);
        add(topUpL2);

        nameOfProduct = new JTextField();
        nameOfProduct.setBounds(275, 20, 220, 40);
        nameOfProduct.setFont(font);
        nameOfProduct.setBackground(Color.LIGHT_GRAY);
        add(nameOfProduct);

        amount = new JTextField();
        amount.setBounds(275, 80, 220, 40);
        amount.setFont(font);
        amount.setEditable(false);
        amount.setBackground(Color.LIGHT_GRAY);
        add(amount);

        font = new Font(null, Font.BOLD, 25);

        SpinnerModel count = new SpinnerNumberModel(1, -20, 20, 1);
        topUpAmount = new JSpinner(count);
        topUpAmount.setBounds(295, 160, 180, 40);
        topUpAmount.setFont(font);
        add(topUpAmount);

        font = new Font(null, Font.BOLD, 15);

        search = new JButton("Search");
        search.setBounds(530, 20, 180, 40);
        search.setFont(font);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("kek");
                Search();
            }
        });
        add(search);

        topUpProduct = new JButton("Top Up");
        topUpProduct.setBounds(295, 230, 180, 40);
        topUpProduct.setFont(font);
        topUpProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopUp();
            }
        });
        add(topUpProduct);

        back = new JButton("BACK");
        back.setFont(font);
        back.setBounds(295, 290, 180, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainAdmin.af.adminPage2.setVisible(true);
                nameOfProduct.setText("");
                amount.setText("");
            }
        });
        add(back);

        ImageIcon imageIcon = MainAdmin.af.createIcon("/images/adminTopUpFon.png"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, -10, 768, 432);
        MainImage.setIcon(imageIcon);
        add(MainImage);
    }

    public void Search() {
        Product product = new Product(null, nameOfProduct.getText(), null, null);
        Data data = new Data("searchTopUp", product);
        if (!nameOfProduct.getText().equalsIgnoreCase("")) {
            try {
                MainAdmin.searchTopUp(data);
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
            String amountTemp = String.valueOf(count);
            JOptionPane.showMessageDialog(null, "Correcr name");
            amount.setText(amountTemp);
        }
        else {
            JOptionPane.showMessageDialog(null, "Can`t find this product");
        }
    }

    public void TopUp() {
        if (!nameOfProduct.equals("")) {
            Integer temp = (Integer) topUpAmount.getValue();
            Product product = new Product(null, nameOfProduct.getText(), temp, null);
            Data data = new Data("topUpProduct", product);
            try {
                MainAdmin.topUpProduct(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Joldardy toltyr");
        }

    }
    public void dialog2(Boolean choice, Integer count) {
        if (choice) {
            JOptionPane.showMessageDialog(null, "Success");
            String amountTemp = String.valueOf(count);
            this.amount.setText(amountTemp);
        }
        else {
            JOptionPane.showMessageDialog(null, "Can not top up");
        }
    }

}

