package TankMovement;

import javax.swing.JPanel;

public class TankController implements Runnable {

    private JPanel panel;
    private Tank tank;


    private double turningAngle;
    private double speed;

    private boolean turningRight;
    private boolean turningLeft;

    private boolean movingForward;
    private boolean movingBackwards;

    public TankController(JPanel panel, Tank tank) {

        this.panel = panel;
        this.tank = tank;
    }

    public void startMoving() {

        Thread thread = new Thread(this);
        thread.start();
    }

    public void setMovingForward(boolean forward) {

        movingForward = forward;

        if (movingForward) {
            speed = .1f;
            return;
        }
         
        speed = -.1f;
    }

    public void setMovingBackwards(boolean backwards) {

        movingBackwards = backwards;

        if (movingBackwards) {
            speed = -.1f;
            return;
        }
         
        speed = .1f;
    }

    public void setRight(boolean right) {

        this.turningRight = right;

        if (turningRight) {
            turningAngle = 2f;
            return;
        }
        
        turningAngle = -2f;
    }

    public void setLeft(boolean left) {

        this.turningLeft = left;

        if (turningLeft) {
            turningAngle = -2f;
            return;
        }
        
        turningAngle = 2f;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(10);
            } catch (Exception e) {}

            if (movingForward || movingBackwards)
                tank.move(speed);

            if (turningLeft || turningRight)
                tank.rotate(turningAngle);

            panel.repaint();
        }
    }
    
}
