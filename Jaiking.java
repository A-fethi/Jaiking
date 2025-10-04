
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Jaiking extends JFrame {

    private boolean drawLines = false;
    private ArrayList<Point> points = new ArrayList<>();

    public Jaiking() {
        setTitle("Jaiking");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingPanel panel = new DrawingPanel();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        add(panel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());
                panel.repaint();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
                    points.clear();
                    drawLines = false;
                    panel.repaint();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("Enter Clicked");
                    drawLines = true;
                    panel.repaint();
                }
            }
        });
    }

    // private void startAnimation() {
    //     // Placeholder for animation logic
    //     System.out.println("Starting animation with " + points.size() + " points.");
    // }
    private class DrawingPanel extends JPanel {

        public DrawingPanel() {
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GRAY);
            drawPoints(g);
            drawLines(g);
        }

        private void drawPoints(Graphics g) {
            for (Point p : points) {
                g.drawOval(p.x - 3, p.y - 3, 6, 6);
            }
        }

        private void drawLines(Graphics g) {
            if (drawLines) {
                for (int i = 0; i < points.size() - 1; i++) {
                    Point p1 = points.get(i);
                    Point p2 = points.get(i + 1);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jaiking drawer = new Jaiking();
            drawer.setVisible(true);
        });
    }
}
