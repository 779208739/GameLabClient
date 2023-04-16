package GAMELAB;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

public class Body {
    JPanel MainPanel = new JPanel();
    JPanel Game = new JPanel();
    JPanel GameSet = new JPanel();

    GameSubcard gameSubcard = new GameSubcard("Name", 10.10, "Intro", "Keywords");

    Navigation navigation = new Navigation();

    Pagination page = new Pagination(0);

    public JPanel init() {
        setPanel();

        return MainPanel;
    }

    private void setPanel() {
        MainPanel.setBackground(Color.blue);
        MainPanel.setLayout(null);
        MainPanel.setSize(900, 500);

        // Set pagination
        setPagination();
        page.setLocation(80, 440);
        // Set GameSubCard
        gameSubcard.setLocation(590, 0);
        // Set GameSet
        setGameSet(0);
        navigation.setBounds(0, 0, 80, 180);

        GameSet.setBackground(Color.blue);
        GameSet.setBounds(80, 0, 500, 440);
        GameSet.setLayout(null);
        // Set Navigation
        setNavigation();

        MainPanel.add(gameSubcard);
        MainPanel.add(GameSet);
        MainPanel.add(page);
        MainPanel.add(navigation);
    }

    // Update the games(size is less than or equal to 4) in the main screen
    // navIndex: 0 = Lab, 1 = Store, 2 = Cart
    private void setGameSet(int navIndex) {
        int[] GetGameID = {};

        // int[] GetGameID = ...
        switch (navIndex) {
            case 0:
                // Lab
                GetGameID = new int[] { 1, 2, 3, 4, 5, 6, 7 };
                break;
            case 1:
                // Store
                GetGameID = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
                break;
            case 2:
                // Cart
                GetGameID = new int[] { 11, 12 };
        }

        this.page.PageSize = (GetGameID.length - 1) / 4 + 1;
        if ((GetGameID.length - 1) / 4 == 0)
            this.page.NextPage.setEnabled(false);
        else if ((GetGameID.length - 1) / 4 != 0 && this.page.PageNow != this.page.PageSize)
            this.page.NextPage.setEnabled(true);

        this.page.updateLabel();

        int[] showGameID = Arrays.copyOfRange(GetGameID, (this.page.PageNow - 1) * 4,
                this.page.PageNow * 4 > GetGameID.length ? GetGameID.length : this.page.PageNow * 4);

        GameSet.removeAll();

        for (int index = 0; index < showGameID.length; index++) {
            // GameCard card = new GameCard(Game game);
            GameCard card = new GameCard();
            card.setLocation(0, 110 * index);
            GameSet.add(card);
        }

        GameSet.revalidate();
        GameSet.repaint();

    }

    private void setGameSubCard() {
        this.gameSubcard.updateGameSubCard("UpdateName", 10.30, "updateIntro", "updateIntro", 0.8);
    }

    private void setPagination() {
        this.page.NextPage.addActionListener((e) -> {
            ClickOnNext();
        });

        this.page.PreviousPage.addActionListener((e) -> {
            ClickOnPrevious();
        });

    }

    private void setNavigation() {
        this.navigation.BtnLibrary.addActionListener((e) -> {
            ClickOnNavigation(0);
        });
        this.navigation.BtnStore.addActionListener((e) -> {
            ClickOnNavigation(1);
        });
        this.navigation.BtnCart.addActionListener((e) -> {
            ClickOnNavigation(2);
        });
        // Initial

        ClickOnNavigation(0);
    }

    private void ClickOnNext() {
        this.page.PageNow++;
        if (this.page.PageNow == this.page.PageSize)
            this.page.NextPage.setEnabled(false);

        this.page.PreviousPage.setEnabled(true);
        this.page.updateLabel();
        setGameSet(this.navigation.IndexNow);
    }

    private void ClickOnPrevious() {
        this.page.PageNow--;
        if (this.page.PageNow == 1)
            this.page.PreviousPage.setEnabled(false);

        this.page.NextPage.setEnabled(true);
        this.page.updateLabel();
        setGameSet(this.navigation.IndexNow);
    }

    private void ClickOnNavigation(int index) {
        this.page.PageNow = 1;
        this.navigation.IndexNow = index;
        setGameSet(index);

        this.navigation.BtnLibrary.setBackground(Color.white);
        this.navigation.BtnStore.setBackground(Color.WHITE);
        this.navigation.BtnCart.setBackground(Color.WHITE);

        switch (index) {
            case 0:
                this.navigation.BtnLibrary.setBackground(Color.PINK);
                break;
            case 1:
                this.navigation.BtnStore.setBackground(Color.PINK);
                break;
            case 2:
                this.navigation.BtnCart.setBackground(Color.PINK);
                break;
        }
    }
}
