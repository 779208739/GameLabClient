package GAMELAB;

import java.awt.Color;

import javax.swing.*;

public class Navigation extends JPanel {
    JButton BtnLibrary;
    JButton BtnStore;
    JButton BtnCart;

    Navigation() {
        init();
    }

    private void init() {
        this.setLayout(null);
        this.setSize(80, 150);

        this.BtnLibrary = new JButton("Lab");
        this.BtnStore = new JButton("Store");
        this.BtnCart = new JButton("Cart");

        this.BtnLibrary.setBounds(0, 0, 80, 50);
        this.BtnStore.setBounds(0, 50, 80, 50);
        this.BtnCart.setBounds(0, 100, 80, 50);

        // To show the background color on MacOS
        BtnLibrary.setOpaque(true);
        BtnLibrary.setBorderPainted(false);
        BtnStore.setOpaque(true);
        BtnStore.setBorderPainted(false);
        BtnCart.setOpaque(true);
        BtnCart.setBorderPainted(false);

        this.BtnLibrary.addActionListener((e) -> {
            ClickOn(0);
        });
        this.BtnStore.addActionListener((e) -> {
            ClickOn(1);
        });
        this.BtnCart.addActionListener((e) -> {
            ClickOn(2);
        });

        this.add(BtnCart);
        this.add(BtnLibrary);
        this.add(BtnStore);

        // Initial with library
        ClickOn(0);
    }

    private void ClickOn(int index) {
        this.BtnLibrary.setBackground(Color.white);
        this.BtnStore.setBackground(Color.WHITE);
        this.BtnCart.setBackground(Color.WHITE);

        switch (index) {
            case 0:
                this.BtnLibrary.setBackground(Color.PINK);
                break;
            case 1:
                this.BtnStore.setBackground(Color.PINK);
                break;
            case 2:
                this.BtnCart.setBackground(Color.PINK);
                break;
        }
    }
}
