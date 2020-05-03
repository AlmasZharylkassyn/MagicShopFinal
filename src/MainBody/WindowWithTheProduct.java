package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WindowWithTheProduct extends Container {
    private Font font;

    private JLabel MainImage;

    private JLabel coinL;
    private JLabel howMuch;
    private JLabel image1;

    private JTextArea listOfProducts;

    private JTextField idOfProduct;
    private JTextField cash;

    private JButton list;
    private JButton buy;
    private JButton myMoney;
    private JButton exit;

    private JSpinner buyCount;

    private Integer cashTotal;

    public WindowWithTheProduct() {
        setSize(1280, 720);
        setLayout(null);

        image1 = new JLabel();
        image1.setBounds(650, 130, 180, 74);
        ImageIcon img1 = Main.mf.createIcon("/images/Image1.png");
        image1.setIcon(img1);
        add(image1);

        font = new Font(null, Font.TYPE1_FONT, 15);


        idOfProduct = new JTextField();
        idOfProduct.setFont(new Font(null, Font.BOLD, 30));
        idOfProduct.setBounds(840, 145, 100, 50);
        add(idOfProduct);

        listOfProducts = new JTextArea();
        listOfProducts.setBounds(40, 55, 450, 560);
        listOfProducts.setFont(font);
        add(listOfProducts);

        list = new JButton();
        ImageIcon imageList = Main.mf.createIcon("/images/ButtonList.png");
        list.setIcon(imageList);
        list.setBounds(740, 30, 180, 68);
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.listProducts();
            }
        });
        add(list);

        buy = new JButton();
        ImageIcon buyImage = Main.mf.createIcon("/images/ButtonBuy.png");
        buy.setIcon(buyImage);
        buy.setBounds(740, 340, 180, 68);
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyProduct();
            }
        });
        add(buy);

        myMoney = new JButton();
        ImageIcon chekCashImg = Main.mf.createIcon("/images/ButtonCheckCash.png");
        myMoney.setIcon(chekCashImg);
        myMoney.setBounds(740, 440, 180, 68);
        myMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.checkCash();
            }
        });
        add(myMoney);

        cash = new JTextField();
        cash.setEditable(false);
        cash.setBounds(940, 450, 70, 50);
        cash.setFont(new Font(null, Font.BOLD, 25));
        add(cash);

        coinL = new JLabel();
        coinL.setBounds(1000, 440, 70, 70);
        ImageIcon coinIcon = Main.mf.createIcon("/images/coinImage.png");
        coinL.setIcon(coinIcon);
        add(coinL);

        howMuch = new JLabel();
        ImageIcon img2 = Main.mf.createIcon("/images/Image2.png");
        howMuch.setIcon(img2);
        howMuch.setBounds(650, 230, 180, 74);
        howMuch.setFont(font);
        add(howMuch);
        image1.repaint();

        SpinnerModel count = new SpinnerNumberModel(1, 1, 20, 1);
        buyCount = new JSpinner(count);
        buyCount.setBounds(840, 247, 100, 50);
        buyCount.setFont(new Font(null, Font.BOLD, 30));
        add(buyCount);

        exit = new JButton();
        exit.setBounds(740, 540, 180, 68);
        ImageIcon imageExit = Main.mf.createIcon("/images/ButtonImageExit.png");
        exit.setIcon(imageExit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exit);

        ImageIcon mainImageIcon = Main.mf.createIcon("/images/2.jpg"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, 0, 1280, 720);
        MainImage.setIcon(mainImageIcon);
        add(MainImage);


    }

    public void listProducts(ArrayList<Product> products) {
        String text = "";
        for (int i = 0; i < products.size(); i++) {
            text += products.get(i).getStringData() + "\n";
        }
        listOfProducts.setText(text);
    }

    public void checkCash(Integer cash) {
        cashTotal = cash;
        if (cash != null) {
            String ss = String.valueOf(cash);
            this.cash.setText(ss);
        }
        else {
            JOptionPane.showMessageDialog(null, "Something went wrong");
        }
    }

    public void buyProduct() {
        try {
            Integer id = null;
            Integer howMuch = null;
            Boolean b = false;
            try {
                id = Integer.parseInt(idOfProduct.getText());
                howMuch = (Integer) buyCount.getValue();
                b = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (b) {
                Product product = new Product(null, null, howMuch, null);
                Main.checkCash();
                User user = new User();
                user.setCash(cashTotal);
                Data data = new Data("buyProduct", product);
                //data.setOrder("buyProduct");
                data.setInteger(id);
                data.setUser(user);
                Main.buyProduct(data);
            }
            else {
                dialog1(false, null, null);
            }
        } catch (NullPointerException e) {
            JDialog dialog = Main.mf.createDialog("Old seller", true, 310, 160);
            JButton b2 = new JButton();
            b2.setBounds(0, 0, 310, 160);
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
            ImageIcon errorImage1 = null;
            errorImage1 = Main.mf.createIcon("/images/WWTS14.png");
            b2.setIcon(errorImage1);
            dialog.add(b2);
            dialog.setVisible(true);
        }
    }
    public void dialog1(Boolean choice, String order, Integer cash1) {
        JDialog dialog = Main.mf.createDialog("Old seller", true, 310, 160);
        JButton b2 = new JButton();
        b2.setBounds(0, 0, 310, 160);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        ImageIcon errorImage1 = null;
        if (choice) {
            String cash = String.valueOf(cash1);
            this.cash.setText(cash);
            Main.listProducts();
            //JOptionPane.showMessageDialog(null, "Successful");
            errorImage1 = Main.mf.createIcon("/images/WWTS10.png");
        }
        else {
                if (order.equals("NotEnoughCash")) {
                    //JOptionPane.showMessageDialog(null, "Not enough cash");
                    errorImage1 = Main.mf.createIcon("/images/WWTS11.png");
                } else if (order.equals("NotEnoughAmountOfProduct")) {
                    //JOptionPane.showMessageDialog(null, "Not enough amount of product");
                    errorImage1 = Main.mf.createIcon("/images/WWTS12.png");
                } else {
                    //JOptionPane.showMessageDialog(null, "Not successful");
                    errorImage1 = Main.mf.createIcon("/images/WWTS13.png");
                }
        }
        b2.setIcon(errorImage1);
        dialog.add(b2);
        dialog.setVisible(true);
    }
}
