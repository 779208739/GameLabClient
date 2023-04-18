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
        panel.setBackground(new Color(46, 46, 46));
        JButton BtnUsername = new JButton(gamer0.getUserName());
        BtnUsername.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnUsername.setForeground(Color.WHITE);
        BtnUsername.setBounds(800, 10, 80, 30);

        BtnUsername.addActionListener(e -> {
            viewGamerInfo(gamer0);
        });

        JButton BtnLogout = new JButton("Logout");
        BtnLogout.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnLogout.setForeground(Color.WHITE);
        BtnLogout.setBounds(900, 10, 80, 30);

        BtnLogout.addActionListener((e) -> {
            // LogOut
            frame.dispose();
            new Login().init();

        });

        panel.add(BtnLogout);
        panel.add(BtnUsername);
    }

    private void viewGamerInfo(Gamer gamer) {
        JFrame frame = new JFrame("Gamer Info");

        frame.setSize(250, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(new JLabel("Username: " + gamer.getUserName()), gbc);
        gbc.gridy++;
        panel.add(new JLabel("UserID: " + gamer.getUserID()), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Email: " + gamer.getEmail()), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Phone: " + gamer.getPhone()), gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
