package management;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import entity.Game;

import java.awt.Color;
import java.util.List;

public class GameAppend extends JPanel {

    JLabel labelName = new JLabel("Name: ");
    JLabel labelDes = new JLabel("Description: ");
    JLabel labelType = new JLabel("Type: ");
    JLabel ImgPath = new JLabel("No image selected");
    JLabel labelImg = new JLabel("Image: ");
    JLabel labelKeywords = new JLabel("Keywords: ");

    JTextField Name = new JTextField();
    JTextArea Description = new JTextArea();
    JTextField Type = new JTextField();
    JFileChooser fc = new JFileChooser();
    JButton BtnAddImg = new JButton("Add Image");
    JButton BtnAddKeywords = new JButton("+");

    List<String> Keywords;

    public void init() {
        this.setBackground(Color.GRAY);
        this.setSize(600, 500);
        this.setLayout(null);

        labelName.setBounds(100, 30, 200, 50);
        labelDes.setBounds(100, 100, 200, 50);
        labelType.setBounds(100, 280, 200, 50);
        labelImg.setBounds(100, 350, 200, 50);
        labelKeywords.setBounds(100, 420, 200, 50);

        Name.setBounds(280, 30, 200, 50);
        Description.setBounds(280, 100, 200, 160);
        Type.setBounds(280, 280, 200, 50);
        ImgPath.setBounds(280, 350, 200, 50);
        BtnAddImg.setBounds(180, 350, 80, 50);
        BtnAddKeywords.setBounds(210, 420, 50, 50);

        BtnAddImg.addActionListener((e) -> {
            getImgPath();
        });

        BtnAddKeywords.addActionListener((e) -> {
            addKeywords();
            BtnAddKeywords.setLocation(20 + 80 * Keywords.size(), 420);

            // Size of keywords should less than 4
            if (Keywords.size() > 3)
                BtnAddKeywords.setEnabled(false);
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

    }

    private void getImgPath() {
        FileFilter imageFilter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());
        fc.addChoosableFileFilter(imageFilter);

        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            ImgPath.setText(fc.getSelectedFile().getAbsolutePath());
        }
    }

    private JTextField addKeywords() {
        JTextField OneKeyword = new JTextField();
        OneKeyword.setBounds(50 * Keywords.size(), 420, 80, 50);

        return OneKeyword;
    }
}
