package account;

import GAMELAB.Layout;
import system.userDAO;

import javax.swing.*;

public class Reset {
    JFrame frame = new JFrame("Reset Password");
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

        JLabel LabelName = new JLabel("Username", SwingConstants.RIGHT);
        JLabel LabelPasswd = new JLabel("New Password", SwingConstants.RIGHT);

        JTextField TextName = new JTextField(5);
        JPasswordField TextPasswd = new JPasswordField(5);

        LabelName.setBounds(80, 50, 80, 40);
        TextName.setBounds(180, 50, 150, 40);

        LabelPasswd.setBounds(60, 110, 100, 40);
        TextPasswd.setBounds(180, 110, 150, 40);

        // btn for reset
        JButton BtnReset = new JButton("Reset");
        BtnReset.setBounds(210, 180, 80, 40);
        BtnReset.addActionListener((e) -> {

            // Get the input from the JTextField and JPasswordField
            String username = TextName.getText();
            char[] passwordCharArray = TextPasswd.getPassword();
            String password = new String(passwordCharArray);

            boolean reset = user.update(username, password);

            if (reset) {

                JOptionPane.showMessageDialog(panel, "Reset password successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new Login().init();
                frame.dispose();

            } else {
                // Wrong passwd or username
                JOptionPane.showMessageDialog(panel, "Wrong Password or Username", "error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(LabelName);
        panel.add(TextName);
        panel.add(TextPasswd);
        panel.add(LabelPasswd);
        panel.add(BtnReset);
    }
}
