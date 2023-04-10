package GAMELAB;

import java.awt.*;
import javax.swing.*;

// Main frame
public class Layout {
    JFrame frame = new JFrame("Game Lab");

    Header header = new Header();
    Body body = new Body();

    public void init() {
        frame.setSize(1000, 600);

        frame.setLocationRelativeTo(null);

        frame.getContentPane().setLayout(null);

        JPanel PanelHeader = header.init();
        JPanel PanelBody = body.init();
        Search search = new Search();
        Navigation navigation = new Navigation();

        PanelHeader.setBounds(0, 0, 1000, 50);
        PanelBody.setLocation(100, 100);
        search.setLocation(200, 50);
        navigation.setLocation(20, 100);

        frame.add(navigation);
        frame.add(PanelHeader);
        frame.add(PanelBody);
        frame.add(search);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
