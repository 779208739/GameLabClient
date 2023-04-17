package management;

import javax.swing.*;

public class Layout {
    JFrame frame = new JFrame("Game Management");

    public void init() {
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        GameAppend gameappend = new GameAppend();
        gameappend.init();
        gameappend.setLocation(200, 50);
        frame.add(gameappend);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Layout().init();
    }

}