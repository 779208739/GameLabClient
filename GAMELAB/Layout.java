package GAMELAB;

import java.awt.*;
import javax.swing.*;

// Main frame
public class Layout {
    JFrame frame = new JFrame("Game Lab");

    Header header = new Header();
    Body body = new Body();

    public void init() {
        frame.setSize(1000, 620);

        frame.setLocationRelativeTo(null);

        frame.getContentPane().setLayout(null);

        JPanel PanelHeader = header.init(frame);
        JPanel PanelBody = body.init();

        PanelHeader.setBounds(0, 0, 1000, 50);
        PanelBody.setLocation(10, 50);

        frame.add(PanelHeader);
        frame.add(PanelBody);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
