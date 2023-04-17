package management;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
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
    JLabel labelType = new JLabel("Type: ");
    JLabel labelImg = new JLabel("Image: ");
    JLabel labelKeywords = new JLabel("Keywords: ");

    JTextField Name = new JTextField();
    JTextArea Description = new JTextArea();
    JTextField Type = new JTextField();
    JTextField ImgPath = new JTextField("No image selected");
    JFileChooser fc = new JFileChooser();
    JButton BtnAddImg = new JButton("+");
    JButton BtnAddKeywords = new JButton("+");
    JButton BtnSave = new JButton("Save");

    List<JTextField> Keywords = new ArrayList<>();

    public void init() {
        this.setBackground(Color.GRAY);
        this.setSize(600, 470);
        this.setLayout(null);

        labelName.setBounds(100, 20, 200, 30);
        labelDes.setBounds(100, 100, 200, 30);
        labelType.setBounds(100, 220, 200, 30);
        labelImg.setBounds(100, 300, 200, 30);
        labelKeywords.setBounds(100, 380, 200, 30);

        Name.setBounds(100, 50, 300, 40);
        Description.setBounds(100, 130, 300, 80);
        Type.setBounds(100, 250, 300, 40);
        ImgPath.setBounds(100, 330, 300, 40);
        BtnAddImg.setBounds(40, 335, 40, 30);
        BtnAddKeywords.setBounds(40, 415, 40, 30);
        BtnSave.setBounds(420, 400, 160, 50);

        LoadImg("static/pixel_test.png");

        BtnAddImg.addActionListener((e) -> {
            getImgPath();
        });

        BtnAddKeywords.addActionListener((e) -> {
            JTextField OneKeyword = new JTextField();
            OneKeyword.setBounds(100 + 75 * Keywords.size(), 410, 70, 40);
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

            if (true) {
                // Successfully add game
                JOptionPane.showMessageDialog(this, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);

                Name.setText("");
                Description.setText("");
                Type.setText("");
                ImgPath.setText("No image selected");

                Component[] componentList = this.getComponents();
                for (Component c : componentList) {
                    if (c.getName() == "keyword")
                        this.remove(c);
                }
                LoadImg("static/pixel_test.png");

            } else {
                JOptionPane.showMessageDialog(this, "Add fails!", "Failure", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.add(labelName);
        this.add(labelDes);
        this.add(labelImg);
        this.add(labelType);
        this.add(labelKeywords);
        this.add(ImgPath);

        this.add(Name);
        this.add(Description);
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
        thumbnail.setBounds(420, 210, 160, 160);
        this.add(thumbnail);

        this.revalidate();
        this.repaint();
    }

}
