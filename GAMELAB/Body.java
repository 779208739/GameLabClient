package GAMELAB;

import java.awt.*;
import java.awt.Component;
import java.util.Arrays;

import javax.swing.*;

public class Body {
    JPanel MainPanel = new JPanel();
    JPanel Game = new JPanel();
    JPanel GameSet = new JPanel();

    GameSubcard gameSubcard = new GameSubcard("Name", 10.10, "Intro", "Keywords");
    Navigation navigation = new Navigation();
    Pagination page = new Pagination(0);
    Search search = new Search();

    public JPanel init() {
        setPanel();

        return MainPanel;
    }

    private void setPanel() {
        MainPanel.setLayout(null);
        MainPanel.setSize(970, 530);

        // Set pagination
        setPagination();
        page.setLocation(90, 490);

        // Set GameSubCard
        gameSubcard.setLocation(660, 50);

        // Set GameSet
        setGameSet();
        navigation.setBounds(0, 50, 80, 180);

        GameSet.setBounds(90, 50, 570, 440);
        GameSet.setLayout(null);

        // Set Navigation
        setNavigation();

        // Set Search
        setSearch();
        search.setLocation(10, 0);

        MainPanel.add(gameSubcard);
        MainPanel.add(GameSet);
        MainPanel.add(page);
        MainPanel.add(navigation);
        MainPanel.add(search);
    }

    // Update the games(size is less than or equal to 4) in the main screen
    // navIndex: 0 = Lab, 1 = Store, 2 = Cart
    private void setGameSet() {
        int[] GetGameID = {};

        // int[] GetGameID = ...
        switch (this.navigation.IndexNow) {
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

        // If search
        if (this.search.SearchNow)
            switch (this.navigation.IndexNow) {
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

        // Add GameCard
        // For each gamecard
        for (int index = 0; index < showGameID.length; index++) {
            // GameCard card = new GameCard(Game game);
            GameCard card = new GameCard();
            card.setLocation(0, 110 * index);

            // Add btn for updating GameSubcard
            JButton BtnGameSubcard = new JButton(">");
            BtnGameSubcard.setBorder(null);
            BtnGameSubcard.setBounds(510, 110 * index, 50, 100);
            // Set background color for btn
            BtnGameSubcard.setOpaque(true);
            BtnGameSubcard.setBorderPainted(false);

            if (index != 0)
                BtnGameSubcard.setBackground(Color.ORANGE);
            else
                BtnGameSubcard.setBackground(Color.lightGray);

            BtnGameSubcard.addActionListener((e) -> {
                ClickOnGameSubcard(BtnGameSubcard, card.GameID);
            });

            GameSet.add(card);
            GameSet.add(BtnGameSubcard);
        }

        GameSet.revalidate();
        GameSet.repaint();

    }

    private void setGameSubCard(int GameID) {
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

    private void setSearch() {
        this.search.init();

        this.search.SearchBtn.addActionListener((e) -> {
            ClickOnSearch();
        });
    }

    private void ClickOnNext() {
        this.page.PageNow++;
        if (this.page.PageNow == this.page.PageSize)
            this.page.NextPage.setEnabled(false);

        this.page.PreviousPage.setEnabled(true);
        this.page.updateLabel();
        setGameSet();
    }

    private void ClickOnPrevious() {
        this.page.PageNow--;
        if (this.page.PageNow == 1)
            this.page.PreviousPage.setEnabled(false);

        this.page.NextPage.setEnabled(true);
        this.page.updateLabel();
        setGameSet();
    }

    private void ClickOnNavigation(int index) {
        this.page.PageNow = 1;
        this.navigation.IndexNow = index;
        setGameSet();

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

    private void ClickOnSearch() {

        this.search.SearchNow = true;
        setGameSet();
        this.search.SearchNow = false;
    }

    private void ClickOnGameSubcard(JButton BtnGameSubcard, int GameID) {
        // Clickings will change the color of the btn
        Component[] componentList = GameSet.getComponents();
        for (Component btn : componentList) {
            if (btn instanceof JButton) {
                btn.setBackground(Color.ORANGE);
            }
        }

        BtnGameSubcard.setBackground(Color.lightGray);
        setGameSubCard(GameID);
    }
}
