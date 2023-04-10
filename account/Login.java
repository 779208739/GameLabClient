package account;

import javax.swing.*;

public class Login {
    JFrame frame = new JFrame("Game Lab");
    JPanel mypanel = new JPanel();

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

        JLabel LabelLogin = new JLabel("user", SwingConstants.RIGHT);
        JLabel LabelPasswd = new JLabel("Password", SwingConstants.RIGHT);

        JTextField TextLogin = new JTextField(5);
        JPasswordField TextPasswd = new JPasswordField(5);

        LabelLogin.setBounds(80, 50, 80, 40);
        TextLogin.setBounds(180, 50, 150, 40);

        LabelPasswd.setBounds(80, 110, 80, 40);
        TextPasswd.setBounds(180, 110, 150, 40);

        // btn for login
        JButton BtnLogin = new JButton("login");
        BtnLogin.setBounds(210, 180, 80, 40);
        BtnLogin.addActionListener((e) -> {
            // verify the username & password while click login
            if (false) {
                // Login successfully
            } else {
                // Wrong passwd or username
                JOptionPane.showMessageDialog(panel, "Wrong Password or Username", "error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(LabelLogin);
        panel.add(TextLogin);
        panel.add(TextPasswd);
        panel.add(LabelPasswd);
        panel.add(BtnLogin);
    }

    public static void main(String[] args) {
        new Login().init();
    }
}
