
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Jaiking extends JFrame {

    private boolean drawLines = false;
    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Point> smoothPoints = new ArrayList<>();
    private int smoothIteration = 0;
    private final int MAX_ITERATIONS = 7;
    private Timer smoothTimer;
    private boolean inputLocked = false;

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
                if (!inputLocked) {
                    points.add(e.getPoint());
                    panel.repaint();
                }
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
                    points.clear();
                    smoothPoints.clear();
                    drawLines = false;
                    inputLocked = false;
                    if (smoothTimer != null) {
                        smoothTimer.stop();
                    }
                    panel.repaint();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER && !inputLocked) {
                    inputLocked = true;
                    drawLines = true;
                    panel.repaint();

                    if (points.size() <= 2) {
                        smoothPoints = new ArrayList<>(points);
                        if (smoothTimer != null) {
                            smoothTimer.stop();
                        }
                    } else {
                        smoothIteration = 0;
                        smoothPoints = new ArrayList<>(points);

                        smoothTimer = new Timer(500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                if (smoothIteration < MAX_ITERATIONS) {
                                    smoothPoints = chaikinAlgorithm(smoothPoints);
                                    smoothIteration++;
                                    panel.repaint();
                                } else {
                                    smoothPoints = new ArrayList<>(points);
                                    smoothIteration = 0;
                                }
                                panel.repaint();
                            }
                        });

                        smoothTimer.start();
                    }

                }
            }
        });
    }

    private ArrayList<Point> chaikinAlgorithm(ArrayList<Point> input) {
        if (input.size() <= 2) {
            return new ArrayList<>(input);
        }

        ArrayList<Point> newPoints = new ArrayList<>();
        newPoints.add(input.get(0));

        for (int i = 0; i < input.size() - 1; i++) {
            Point p1 = input.get(i);
            Point p2 = input.get(i + 1);

            int qx = Math.round(0.75f * p1.x + 0.25f * p2.x);
            int qy = Math.round(0.75f * p1.y + 0.25f * p2.y);
            int rx = Math.round(0.25f * p1.x + 0.75f * p2.x);
            int ry = Math.round(0.25f * p1.y + 0.75f * p2.y);

            newPoints.add(new Point(qx, qy));
            newPoints.add(new Point(rx, ry));
        }

        newPoints.add(input.get(input.size() - 1));
        return newPoints;
    }

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
                for (int i = 0; i < smoothPoints.size() - 1; i++) {
                    Point p1 = smoothPoints.get(i);
                    Point p2 = smoothPoints.get(i + 1);
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
