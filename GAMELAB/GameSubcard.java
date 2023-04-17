package GAMELAB;

import system.gamerDAO;

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

    gamerDAO gamer = new gamerDAO();

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

                BtnRemove.addActionListener(e -> {
                    boolean isRemoved = gamer.remove(this.GameID);
                    showMessageWindow(isRemoved);
                });

                this.add(BtnRemove);
                break;
            case 1: // Store
                JButton BtnAdd = new JButton("Add");
                BtnAdd.setFont(new Font("Serif", Font.PLAIN, 20));
                BtnAdd.setBounds(100, 380, 80, 40);

                BtnAdd.addActionListener(e -> {
                    boolean isAdded = gamer.addToCart(this.GameID);
                    showMessageWindow(isAdded);
                });

                JButton BtnBuy = new JButton("Buy");
                BtnBuy.setFont(new Font("Serif", Font.PLAIN, 20));
                BtnBuy.setBounds(210, 380, 80, 40);

                BtnBuy.addActionListener(e -> {
                    boolean isPurchased = gamer.purchase(this.GameID);
                    showMessageWindow(isPurchased);
                });

                this.add(BtnBuy);
                this.add(BtnAdd);
                break;
            case 2: // Cart
                JButton BtnBuyCart = new JButton("Buy");
                BtnBuyCart.setFont(new Font("Serif", Font.PLAIN, 20));
                BtnBuyCart.setBounds(210, 380, 80, 40);

                BtnBuyCart.addActionListener(e -> {
                    boolean isPurchasedCart = gamer.purchase(this.GameID);
                    boolean isDeletedCart = gamer.delete(this.GameID);
                    showMessageWindow(isPurchasedCart && isDeletedCart);
                });

                JButton BtnDelete = new JButton("Delete");
                BtnDelete.setFont(new Font("Serif", Font.PLAIN, 20));
                BtnDelete.setBounds(100, 380, 80, 40);

                BtnDelete.addActionListener(e -> {
                    boolean isDeleted = gamer.delete(this.GameID);
                    showMessageWindow(isDeleted);
                });

                this.add(BtnBuyCart);
                this.add(BtnDelete);

        }
    }

    public void showMessageWindow(boolean isSuccess) {
        String message;

        if (isSuccess) {
            message = "Success and Refresh!";
        } else {
            message = "Fail, already in cart or lib";
        }

        JOptionPane.showMessageDialog(null, message);
    }

    public void updateGameSubCard(int GameID, String Name, double Price, String Intro, String Keyword, double Discount, int displayCase) {
        // getGameInfo
        this.GameID = GameID;
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
