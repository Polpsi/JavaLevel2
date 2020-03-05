package circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private Sprite[] sprites = new Sprite[10];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        initApplication();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (e.getButton()==MouseEvent.BUTTON1){
                    addBall();
                } else if (e.getButton()==MouseEvent.BUTTON3) {
                    removeFirstBall();
                }
            }
        });
        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        setVisible(true);
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
    }

    private void addBall(){
        Sprite[] tmpSprites= Arrays.copyOf(sprites,sprites.length+1);
        tmpSprites[tmpSprites.length-1]=new Ball();
        sprites = Arrays.copyOf(tmpSprites,tmpSprites.length);
    }

    private void removeFirstBall(){
        if (sprites.length>0){
            Sprite[] tmpSprites = Arrays.copyOfRange(sprites, 1, sprites.length);
            sprites = Arrays.copyOf(tmpSprites, tmpSprites.length);
        }
    }

    public void onCanvasRepainted(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        Background.changeColor(canvas,deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
    }

}
