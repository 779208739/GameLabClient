package GAMELAB;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// This is the game card class
// Game card briefly show the basic game information,
public class GameCard extends JPanel {

    String ImgPath = "static/TestImg.jpg";
    BufferedImage ImgSource;
    Image Img;

    String Type = "Type";
    String KeyWords = "this is a set of keywords";

    GameCard(String ImgPath, String Type, String keyWords) {
        this.ImgPath = ImgPath;
        this.Type = Type;
        this.KeyWords = keyWords;
    }

    GameCard() {
        InitGame();
    }

    private void LoadImg() {
        try {

            ImgSource = ImageIO.read(new File(ImgPath));
        } catch (IOException ex) {
            System.out.println(new File(ImgPath).getPath());
            System.out.println(ex);
        }

        this.Img = ImgSource.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
    }

    public void InitGame() {
        LoadImg();

        this.setLayout(null);
        this.setBackground(Color.orange);
        this.setSize(500, 100);

        JLabel LabelImg = new JLabel(new ImageIcon(this.Img));
        LabelImg.setBounds(25, 10, 80, 80);

        JLabel LabelTitle = new JLabel("name");
        LabelTitle.setFont(new Font("Serif", Font.PLAIN, 35));
        LabelTitle.setBounds(150, 10, 200, 25);

        JLabel LabelType = new JLabel(this.Type);
        LabelType.setBounds(150, 45, 400, 25);

        JLabel LabelKeyWord = new JLabel(this.KeyWords);
        LabelKeyWord.setBounds(150, 70, 400, 25);

        this.add(LabelImg);
        this.add(LabelTitle);
        this.add(LabelType);
        this.add(LabelKeyWord);
    }

}
