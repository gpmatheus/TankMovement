package TankMovement;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel implements KeyListener {

    private static final int size = 900;
    private static final int parameter = 40;
    
    private Tank tank = new Tank();
    private TankController tm = new TankController(this, tank);

    public static void main(String[] args) {

        Display display = new Display();
        display.startRunning();
    }

    public void startRunning() {
        tm.startMoving();
    }

    public Display() {

        setBackground(Color.BLACK);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size, size);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(this);
        frame.addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        paintTank(g);
    }

    private void paintTank(Graphics g) {

        double[][][] tankPoints = tank.getTankPoints();

        double x;
        double y;

        for (int i = 0; i < tankPoints.length; i++) {

            x = tankPoints[i][0][0] * parameter + size / 2;
            y = (tankPoints[i][1][0] * parameter) * -1 + size / 2;

            g.setColor(Color.GREEN);
            g.drawLine((int) x,(int) y,(int) x,(int) y);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyChar()) {
            case 'w':

                tm.setMovingForward(true);
                break;
            case 's':

                tm.setMovingBackwards(true);
                break;
            case 'a':

                tm.setRight(true);
                break;
            case 'd':

                tm.setLeft(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        switch (e.getKeyChar()) {
            case 'w':

                tm.setMovingForward(false);
                break;
            case 's':

                tm.setMovingBackwards(false);
                break;
            case 'a':

                tm.setRight(false);
                break;
            case 'd':

                tm.setLeft(false);
                break;
        }
    }

    
}
