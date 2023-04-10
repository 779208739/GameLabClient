package GAMELAB;

import java.awt.*;
import javax.swing.*;

public class Header {
    JPanel panel = new JPanel();

    public JPanel init() {
        setPanel(panel);

        return panel;
    }

    private void setPanel(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);
        JLabel LabelUsername = new JLabel("jack", SwingConstants.RIGHT);
        LabelUsername.setBounds(800, 0, 60, 50);

        JButton BtnLogout = new JButton("Logout");
        BtnLogout.setBounds(900, 10, 100, 30);

        BtnLogout.addActionListener((e) -> {
            System.out.println("logout");
            // LogOut
        });

        panel.add(BtnLogout);
        panel.add(LabelUsername);
    }
}
