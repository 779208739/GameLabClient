package GAMELAB;

import java.awt.Color;
import javax.swing.*;
import java.awt.Font;

public class GameSubcard extends JPanel {
    int GameID;
    String Name;
    double Price;
    String Intro;
    String Keyword;
    double Discount;

    private JLabel LabelName;
    private JLabel LabelPrice;
    private JLabel LabelOriginPrice;
    private JLabel LabelKeyword;
    private JTextArea TextIntro;

    GameSubcard(){}

    GameSubcard(String Name, double Price, String Intro, String Keyword, double Discount) {
        this.Name = Name;
        this.Price = Price;
        this.Intro = Intro;
        this.Keyword = Keyword;
        this.Discount = Discount;

        init();
    }

    GameSubcard(String Name, double Price, String Intro, String Keyword) {
        // No discount
        this.Name = Name;
        this.Price = Price;
        this.Intro = Intro;
        this.Keyword = Keyword;
        this.Discount = 1.00;

        init();
    }

    private void init() {
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.setSize(300, 430);

        LabelName = new JLabel(this.Name);
        LabelName.setFont(new Font("Serif", Font.PLAIN, 35));
        LabelName.setBounds(10, 10, 295, 35);

        LabelPrice = new JLabel("$ " + String.valueOf(this.Price * this.Discount), SwingConstants.RIGHT);
        LabelPrice.setFont(new Font("Serif", Font.PLAIN, 38));
        LabelPrice.setBounds(190, 40, 100, 45);

        LabelOriginPrice = new JLabel("$ " + String.valueOf(this.Price), SwingConstants.RIGHT);
        LabelOriginPrice.setFont(new Font("Serif", Font.PLAIN, 15));
        LabelOriginPrice.setBounds(10, 40, 50, 45);

        LabelKeyword = new JLabel(this.Keyword);
        LabelKeyword.setFont(new Font("Serif", Font.PLAIN, 20));
        LabelKeyword.setBounds(10, 100, 290, 25);

        TextIntro = new JTextArea(this.Intro);
        TextIntro.setBounds(10, 160, 280, 200);
        TextIntro.setFont(new Font("Serif", Font.PLAIN, 20));
        TextIntro.setWrapStyleWord(true);
        TextIntro.setLineWrap(true);
        TextIntro.setBorder(null);
        TextIntro.setEditable(false);

        JButton BtnBuy = new JButton("Buy");
        BtnBuy.setFont(new Font("Serif", Font.PLAIN, 20));
        BtnBuy.setBounds(210, 380, 80, 40);

        if (Discount != 1.00) {
            // Show original price if exist a discount
            LabelPrice.setForeground(Color.green);
            this.add(LabelOriginPrice);
        }

        this.add(LabelName);
        this.add(LabelPrice);
        this.add(LabelKeyword);
        this.add(TextIntro);
        this.add(BtnBuy);
    }

    public void updateGameSubCard(String Name, double Price, String Intro, String Keyword, double Discount) {
        // getGameInfo
        this.Name = Name;
        this.Price = Price;
        this.Intro = Intro;
        this.Keyword = Keyword;
        this.Discount = Discount;

        this.removeAll();
        init();

        // Repaint the UI to reflect the changes
        this.revalidate();
        this.repaint();
    }
}
