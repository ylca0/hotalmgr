package top.ylcao.hotalmgr;

import javax.swing.*;
import java.awt.*;

public class Statistics extends JFrame {

    public Statistics() {
        super("统计");
        add(new Histogram());
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    class Histogram extends JPanel {
        int HEIGHT = 250;
        int offsetY = 14;
        Font font = new Font("Times", Font.PLAIN, 14);

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            HEIGHT = (int) (getHeight());
            int width = getWidth();
            int w80 = (int) (width * 0.8);
            int w20 = (int) (width * 0.2);
            int uw = w80 / 4;
            int gap = w20 / 5;

            int bottom = getHeight() - 20;
            int rectH = (int) (HEIGHT * 0.2);
            String str;
            g.setFont(font);

            g.setColor(Color.RED);
            g.fillRect(gap, bottom - rectH, uw, rectH);
            g.setColor(Color.BLACK);
            str = "Project -- 20%";
            g.drawString(str, gap, bottom - rectH - offsetY);

            rectH = (int) (HEIGHT * 0.1);
            g.setColor(Color.BLUE);
            g.fillRect(gap * 2 + uw, bottom - rectH, uw, rectH);
            g.setColor(Color.BLACK);
            str = "Quizzes -- 10%";
            g.drawString(str, gap * 2 + uw, bottom - rectH - offsetY);

            rectH = (int) (HEIGHT * 0.3);
            g.setColor(Color.GREEN);
            g.fillRect(gap * 3 + uw * 2, bottom - rectH, uw, rectH);
            g.setColor(Color.BLACK);
            str = "Midtems -- 30%";
            g.drawString(str, gap * 3 + uw * 2, bottom - rectH - offsetY);

            rectH = (int) (HEIGHT * 0.4);
            g.setColor(Color.ORANGE);
            g.fillRect(gap * 4 + uw * 3, bottom - rectH, uw, rectH);
            g.setColor(Color.BLACK);
            str = "Final -- 40%";
            g.drawString(str, gap * 4 + uw * 3, bottom - rectH - offsetY);

            int x1, x2, y1, y2;
            x1 = gap / 2;
            y1 = bottom;
            x2 = width - gap / 2;
            y2 = y1;
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);

        }
    }
}
