package Problem12;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


class Circle {
    int x, y;
    int r;    

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}

public class DragToDrawCircle extends JFrame {

    public DragToDrawCircle() {
        setTitle("마우스 클릭-드래그로 원 그리기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DrawPanel());
        setSize(600, 600);
        setVisible(true);
    }

    class DrawPanel extends JPanel {
        Vector<Circle> circles = new Vector<>();
        int centerX, centerY;    
        int currentRadius = 0; 
        boolean drawing = false;
        public DrawPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    centerX = e.getX();
                    centerY = e.getY();
                    currentRadius = 0;
                    drawing = true; 
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (drawing && currentRadius > 0) {
                        
                        circles.add(new Circle(centerX, centerY, currentRadius));
                    }
                    drawing = false;
                    currentRadius = 0;
                    repaint();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (drawing) {
                        int dx = e.getX() - centerX;
                        int dy = e.getY() - centerY;
                        currentRadius = (int)Math.sqrt(dx * dx + dy * dy); 
                        repaint();
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(new Color(160, 60, 160)); 

            
            for (Circle c : circles) {
                g.drawOval(c.x - c.r, c.y - c.r, c.r * 2, c.r * 2);
            }

            if (drawing && currentRadius > 0) {
                g.drawOval(centerX - currentRadius, centerY - currentRadius,
                           currentRadius * 2, currentRadius * 2);
            }
        }
    }

    public static void main(String[] args) {
        new DragToDrawCircle();
    }
}
