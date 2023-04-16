package GAMELAB;

import javax.swing.*;

public class Pagination extends JPanel {

    int PageSize;
    int PageNow = 1;
    JButton NextPage;
    JButton PreviousPage;
    JLabel LabelPage;

    Pagination(int PageSize) {
        this.setLayout(null);
        this.PageSize = PageSize;
        this.setSize(600, 80);

        this.NextPage = new JButton("Next");
        this.PreviousPage = new JButton("Previous");
        this.PreviousPage.setEnabled(false);
        this.LabelPage = new JLabel(PageNow + " / " + PageSize);

        this.NextPage.setBounds(450, 0, 50, 50);
        this.PreviousPage.setBounds(50, 0, 50, 50);
        this.LabelPage.setBounds(200, 0, 100, 50);

        this.add(LabelPage);
        this.add(NextPage);
        this.add(PreviousPage);

    }

    public void updateLabel() {
        this.LabelPage.setText(PageNow + " / " + PageSize);
    }

}
