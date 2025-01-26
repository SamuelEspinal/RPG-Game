package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {

	public GamePanel gamePanel;
	public int worldX, worldY;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	// FOR MOBS
	public BufferedImage up3, up4, up5, up6, down3, down4, down5, down6, left3, left4, left5, left6, right3, right4, right5, right6;
	public String direction = "down";
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
	public int hitboxDefaultX, hitboxDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	public BufferedImage image, image2, image3, image4, image5, image6;
	public String name;
	public boolean collision = false;

	// CHARACTER or MONSTER HEALTH STATUS
	public int maxLife;
	public int life;

	public Entity(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void setAction() {}

	public void speak() {
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;

	}
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
		if(spriteCounter > 12/* Change this value to change speed of animation */) {
			if(spriteNum == 1 ){spriteNum = 2;}
			else if (spriteNum == 2){spriteNum = 1;}
			spriteCounter = 0;
		}

	}
	public void draw(Graphics2D graphics2D) {

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
					break;
				case "down":
					if(spriteNum == 1) {image = down1;}
					if(spriteNum == 2) {image = down2;}
					break;
				case "left":
					if(spriteNum == 1) {image = left1;}
					if(spriteNum == 2) {image = left2;}
					break;
				case "right":
					if(spriteNum == 1) {image = right1;}
					if(spriteNum == 2) {image = right2;}
					break;
				case "stillFrame":
					//image = stillFrame;
			}

			graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
		}
	}
	public BufferedImage setup(String imagePath) {
		UtilityTool utilityTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
			image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
		}catch(IOException error) {
			error.printStackTrace();
		}
		return image;
	}
}
