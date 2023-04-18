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

    boolean isSearchActive = false;

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

        setSearch();

        MainPanel.add(gameSubcard);
        MainPanel.add(GameSet);
        MainPanel.add(page);
        MainPanel.add(navigation);

    }

    // Update the games(size is less than or equal to 4) in the main screen
    // navIndex: 0 = Lab, 1 = Store, 2 = Cart
    private void setGameSet() {
        int[] GetGameID = {};

        // get GameID from database
        if (isSearchActive) {
            GetGameID = this.search.gameIDs;
        } else {
            // get GameID from database
            switch (this.navigation.IndexNow) {
                case 0:
                    // Lab
                    GetGameID = librarydao.getGamesInLibrary();
                    MainPanel.remove(search);
                    break;
                case 1:
                    // Store
                    GetGameID = storedao.getGameIDs();
                    search.setLocation(10, 0);
                    MainPanel.add(search);
                    break;
                case 2:
                    // Cart
                    GetGameID = cartdao.getGamesInCart();
                    MainPanel.remove(search);
            }
        }

        MainPanel.revalidate();
        MainPanel.repaint();

        // If search
        // if (this.search.SearchNow){
        // GetGameID = this.search.gameIDs;
        // }

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
            BtnGameSubcard.setForeground(Color.WHITE);
            BtnGameSubcard.setBorder(null);
            BtnGameSubcard.setBounds(510, 110 * index, 50, 100);
            // Set background color for btn
            BtnGameSubcard.setOpaque(true);
            BtnGameSubcard.setBorderPainted(false);

            if (index != 0) {
                BtnGameSubcard.setBackground(new Color(221, 148, 53));
                BtnGameSubcard.setForeground(Color.BLACK);
            } else {
                BtnGameSubcard.setBackground(new Color(46, 46, 46));
                BtnGameSubcard.setForeground(Color.WHITE);
            }

            // different buttons will be displayed
            switch (this.navigation.IndexNow) {
                case 0:
                    if (index == 0) {
                        setGameSubCard(game, 0);
                    }

                    BtnGameSubcard.addActionListener((e) -> {
                        ClickOnGameSubcard(BtnGameSubcard, game, 0);
                    });

                    GameSet.add(BtnGameSubcard);
                    break;
                case 1:
                    if (index == 0) {
                        setGameSubCard(game, 1);
                    }

                    BtnGameSubcard.addActionListener((e) -> {
                        ClickOnGameSubcard(BtnGameSubcard, game, 1);
                    });

                    GameSet.add(BtnGameSubcard);
                    break;
                case 2:
                    if (index == 0) {
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

            String searchBarText = this.search.SearchBar.getText().trim();
            String searchType = (String) this.search.SearchType.getSelectedItem();

            if (searchBarText.isEmpty()) {
                // Call getGamesByType if SearchBar is empty
                this.search.gameIDs = storedao.getGamesByType(searchType);
            } else {
                // Call searchGames if SearchBar has text
                this.search.gameIDs = storedao.searchGames(searchBarText, searchType);
            }

            ClickOnSearch();
        });
    }

    private void ClickOnNext() {
        if (isSearchActive) {
            this.page.PageNow++;
        } else {
            this.page.PageNow++;
            if (this.page.PageNow == this.page.PageSize)
                this.page.NextPage.setEnabled(false);
        }

        this.page.PreviousPage.setEnabled(true);
        this.page.updateLabel();
        setGameSet();
    }

    private void ClickOnPrevious() {
        if (isSearchActive) {
            this.page.PageNow--;
        } else {
            this.page.PageNow--;
            if (this.page.PageNow == 1)
                this.page.PreviousPage.setEnabled(false);
        }

        this.page.NextPage.setEnabled(true);
        this.page.updateLabel();
        setGameSet();
    }

    private void ClickOnNavigation(int index) {
        isSearchActive = false;

        this.page.PageNow = 1;
        this.navigation.IndexNow = index;
        setGameSet();

        this.navigation.BtnLibrary.setBackground(new Color(221, 148, 53));
        this.navigation.BtnStore.setBackground(new Color(221, 148, 53));
        this.navigation.BtnCart.setBackground(new Color(221, 148, 53));

        this.navigation.BtnLibrary.setForeground(Color.BLACK);
        this.navigation.BtnStore.setForeground(Color.BLACK);
        this.navigation.BtnCart.setForeground(Color.BLACK);

        switch (index) {
            case 0:
                this.navigation.BtnLibrary.setBackground(new Color(46, 46, 46));
                this.navigation.BtnLibrary.setForeground(Color.WHITE);
                break;
            case 1:
                this.navigation.BtnStore.setBackground(new Color(46, 46, 46));
                this.navigation.BtnStore.setForeground(Color.WHITE);
                break;
            case 2:
                this.navigation.BtnCart.setBackground(new Color(46, 46, 46));
                this.navigation.BtnCart.setForeground(Color.WHITE);
                break;
        }

        page.PreviousPage.setEnabled(false);
    }

    private void ClickOnSearch() {

        isSearchActive = true;
        setGameSet();

    }

    private void ClickOnGameSubcard(JButton BtnGameSubcard, Game game, int displayCase) {
        // Clickings will change the color of the btn
        Component[] componentList = GameSet.getComponents();
        for (Component btn : componentList) {
            if (btn instanceof JButton) {
                btn.setBackground(new Color(221, 148, 53));
                btn.setForeground(Color.BLACK);
            }
        }

        BtnGameSubcard.setBackground(new Color(46, 46, 46));
        BtnGameSubcard.setForeground(Color.WHITE);
        setGameSubCard(game, displayCase);
    }
}
