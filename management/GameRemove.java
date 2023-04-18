package management;

import system.adminDAO;

import javax.swing.*;
import java.awt.*;

public class GameRemove extends JPanel {
    JLabel Title = new JLabel("Delete a game");
    JLabel LabelGameID = new JLabel("GameID:");
    JTextField GameID = new JTextField();
    JButton BtnDelete = new JButton("Delete");

    adminDAO admin = new adminDAO();

    GameRemove() {
        init();
    }

    private void init() {
        this.setBackground(new Color(46, 46, 46));
        this.setLayout(null);
        this.setSize(200, 270);

        Title.setBounds(20, 20, 180, 50);
        Title.setForeground(Color.WHITE);
        Title.setFont(new Font("Serif", Font.PLAIN, 25));

        LabelGameID.setBounds(20, 130, 80, 40);
        LabelGameID.setForeground(Color.WHITE);
        GameID.setBounds(110, 130, 70, 40);
        BtnDelete.setBounds(20, 200, 160, 50);
        BtnDelete.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnDelete.setForeground(Color.WHITE);

        BtnDelete.addActionListener((e) -> {
            // Delete(GamerID.getText())
            int gameID = Integer.parseInt(GameID.getText());
            boolean isDeleted = admin.deleteGame(gameID);
            if (isDeleted) {
                GameID.setText("");
                JOptionPane.showMessageDialog(this, "Delete successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(this, "GameID doesn't exist", "error", JOptionPane.ERROR_MESSAGE);
            }

        });

        this.add(Title);
        this.add(LabelGameID);
        this.add(GameID);
        this.add(BtnDelete);
    }
}
