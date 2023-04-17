package account;

import GAMELAB.Layout;
import system.userDAO;

import javax.swing.*;

public class Signup {
    JFrame frame = new JFrame("Sign Up");
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
        JLabel LabelPasswd = new JLabel("Password", SwingConstants.RIGHT);
        JLabel LabelEmail = new JLabel("Email", SwingConstants.RIGHT);
        JLabel LabelPhone = new JLabel("Phone", SwingConstants.RIGHT);

        JTextField TextName = new JTextField(5);
        JPasswordField TextPasswd = new JPasswordField(5);
        JTextField TextEmail = new JTextField(5);
        JTextField TextPhone = new JTextField(5);

        LabelName.setBounds(80, 10, 80, 40);
        TextName.setBounds(180, 10, 150, 40);

        LabelPasswd.setBounds(80, 60, 80, 40);
        TextPasswd.setBounds(180, 60, 150, 40);

        LabelEmail.setBounds(80, 110, 80, 40);
        TextEmail.setBounds(180, 110, 150, 40);

        LabelPhone.setBounds(80, 160, 80, 40);
        TextPhone.setBounds(180, 160, 150, 40);

        // btn for Signup
        JButton BtnSignup = new JButton("Signup");
        BtnSignup.setBounds(210, 210, 80, 40);
        BtnSignup.addActionListener((e) -> {

            // Get the input from the JTextField and JPasswordField
            String username = TextName.getText();
            char[] passwordCharArray = TextPasswd.getPassword();
            String password = new String(passwordCharArray);
            String email = TextEmail.getText();
            String phone = TextPhone.getText();

            boolean Signup = user.Signup(username, password, email, phone);
            boolean exist = user.usernameExists(username);

            if (Signup && exist) {
                // Sign up successfully
                JOptionPane.showMessageDialog(panel, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new Login().init();
                frame.dispose();

            } else {
                // Wrong passwd or username
                JOptionPane.showMessageDialog(panel, "UserName already exist!", "error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(LabelName);
        panel.add(TextName);
        panel.add(TextPasswd);
        panel.add(LabelPasswd);
        panel.add(LabelEmail);
        panel.add(TextEmail);
        panel.add(LabelPhone);
        panel.add(TextPhone);
        panel.add(BtnSignup);
    }
}
