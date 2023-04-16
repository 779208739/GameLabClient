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
        this.setSize(500, 30);

        this.NextPage = new JButton("Next");
        this.PreviousPage = new JButton("Previous");
        this.PreviousPage.setEnabled(false);
        this.LabelPage = new JLabel(PageNow + " / " + PageSize);

        this.NextPage.setBounds(400, 0, 100, 30);
        this.PreviousPage.setBounds(0, 0, 100, 30);
        this.LabelPage.setBounds(230, 0, 100, 30);

        this.add(LabelPage);
        this.add(NextPage);
        this.add(PreviousPage);

    }

    public void updateLabel() {
        this.LabelPage.setText(PageNow + " / " + PageSize);
    }

}
