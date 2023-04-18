package account;

import GAMELAB.Layout;

import system.gamerDAO;
import system.userDAO;

import java.awt.Color;
import javax.swing.*;

public class Login {
    JFrame frame = new JFrame("Game Lab");
    JPanel mypanel = new JPanel();

    userDAO user = new userDAO();

    public void init() {
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        frame.add(mypanel);
        setPanel(mypanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private void setPanel(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(new Color(46, 46, 46));

        JLabel LabelLogin = new JLabel("Username", SwingConstants.RIGHT);
        JLabel LabelPasswd = new JLabel("Password", SwingConstants.RIGHT);

        JTextField TextLogin = new JTextField(5);
        JPasswordField TextPasswd = new JPasswordField(5);

        LabelLogin.setBounds(80, 50, 80, 40);
        LabelLogin.setForeground(Color.WHITE);
        TextLogin.setBounds(180, 50, 150, 40);

        LabelPasswd.setBounds(80, 110, 80, 40);
        LabelPasswd.setForeground(Color.WHITE);
        TextPasswd.setBounds(180, 110, 150, 40);

        // btn for login
        JButton BtnLogin = new JButton("Login");
        BtnLogin.setBounds(210, 180, 80, 40);
        BtnLogin.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnLogin.setForeground(Color.WHITE);
        BtnLogin.addActionListener((e) -> {

            // Get the input from the JTextField and JPasswordField
            String username = TextLogin.getText();
            char[] passwordCharArray = TextPasswd.getPassword();
            String password = new String(passwordCharArray);

            boolean Login = user.Login(username, password);
            boolean isGamer = user.isGamer();
            // verify the username & password while click login
            if (Login == true && isGamer == true) {
                // Login successfully
                new Layout().init();
                frame.dispose();

            } else {
                // Wrong passwd or username
                JOptionPane.showMessageDialog(panel, "Wrong Password or Username", "error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // btn for signup
        JButton BtnSignup = new JButton("Signup");
        BtnSignup.setBounds(80, 180, 80, 40);
        BtnSignup.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnSignup.setForeground(Color.WHITE);
        BtnSignup.addActionListener(e -> {
            new Signup().init();
            frame.dispose();
        });

        // btn for reset
        JButton BtnReset = new JButton("Reset");
        BtnReset.setBounds(340, 180, 80, 40);
        BtnReset.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnReset.setForeground(Color.WHITE);
        BtnReset.addActionListener(e -> {
            new Reset().init();
            frame.dispose();
        });

        panel.add(LabelLogin);
        panel.add(TextLogin);
        panel.add(TextPasswd);
        panel.add(LabelPasswd);
        panel.add(BtnLogin);
        panel.add(BtnSignup);
        panel.add(BtnReset);
    }

}
