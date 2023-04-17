package GAMELAB;

import entity.Game;
import system.cartDAO;
import system.gameDAO;
import system.libraryDAO;
import system.storeDAO;

import java.awt.*;
import java.awt.Component;
import java.util.Arrays;

import javax.swing.*;

public class Body {
    JPanel MainPanel = new JPanel();
    JPanel Game = new JPanel();
    JPanel GameSet = new JPanel();

    GameSubcard gameSubcard = new GameSubcard();
    Navigation navigation = new Navigation();
    Pagination page = new Pagination(0);
    Search search = new Search();

    libraryDAO librarydao = new libraryDAO();
    storeDAO storedao = new storeDAO();
    cartDAO cartdao = new cartDAO();
    gameDAO gamedao = new gameDAO();

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

        // get GameID from database
        switch (this.navigation.IndexNow) {
            case 0:
                // Lab
                GetGameID = librarydao.getGamesInLibrary();
                break;
            case 1:
                // Store
                GetGameID = storedao.getGameIDs();
                break;
            case 2:
                // Cart
                GetGameID = cartdao.getGamesInCart();
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
            int gameID = showGameID[index];
            entity.Game game = gamedao.getGameInfo(gameID);

            String Name = game.getGameName();
            String ImgPath = game.getImages().get(0); // display the first image
            String Type = game.getType();
            String KeyWords = String.join(", ", game.getKeywords());

            GameCard card = new GameCard(gameID, Name, ImgPath, Type, KeyWords);
            card.setLocation(0, 110 * index);
            GameSet.add(card);


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

            // different buttons will be displayed
            switch(this.navigation.IndexNow){
                case 0:
                    if(index == 0){
                        setGameSubCard(game, 0);
                    }

                    BtnGameSubcard.addActionListener((e) -> {
                        ClickOnGameSubcard(BtnGameSubcard, game, 0);
                    });

                    GameSet.add(BtnGameSubcard);
                    break;
                case 1:
                    if(index == 0){
                        setGameSubCard(game, 1);
                    }

                    BtnGameSubcard.addActionListener((e) -> {
                        ClickOnGameSubcard(BtnGameSubcard, game, 1);
                    });

                    GameSet.add(BtnGameSubcard);
                    break;
                case 2:
                    if(index == 0){
                        setGameSubCard(game, 2);
                    }

                    BtnGameSubcard.addActionListener((e) -> {
                        ClickOnGameSubcard(BtnGameSubcard, game, 2);
                    });

                    GameSet.add(BtnGameSubcard);
            }
        }

        GameSet.revalidate();
        GameSet.repaint();

    }

    private void setGameSubCard(Game game, int displayCase) {
        int GameID = game.getGameID();
        String GameName = game.getGameName();
        double Price = game.getPrice();
        String intro = game.getDescription();
        String KeyWords = String.join(", ", game.getKeywords());

        this.gameSubcard.updateGameSubCard(GameID, GameName, Price, intro, KeyWords, 1.0, displayCase);
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

    private void ClickOnGameSubcard(JButton BtnGameSubcard, Game game, int displayCase) {
        // Clickings will change the color of the btn
        Component[] componentList = GameSet.getComponents();
        for (Component btn : componentList) {
            if (btn instanceof JButton) {
                btn.setBackground(Color.ORANGE);
            }
        }

        BtnGameSubcard.setBackground(Color.lightGray);
        setGameSubCard(game, displayCase);
    }
}
