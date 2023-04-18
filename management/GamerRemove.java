package management;

import system.adminDAO;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class GamerRemove extends JPanel {
    JLabel Title = new JLabel("Delete a gamer");
    JLabel LabelGamerID = new JLabel("GamerID:");
    JTextField GamerID = new JTextField();
    JButton BtnDelete = new JButton("Delete");

    adminDAO admin = new adminDAO();

    GamerRemove() {
        init();
    }

    private void init() {
        this.setBackground(new Color(46, 46, 46));
        this.setLayout(null);
        this.setSize(200, 270);

        Title.setBounds(20, 20, 180, 50);
        Title.setFont(new Font("Serif", Font.PLAIN, 25));
        Title.setForeground(Color.WHITE);

        LabelGamerID.setBounds(20, 130, 80, 40);
        LabelGamerID.setForeground(Color.WHITE);
        GamerID.setBounds(110, 130, 70, 40);
        BtnDelete.setBounds(20, 200, 160, 50);
        BtnDelete.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnDelete.setForeground(Color.WHITE);

        BtnDelete.addActionListener((e) -> {
            // Delete(GamerID.getText())
            int gamerID = Integer.parseInt(GamerID.getText());
            boolean isDeleted = admin.deleteGamer(gamerID);
            if (isDeleted) {
                GamerID.setText("");
                JOptionPane.showMessageDialog(this, "Delete successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(this, "GamerID doesn't exist", "error", JOptionPane.ERROR_MESSAGE);
            }

        });

        this.add(Title);
        this.add(LabelGamerID);
        this.add(GamerID);
        this.add(BtnDelete);
    }
}
