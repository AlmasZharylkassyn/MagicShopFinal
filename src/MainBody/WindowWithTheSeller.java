package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowWithTheSeller extends Container { //nihuia ne gotovo tozhe

    private JButton button1 = new JButton();

    private JLabel MainImage;
    private JLabel tMainImage;

    private Font font;

    public WindowWithTheSeller() {
        setSize(1280, 720);
        setLayout(null);

        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////
        button1.setBounds(10, 10, 180, 68); //180, 68
        ImageIcon imageIcon = Main.mf.createIcon("/images/ButtonImage.png"); //ButtonImage.png
        button1.setIcon(imageIcon);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.mf.windowWithTheSeller.setVisible(false);
                Main.mf.loginMenu.setVisible(true);
                remove(tMainImage);
                add(MainImage);
            }
        });
        add(button1);

        font = new Font(null, Font.CENTER_BASELINE, 18);
        JLabel tempLabel = new JLabel("WORK IN PROGRESS");
        tempLabel.setBounds(1000, 600, 250, 100);
        tempLabel.setForeground(Color.GREEN);
        tempLabel.setFont(font);
        add(tempLabel);
        /////////////////////////////////////////////////////
        /////////////////////////////////////////////////////

        ImageIcon imageIcon1 = Main.mf.createIcon("/images/magicshopinside4.jpg"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, 0, 1280, 720);
        MainImage.setIcon(imageIcon1);


        ImageIcon imageIcon2 = Main.mf.createIcon("/images/LoginMenuImageNewTemp2.png"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        tMainImage = new JLabel();                           //Изменить название картинки
        tMainImage.setBounds(0, 0, 1280, 720);
        tMainImage.setIcon(imageIcon2);

        JButton tempB = new JButton("kek");
        tempB.setBounds(800, 400, 100, 50);
        tempB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(MainImage);
                add(tMainImage);
                setVisible(false);
                setVisible(true);

            }
        });
        add(tempB);
        //remove(MainImage);
        add(MainImage);
    }
}
