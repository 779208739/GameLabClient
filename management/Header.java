package management;

import account.Login;
import entity.Gamer;
import system.gamerDAO;

import java.awt.*;
import javax.swing.*;

public class Header {
    JPanel panel = new JPanel();

    public JPanel init(JFrame frame) {
        setPanel(panel, frame);

        return panel;
    }

    private void setPanel(JPanel panel, JFrame frame) {

        panel.setLayout(null);
        panel.setBackground(new Color(46, 46, 46));
        JLabel Username = new JLabel("Administrator", SwingConstants.RIGHT);
        Username.setForeground(Color.WHITE);
        Username.setBounds(780, 10, 100, 30);

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
        panel.add(Username);
    }
}
