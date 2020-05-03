package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminDeleteProduct extends Container {

    private JLabel MainImage;

    private Font font;

    private JTextArea productList;

    private JLabel deleteIdL;

    private JTextField deleteId;

    private JButton list;
    private JButton delete;
    private JButton back;

    public AdminDeleteProduct() {
        setSize(768, 432);
        setLayout(null);

        font = new Font(null, Font.TYPE1_FONT, 15);

        productList = new JTextArea();
        productList.setBounds(15, 15, 500, 350);
        productList.setFont(font);
        add(productList);

        deleteIdL = new JLabel("ID of product for deleting:");
        deleteIdL.setFont(font);
        deleteIdL.setForeground(Color.WHITE);
        deleteIdL.setBounds(555, 94, 220, 40);
        add(deleteIdL);

        deleteId = new JTextField();
        deleteId.setBounds(565,140, 180, 40);
        deleteId.setFont(font);
        add(deleteId);

        list = new JButton("LIST");
        list.setBounds(565, 260, 180, 40);
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainAdmin.listProducts();
            }
        });
        add(list);

        delete = new JButton("DELETE");
        delete.setBounds(565, 200, 180, 40);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer id1 = Integer.parseInt(deleteId.getText());
                    Data data = new Data();
                    data.setOrder("deleteProduct");
                    data.setInteger(id1);
                    MainAdmin.deleteProduct(data);
                } catch (Exception ea) {
                    ea.printStackTrace();
                }
            }
        });
        add(delete);

        back = new JButton("BACK");
        //back.setFont(font);
        back.setBounds(565, 320, 180, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainAdmin.af.adminPage2.setVisible(true);
                //nameOfProduct.setText("");
                //amount.setText("");
            }
        });
        add(back);

        ImageIcon imageIcon = MainAdmin.af.createIcon("/images/adminDeletePageFon.png"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, -10, 768, 432);
        MainImage.setIcon(imageIcon);
        add(MainImage);
    }

    public void listProducts(ArrayList<Product> products) {
        String text = "";
        for (int i = 0; i < products.size(); i++) {
            text += products.get(i).getStringData() + "\n";
        }
        productList.setText(text);
    }

    public void deleteProduct(Boolean choice) {
        if (choice) {
            JOptionPane.showMessageDialog(null, "Product has been deleted");
        }
        else {
            JOptionPane.showMessageDialog(null, "Can not delete");
        }
    }
}
