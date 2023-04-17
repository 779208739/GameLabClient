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

    }

    GameSubcard(String Name, double Price, String Intro, String Keyword) {
        // No discount
        this.Name = Name;
        this.Price = Price;
        this.Intro = Intro;
        this.Keyword = Keyword;
        this.Discount = 1.00;

    }

    private void init(int displayCase) {
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

        createButtons(displayCase);

        if (Discount != 1.00) {
            // Show original price if exist a discount
            LabelPrice.setForeground(Color.green);
            this.add(LabelOriginPrice);
        }

        this.add(LabelName);
        this.add(LabelPrice);
        this.add(LabelKeyword);
        this.add(TextIntro);
    }

    private void createButtons(int displayCase) {
        switch (displayCase) {
            case 0: // Lab
                JButton BtnRemove = new JButton("Remove");
                BtnRemove.setFont(new Font("Serif", Font.PLAIN, 20));
                BtnRemove.setBounds(210, 380, 80, 40);
                this.add(BtnRemove);
                break;
            case 1: // Store
                JButton BtnAdd = new JButton("Add");
                BtnAdd.setFont(new Font("Serif", Font.PLAIN, 20));
                BtnAdd.setBounds(100, 380, 80, 40);

                JButton BtnBuy = new JButton("Buy");
                BtnBuy.setFont(new Font("Serif", Font.PLAIN, 20));
                BtnBuy.setBounds(210, 380, 80, 40);

                this.add(BtnBuy);
                this.add(BtnAdd);
                break;
            case 2: // Cart
                JButton BtnBuyCart = new JButton("Delete");
                BtnBuyCart.setFont(new Font("Serif", Font.PLAIN, 20));
                BtnBuyCart.setBounds(100, 380, 80, 40);

                JButton BtnDelete = new JButton("Buy");
                BtnDelete.setFont(new Font("Serif", Font.PLAIN, 20));
                BtnDelete.setBounds(210, 380, 80, 40);

                this.add(BtnBuyCart);
                this.add(BtnDelete);

        }
    }

    public void updateGameSubCard(String Name, double Price, String Intro, String Keyword, double Discount, int displayCase) {
        // getGameInfo
        this.Name = Name;
        this.Price = Price;
        this.Intro = Intro;
        this.Keyword = Keyword;
        this.Discount = Discount;

        this.removeAll();
        init(displayCase);

        // Repaint the UI to reflect the changes
        this.revalidate();
        this.repaint();
    }
}
