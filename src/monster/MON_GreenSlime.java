package monster;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);

        // Slime Stats
        name = "Green Slime";
        speed = 2;
        maxLife = 4;
        life = maxLife;

        hitbox.x = 3;
        hitbox.y = 15;
        hitbox.width = 42;
        hitbox.height = 33;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;

        getImage();

    }

    public void getImage() {

        //Animate the slime
        up1 = setup("/monster/slime/Slime1");
        up2 = setup("/monster/slime/Slime2");
        up3 = setup("/monster/slime/Slime3");
        up4 = setup("/monster/slime/Slime4");
        up5 = setup("/monster/slime/Slime5");
        up6 = setup("/monster/slime/Slime6");

        down1 = setup("/monster/slime/Slime1");
        down2 = setup("/monster/slime/Slime2");
        down3 = setup("/monster/slime/Slime3");
        down4 = setup("/monster/slime/Slime4");
        down5 = setup("/monster/slime/Slime5");
        down6 = setup("/monster/slime/Slime6");

        left1 = setup("/monster/slime/Slime1");
        left2 = setup("/monster/slime/Slime2");
        left3 = setup("/monster/slime/Slime3");
        left4 = setup("/monster/slime/Slime4");
        left5 = setup("/monster/slime/Slime5");
        left6 = setup("/monster/slime/Slime6");

        right1 = setup("/monster/slime/Slime1");
        right2 = setup("/monster/slime/Slime2");
        right3 = setup("/monster/slime/Slime3");
        right4 = setup("/monster/slime/Slime4");
        right5 = setup("/monster/slime/Slime5");
        right6 = setup("/monster/slime/Slime6");

    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Pick up a number from 1 to 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        super.draw(graphics2D);

        BufferedImage image = null;


        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if(worldX + gamePanel.tileSize> gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize< gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize> gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize< gamePanel.player.worldY + gamePanel.player.screenY) {

            switch(direction) {
                case "up":
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}
                    if(spriteNum == 3) {image = up3;}
                    if(spriteNum == 4) {image = up4;}
                    if(spriteNum == 5) {image = up5;}
                    if(spriteNum == 6) {image = up6;}
                    break;
                case "down":
                    if(spriteNum == 1) {image = down1;}
                    if(spriteNum == 2) {image = down2;}
                    if(spriteNum == 3) {image = down3;}
                    if(spriteNum == 4) {image = down4;}
                    if(spriteNum == 5) {image = down5;}
                    if(spriteNum == 6) {image = down6;}
                    break;
                case "left":
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2) {image = left2;}
                    if(spriteNum == 3) {image = left3;}
                    if(spriteNum == 4) {image = left4;}
                    if(spriteNum == 5) {image = left5;}
                    if(spriteNum == 6) {image = left6;}
                    break;
                case "right":
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2) {image = right2;}
                    if(spriteNum == 3) {image = right3;}
                    if(spriteNum == 4) {image = right4;}
                    if(spriteNum == 5) {image = right5;}
                    if(spriteNum == 6) {image = right6;}
                    break;
            }

            graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    // NEED TO UPDATE THE ANIMATION COUNTER FOR THIS CUSTOM SLIME ANIMATION
    public void update() {
        setAction();

        collisionOn = false;

        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);

        // IF COLLISION IF FALSE, PLAYER CAN MOVE
        if(!collisionOn) {
            switch(direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        // ANIMATION COUNTER SEQUENCE
        spriteCounter++;
        if(spriteCounter > 12) { //Change this value to change speed of animation
            spriteNum++;
            spriteCounter = 0;
        }
        if(spriteNum == 6) {
            spriteNum = 1;
        }

    }
}
