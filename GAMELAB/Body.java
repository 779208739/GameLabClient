package GAMELAB;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

public class Body {
    JPanel MainPanel = new JPanel();
    JPanel Game = new JPanel();
    JPanel GameSet = new JPanel();

    Pagination page = new Pagination(0);

    public JPanel init() {
        setPanel();

        return MainPanel;
    }

    private void setPanel() {
        MainPanel.setBackground(Color.blue);
        MainPanel.setLayout(null);
        MainPanel.setSize(820, 500);

        // Set pagination
        setPagination();
        this.page.setLocation(0, 440);

        GameSubcard subcard = new GameSubcard("GameName", 10.20,
                "aa cc bv sdd sasdadssad dfafa ddd", "aa bb cc");
        subcard.setLocation(510, 0);
        // Set GameSet
        setGameSet();
        GameSet.setBackground(Color.blue);
        GameSet.setBounds(0, 0, 600, 440);
        GameSet.setLayout(null);

        MainPanel.add(subcard);
        MainPanel.add(GameSet);
        MainPanel.add(this.page);
    }

    // Update the games(size is less than or equal to 4) in the main screen
    private void setGameSet() {
        // int[] GetGameID = ...
        int[] GetGameID = { 1, 2, 3, 4, 5, 6, 7 };
        this.page.PageSize = (GetGameID.length - 1) / 4 + 1;
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

    private void setPagination() {
        this.page.NextPage.addActionListener((e) -> {
            ClickOnNext();
        });

        this.page.PreviousPage.addActionListener((e) -> {
            ClickOnPrevious();
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
}
