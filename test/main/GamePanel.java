package main;

import main.*;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    
    private final MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private Color color = new Color(50, 150, 180);
    private final Random random;
    
    private final ArrayList<MyRect> rects = new ArrayList<>();
    
    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    
    public void changeXDelta(int value) {
        this.xDelta += value;
    }
    
    public void changeYDelta(int value) {
        this.yDelta += value;
    }
    
    public void setReactPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }
    
    public void spawnRect(int x, int y) {
        rects.add(new MyRect(x, y));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (MyRect rect : rects) {
            rect.updateRect();
            rect.draw(g);
        }
        
        updateRectangle();
        
        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 200, 50);
    }

    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta > 200 || xDelta < 0) {
            xDir *= -1;
            color = getRndColor();
        }
        yDelta += yDir;
        if(yDelta > 320 || yDelta < 0) {
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        
        return new Color(r,g,b);
    }
    
    public class MyRect {
        int x, y, w, h;
        int xDir = 1, yDir = 1;
        Color color;
        
        public MyRect(int x, int y) {
            this.x = x;
            this.y = y;
            w = random.nextInt(100);
            h = w;
            color = newColor();
        }
        
        public void updateRect() {
            this.x += xDir;
            this.y += yDir;
            
            if ((x + w) > 400 || x < 0) {
                xDir *= -1;
                color = newColor();
            }
            if ((y + h) > 400 || y < 0) {
                yDir *= -1;
                color = newColor();
            }
        }
        
        private Color newColor() {
            return new Color(random.nextInt(255), random.nextInt(255),  random.nextInt(255));
        }

        private void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, w, h);
        }
    }
}
