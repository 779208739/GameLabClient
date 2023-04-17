package GAMELAB;

import account.Login;
import entity.Gamer;
import system.gamerDAO;

import java.awt.*;
import javax.swing.*;

public class Header {
    JPanel panel = new JPanel();

    gamerDAO gamer = new gamerDAO();

    public JPanel init(JFrame frame) {
        setPanel(panel, frame);

        return panel;
    }

    private void setPanel(JPanel panel, JFrame frame) {

        Gamer gamer0 = gamer.getGamerInfo();

        panel.setLayout(null);
        panel.setBackground(Color.lightGray);
        JLabel LabelUsername = new JLabel(gamer0.getUserName(), SwingConstants.RIGHT);
        LabelUsername.setBounds(800, 0, 60, 50);

        JButton BtnLogout = new JButton("Logout");
        BtnLogout.setBounds(900, 10, 100, 30);

        BtnLogout.addActionListener((e) -> {
            // LogOut
            frame.dispose();
            new Login().init();

        });

        panel.add(BtnLogout);
        panel.add(LabelUsername);
    }
}
