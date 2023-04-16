package GAMELAB;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.*;

public class Navigation extends JPanel {
    // 0 = Lab, 1 = Store, 2 = Cart
    int IndexNow = 0;

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
        // https://stackoverflow.com/questions/1065691/how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
        BtnLibrary.setOpaque(true);
        BtnLibrary.setBorderPainted(false);
        BtnStore.setOpaque(true);
        BtnStore.setBorderPainted(false);
        BtnCart.setOpaque(true);
        BtnCart.setBorderPainted(false);

        this.add(BtnCart);
        this.add(BtnLibrary);
        this.add(BtnStore);

    }

}
