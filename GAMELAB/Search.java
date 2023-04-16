package GAMELAB;

import java.util.List;
import javax.swing.*;

public class Search extends JPanel {

    Boolean SearchNow = false;
    JTextField SearchBar = new JTextField();
    JComboBox<String> SearchType = new JComboBox<String>();
    JButton SearchBtn = new JButton("Search");

    public static int[] GetGameIDs(String str, List<String> Keywords) {

        int[] GameIDs = { 1, 2, 3 };
        return GameIDs;
    }

    public void init() {
        this.setLayout(null);
        this.setSize(580, 50);

        SearchBar.setBounds(80, 10, 150, 30);

        SearchType.setBounds(250, 10, 150, 30);
        // Add types to the SearchType
        SearchType.addItem("All");
        SearchType.addItem("Action");
        SearchType.addItem("Romance");

        SearchBtn.setBounds(470, 10, 100, 30);

        this.add(SearchBar);
        this.add(SearchType);
        this.add(SearchBtn);
    }
}
