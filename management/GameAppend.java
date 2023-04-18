package management;

import system.adminDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class GameAppend extends JPanel {

    BufferedImage ImgSource;
    Image Img;

    JLabel labelName = new JLabel("Name: ");
    JLabel labelDes = new JLabel("Description: ");
    JLabel labelPrice = new JLabel("Price: ");
    JLabel labelType = new JLabel("Type: ");
    JLabel labelImg = new JLabel("Image: ");
    JLabel labelKeywords = new JLabel("Keywords: ");
    JLabel Title = new JLabel("Add a game", SwingConstants.RIGHT);

    JTextField Name = new JTextField();
    JTextArea Description = new JTextArea();
    JTextField Price = new JTextField();
    JTextField Type = new JTextField();
    JTextField ImgPath = new JTextField("No image selected");
    JFileChooser fc = new JFileChooser();
    JButton BtnAddImg = new JButton("+");
    JButton BtnAddKeywords = new JButton("+");
    JButton BtnSave = new JButton("Save");

    List<JTextField> Keywords = new ArrayList<>();

    adminDAO admin = new adminDAO();

    GameAppend() {
        init();
    }

    private void init() {
        this.setBackground(new Color(46, 46, 46));
        this.setSize(600, 550);
        this.setLayout(null);

        labelName.setBounds(100, 20, 200, 30);
        labelName.setForeground(Color.WHITE);
        labelDes.setBounds(100, 100, 200, 30);
        labelDes.setForeground(Color.WHITE);
        labelPrice.setBounds(100, 220, 200, 30);
        labelPrice.setForeground(Color.WHITE);
        labelType.setBounds(100, 300, 200, 30);
        labelType.setForeground(Color.WHITE);
        labelImg.setBounds(100, 380, 200, 30);
        labelImg.setForeground(Color.WHITE);
        labelKeywords.setBounds(100, 460, 200, 30);
        labelKeywords.setForeground(Color.WHITE);
        Title.setBounds(400, 20, 180, 50);
        Title.setFont(new Font("Serif", Font.PLAIN, 25));
        Title.setForeground(Color.WHITE);

        Name.setBounds(100, 50, 300, 40);
        Description.setBounds(100, 130, 300, 80);
        Price.setBounds(100, 250, 300, 40);
        Type.setBounds(100, 330, 300, 40);
        ImgPath.setBounds(100, 410, 300, 40);

        BtnAddImg.setBounds(40, 415, 40, 30);
        BtnAddImg.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnAddImg.setForeground(Color.WHITE);
        BtnAddKeywords.setBounds(40, 495, 40, 30);
        BtnAddKeywords.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnAddKeywords.setForeground(Color.WHITE);
        BtnSave.setBounds(420, 480, 160, 50);
        BtnSave.setBorder(BorderFactory.createLineBorder(new Color(221, 148, 53)));
        BtnSave.setForeground(Color.WHITE);

        Description.setLineWrap(true);
        Description.setWrapStyleWord(true);

        LoadImg("static/pixel_test.png");

        BtnAddImg.addActionListener((e) -> {
            getImgPath();
        });

        BtnAddKeywords.addActionListener((e) -> {
            JTextField OneKeyword = new JTextField();
            OneKeyword.setBounds(100 + 75 * Keywords.size(), 490, 70, 40);
            OneKeyword.setName("keyword");
            Keywords.add(OneKeyword);
            this.add(OneKeyword);
            this.repaint();
            // Size of keywords should less than 4
            if (Keywords.size() > 3)
                BtnAddKeywords.setEnabled(false);
        });

        BtnSave.addActionListener((e) -> {
            // Add Game;

            String name = Name.getText();
            String description = Description.getText();
            Double price = Double.valueOf(Price.getText());
            String type = Type.getText();

            List<String> image = new ArrayList<>();
            image.add(ImgPath.getText());

            List<String> keyword = new ArrayList<>();
            for (JTextField Keyword : Keywords) {
                keyword.add(Keyword.getText());
            }

            boolean isAdded = admin.addGame(name, description, price, type, image, keyword);

            if (isAdded) {
                // Successfully add game
                JOptionPane.showMessageDialog(this, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);

                Name.setText("");
                Description.setText("");
                Price.setText("");
                Type.setText("");
                ImgPath.setText("No image selected");

                Component[] componentList = this.getComponents();
                for (Component c : componentList) {
                    if (c.getName() == "keyword")
                        this.remove(c);
                }
                Keywords.clear(); // Clear the Keywords list
                BtnAddKeywords.setEnabled(true); // Enable the button for adding new keywords
                LoadImg("static/pixel_test.png");

            } else {
                JOptionPane.showMessageDialog(this, "Add fails!", "Failure", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.add(labelName);
        this.add(labelDes);
        this.add(labelPrice);
        this.add(labelImg);
        this.add(labelType);
        this.add(labelKeywords);
        this.add(ImgPath);
        this.add(Title);

        this.add(Name);
        this.add(Description);
        this.add(Price);
        this.add(Type);
        this.add(BtnAddImg);
        this.add(BtnAddKeywords);
        this.add(BtnSave);

    }

    private void getImgPath() {
        FileFilter imageFilter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());

        fc.setFileFilter(imageFilter);
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            ImgPath.setText(fc.getSelectedFile().getAbsolutePath());

            LoadImg(ImgPath.getText());
        }
    }

    // Load Image after open the image
    private void LoadImg(String path) {
        try {

            ImgSource = ImageIO.read(new File(path));
        } catch (IOException ex) {
            System.out.println(new File(path).getPath());
            System.out.println(ex);
        }

        this.Img = ImgSource.getScaledInstance(160, 160, Image.SCALE_DEFAULT);
        // Change the img
        // Remove the old img from the panel
        Component[] componentList = this.getComponents();
        for (Component c : componentList) {
            if (c.getName() == "thumbnail")
                this.remove(c);
        }
        // Add new img to the panel
        JLabel thumbnail = new JLabel(new ImageIcon(Img));
        thumbnail.setName("thumbnail");
        thumbnail.setBounds(420, 290, 160, 160);
        this.add(thumbnail);

        this.revalidate();
        this.repaint();
    }

}
