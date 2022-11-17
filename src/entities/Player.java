package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstants.*;
import utilz.LoadSave;

public class Player extends Entity {
    
//    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 35;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private final float playerSpeed = 1.0f;
    
    
    
    public Player (float x, float y) {
        super(x, y);
        loadAnimations();
    }
    
    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimations();
    }
    
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animIndex], (int)x, (int)y, 67*2, 68*2, null);
    }
   
    

    private void updateAnimationTick() {
        animTick++;
        if(animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if(animIndex >= GetSpriteAmount(playerAction)) {
                animIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimations() {
        
        int startAnim = playerAction;
        
        if(moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        
        if(attacking) {
            playerAction = ATTACK_3;
        } 
        
        if(startAnim != playerAction) {
            resetAnimTick();
        }
    }
    
    private void resetAnimTick() {
        animTick = 0;
        animIndex = 0;
    }
    
    private void updatePos() {
        
        moving = false;
        
        if(left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if(right && !left) {
            x += playerSpeed;
            moving = true;
        }
        
        if(up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }
    
    public void resetDirBoolean() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
    
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
    
    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDown() {
        return down;
    }
    
    
    public void loadAnimations() {
        
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        this.setAnimations();
        
        animations = new BufferedImage[10][11];
        for(int j = 0; j < animations.length; j++)
            for(int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i*67, j*68, 67, 68);
    }
}
