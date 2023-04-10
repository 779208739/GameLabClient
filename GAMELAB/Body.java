package GAMELAB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.*;

import netscape.javascript.JSObject;

public class Body {
    JPanel MainPanel = new JPanel();
    JPanel Game = new JPanel();

    public JPanel init() {
        setPanel(MainPanel);

        return MainPanel;
    }

    private void setPanel(JPanel panel) {
        panel.setBackground(Color.blue);
        panel.setLayout(null);
        panel.setSize(820, 500);

        GameSubcard subcard = new GameSubcard("GameName", 10.20,
                "aa cc bv sdd sasdadssad dfafa ddd", "aa bb cc");
        subcard.setLocation(510, 0);

        panel.add(subcard);
        panel.add(setGameSet());
    }

    private JPanel setGameSet() {
        JPanel CardSets = new JPanel();
        CardSets.setBackground(Color.blue);
        CardSets.setBounds(0, 0, 600, 440);
        CardSets.setLayout(null);

        GameCard card1 = new GameCard();
        GameCard card2 = new GameCard();
        GameCard card3 = new GameCard();
        GameCard card4 = new GameCard();

        card2.setLocation(0, 110);
        card3.setLocation(0, 220);
        card4.setLocation(0, 330);
        CardSets.add(card1);
        CardSets.add(card2);
        CardSets.add(card3);
        CardSets.add(card4);

        return CardSets;
    }

}
