package MainBody;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

public class Main {
    public static MainFrame mf;
    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        mf = new MainFrame();
        mf.setVisible(true);
    }

}
