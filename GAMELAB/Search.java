package GAMELAB;

import java.util.List;
import javax.swing.*;

public class Search extends JPanel {
    public static int[] GetGameIDs(String str, List<String> Keywords) {

        int[] GameIDs = { 1, 2, 3 };
        return GameIDs;
    }

    public void init() {
        this.setLayout(null);
        this.setSize(600, 50);

        JTextField SearchBar = new JTextField();
        SearchBar.setBounds(0, 10, 200, 30);

        JComboBox<String> SearchType = new JComboBox<String>();
        SearchType.setBounds(250, 10, 150, 30);
        // Add types to the SearchType
        SearchType.addItem("All");
        SearchType.addItem("Action");
        SearchType.addItem("Romance");

        JButton SearchBtn = new JButton("Search");
        SearchBtn.setBounds(500, 10, 100, 30);

        this.add(SearchBar);
        this.add(SearchType);
        this.add(SearchBtn);
    }
}
