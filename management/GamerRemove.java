package management;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class GamerRemove extends JPanel {
    JLabel Title = new JLabel("Delete a gamer");
    JLabel LabelGamerID = new JLabel("GamerID:");
    JTextField GamerID = new JTextField();
    JButton BtnDelete = new JButton("Delete");

    GamerRemove() {
        init();
    }

    private void init() {
        this.setBackground(Color.GRAY);
        this.setLayout(null);
        this.setSize(200, 550);

        Title.setBounds(20, 20, 180, 50);
        Title.setFont(new Font("Serif", Font.PLAIN, 20));

        LabelGamerID.setBounds(20, 410, 80, 40);
        GamerID.setBounds(110, 410, 70, 40);
        BtnDelete.setBounds(20, 480, 160, 50);

        BtnDelete.addActionListener((e) -> {
            // Delete(GamerID.getText())
        });

        this.add(Title);
        this.add(LabelGamerID);
        this.add(GamerID);
        this.add(BtnDelete);
    }
}
