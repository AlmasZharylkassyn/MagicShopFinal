package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage2 extends Container {

    private Font font;

    private JLabel MainImage;

    private JButton addProduct;
    private JButton topUpProduct;
    private JButton changeProduct;
    private JButton changeCash;

    private JButton exit;

    public AdminPage2() {
        setSize(768, 432);
        setLayout(null);


        font = new Font(null, Font.CENTER_BASELINE, 15);

        addProduct = new JButton("Добавить товар");
        addProduct.setFont(font);
        addProduct.setBounds(295, 20, 180, 40);
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainAdmin.af.adminAddProduct.setVisible(true);
            }
        });
        add(addProduct);

        topUpProduct = new JButton("Пополнить товар");
        topUpProduct.setFont(font);
        topUpProduct.setBounds(295, 80, 180, 40);
        topUpProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainAdmin.af.adminTopUpProduct.setVisible(true);
            }
        });
        add(topUpProduct);

        changeProduct = new JButton("Удалить товар");
        changeProduct.setFont(font);
        changeProduct.setBounds(295, 140, 180, 40);
        changeProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainAdmin.af.adminDeleteProduct.setVisible(true);
            }
        });
        add(changeProduct);

        changeCash = new JButton("Изменить cash у клиента");
        changeCash.setFont(new Font(null, Font.BOLD, 11));
        changeCash.setBounds(295, 200, 180, 40);
        changeCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainAdmin.af.adminChangeCash.setVisible(true);
            }
        });
        add(changeCash);

        font = new Font(null, Font.CENTER_BASELINE, 20);

        exit = new JButton("EXIT");
        exit.setFont(font);
        exit.setBounds(295, 340, 180, 40);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exit);

        ImageIcon imageIcon = MainAdmin.af.createIcon("/images/adminPage2Fon.png"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, -10, 768, 432);
        MainImage.setIcon(imageIcon);
        add(MainImage);
    }
}
