package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminAddProduct extends Container {

    private JLabel MainImage;

    private Font font;

    private JLabel nameL;
    private JLabel amountL;
    private JLabel priceL;

    private JTextField name;
    private JTextField amount;
    private JTextField price;

    private JButton addProduct;
    private JButton back;

    public AdminAddProduct() {
        setSize(768, 432);
        setLayout(null);

        font = new Font(null, Font.BOLD, 25);

        nameL = new JLabel("Name");
        nameL.setBounds(170, 20, 100, 40);
        nameL.setForeground(Color.ORANGE);
        nameL.setFont(font);
        add(nameL);

        amountL = new JLabel("Amount");
        amountL.setBounds(170, 80, 100, 40);
        amountL.setForeground(Color.ORANGE);
        amountL.setFont(font);
        add(amountL);

        priceL = new JLabel("Price");
        priceL.setBounds(170, 140, 100, 40);
        priceL.setForeground(Color.ORANGE);
        priceL.setFont(font);
        add(priceL);

        font = new Font(null, Font.BOLD, 15);

        name = new JTextField();
        name.setBounds(275, 20, 220, 40);
        name.setFont(font);
        name.setBackground(Color.LIGHT_GRAY);
        add(name);

        amount = new JTextField();
        amount.setBounds(275, 80, 220, 40);
        amount.setFont(font);
        amount.setBackground(Color.LIGHT_GRAY);
        add(amount);

        price = new JTextField();
        price.setBounds(275, 140, 220, 40);
        price.setFont(font);
        price.setBackground(Color.LIGHT_GRAY);
        add(price);

        font = new Font(null, Font.BOLD, 25);

        addProduct = new JButton("ADD");
        addProduct.setBounds(295, 230, 180, 40);
        addProduct.setForeground(Color.ORANGE);
        addProduct.setFont(font);
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!name.getText().equals("") && !amount.getText().equals("") && !price.getText().equals("")) {
                    try {
                        int amount1 = Integer.parseInt(amount.getText());
                        int price1 = Integer.parseInt(price.getText());
                        Product product = new Product(null, name.getText(), amount1, price1);
                        Data data = new Data("addProduct", product);
                        MainAdmin.addProduct(data);
                    } catch (Exception ea) {
                        JOptionPane.showMessageDialog(null, "No string type in amount and price");
                        ea.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Joldardy toltyr");
                }
            }
        });
        add(addProduct);

        back = new JButton("BACK");
        back.setFont(font);
        back.setBounds(295, 290, 180, 40);
        back.setForeground(Color.ORANGE);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainAdmin.af.adminPage2.setVisible(true);
                name.setText("");
                amount.setText("");
                price.setText("");
            }
        });
        add(back);

        ImageIcon imageIcon = MainAdmin.af.createIcon("/images/adminAddPageFon.png"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, -10, 768, 432);
        MainImage.setIcon(imageIcon);
        add(MainImage);
    }

    public void dialogError(Boolean choice) {
        if (choice) {
            JOptionPane.showMessageDialog(null, "Product has been added to the dataBase");
            name.setText("");
            amount.setText("");
            price.setText("");
        }
        else {
            JOptionPane.showMessageDialog(null, "There is already the product with the same name");
            name.setText("");
        }
    }
}
