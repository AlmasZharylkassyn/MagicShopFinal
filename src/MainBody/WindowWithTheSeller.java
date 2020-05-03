package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowWithTheSeller extends Container {

    private JButton button1;
    private JButton button2;

    private JLabel MainImage;

    private Font font;

    public WindowWithTheSeller() {
        setSize(1280, 720);
        setLayout(null);

        button2 = new JButton();
        button2.setBounds(450, 500, 330, 120);
        ImageIcon imageIcon6 = Main.mf.createIcon("/images/WWTS4.png");
        button2.setIcon(imageIcon6);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JDialog firstDialog = Main.mf.createDialog("Old seller", true, 330, 140);
                    JButton buttonFirstDialog = new JButton();
                    buttonFirstDialog.setBounds(0, 0, 330, 140);
                    ImageIcon FirstDialogImage = Main.mf.createIcon("/images/WWTS5.png");
                    buttonFirstDialog.setIcon(FirstDialogImage);
                    buttonFirstDialog.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            firstDialog.dispose();
                        }
                    });
                    firstDialog.add(buttonFirstDialog);
                    firstDialog.setLocation(500, 300);
                    firstDialog.setVisible(true);
                } catch (Exception ea) {
                    ea.printStackTrace();
                }
                remove(button2);
                repaint();
                setVisible(false);
                Main.mf.windowWithTheProduct.setVisible(true);
                Main.checkCash();
            }
        });

        /**
         ***********************************************************
         */
        font = new Font(null, Font.CENTER_BASELINE, 18);
        JLabel tempLabel = new JLabel("WORK IN PROGRESS");
        tempLabel.setBounds(1000, 600, 250, 100);
        tempLabel.setForeground(Color.GREEN);
        tempLabel.setFont(font);
        add(tempLabel);
        /**
         ************************************************************
         */

        button1 = new JButton();
        ImageIcon imageIcon5 = Main.mf.createIcon("/images/WWTS2.png");
        button1.setBounds(550, 550, 165, 59);
        button1.setIcon(imageIcon5);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JDialog firstDialog = Main.mf.createDialog("Old seller", true, 330, 140);
                    JButton buttonFirstDialog = new JButton();
                    buttonFirstDialog.setBounds(0, 0, 330, 140);
                    ImageIcon FirstDialogImage = Main.mf.createIcon("/images/WWTS3.png");
                    buttonFirstDialog.setIcon(FirstDialogImage);
                    buttonFirstDialog.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            firstDialog.dispose();
                            add(button2);
                        }
                    });
                    firstDialog.add(buttonFirstDialog);
                    firstDialog.setLocation(500, 300);
                    firstDialog.setVisible(true);
                } catch (Exception ea) {
                    ea.printStackTrace();
                }
                remove(button1);
                button2.repaint();
            }
        });
        add(button1);

        ImageIcon mainImageIcon = Main.mf.createIcon("/images/magicshopinside4.jpg"); //LoginMenuImageNewTemp1 //LoginMenuImageNew
        MainImage = new JLabel();                           //Изменить название картинки
        MainImage.setBounds(0, 0, 1280, 720);
        MainImage.setIcon(mainImageIcon);
        add(MainImage);
    }
}
