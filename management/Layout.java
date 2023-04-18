package management;

import java.awt.Panel;

import javax.swing.*;

public class Layout {
    JFrame frame = new JFrame("Game Management");

    public void init() {
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        GameAppend gameappend = new GameAppend();
        gameappend.setLocation(100, 100);
        frame.add(gameappend);

        GamerRemove gamerremove = new GamerRemove();
        gamerremove.setLocation(720, 100);
        frame.add(gamerremove);

        Header header = new Header();
        JPanel PanelHeader = header.init(frame);
        PanelHeader.setBounds(0, 0, 1000, 50);
        frame.add(PanelHeader);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Layout().init();
    }

}