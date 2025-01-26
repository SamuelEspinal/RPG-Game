package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{

	KeyHandler keyHandler;
	private double velocityX = 0;
	private double velocityY = 0;

	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	int nano = 1000000000;

	public Player(GamePanel gamePanel, KeyHandler keyHandler) {

		super(gamePanel);

		this.keyHandler = keyHandler;
		screenX = gamePanel.screenWidth/2 - gamePanel.tileSize/2;
		screenY = gamePanel.screenHeight/2- gamePanel.tileSize/2;

		hitbox = new Rectangle();
		hitbox.x = 10;
		hitbox.y = 20;
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		hitbox.width = 28;
		hitbox.height = 28;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gamePanel.tileSize * 76;
		worldY = gamePanel.tileSize * 61;
		speed = 3;
		direction = "down";

		// PLAYER STATS
		maxLife = 5;
		life = maxLife;
	}

	public void getPlayerImage() {
			up1 = setup("/player/player_up_1");
			up2 = setup("/player/player_up_2");
			down1 = setup("/player/player_down_1");
			down2 = setup("/player/player_down_2");
			left1 = setup("/player/player_left_1");
			left2 =setup("/player/player_left_2");
			right1 = setup("/player/player_right_1");
			right2 = setup("/player/player_right_2");
	}

	public void update() {

		if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

			// UPDATES CHARACTER MOEVEMNT
			updateMovementDirection();

			// CHECK TILE COLLISION
			collisionOn = false;
			gamePanel.collisionChecker.checkTile(this);

			// CHECK OBJECT COLLISION
			int objIndex = gamePanel.collisionChecker.checkObject(this, true);
			pickUpObject(objIndex);

			// CHECK NPC COLLISION
			int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
			interactNPC(npcIndex);

			// CHECK EVENT COLLISION
			gamePanel.eventHandler.checkEvent();

			// IF COLLISION IF FALSE, PLAYER CAN MOVE
			if(!collisionOn) {
				updateVelocity();
				updatePosition();
			}

			// ANIMATION COUNTER SEQUENCE
			updateAnimationCounter();
		}
	}

	private void updateMovementDirection() {
		if (keyHandler.upPressed) {
			direction = "up";
		} else if (keyHandler.downPressed) {
			direction = "down";
		} else if (keyHandler.leftPressed) {
			direction = "left";
		} else if (keyHandler.rightPressed) {
			direction = "right";
		} else {
			direction = "stillFrame";
		}
	}

	private void updateVelocity() {
		velocityX = 0;
		velocityY = 0;

		if (keyHandler.upPressed) {
			velocityY -= speed;
		}
		if (keyHandler.downPressed) {
			velocityY += speed;
		}
		if (keyHandler.leftPressed) {
			velocityX -= speed;
		}
		if (keyHandler.rightPressed) {
			velocityX += speed;
		}
	}

	private void updatePosition() {
		worldX += velocityX;
		worldY += velocityY;
	}

	private void updateAnimationCounter() {
		spriteCounter++;
		if (spriteCounter > 12/* Change this value to change speed of animation */) {
			spriteNum = (spriteNum == 1) ? 2 : 1;
			spriteCounter = 0;
		}
	}

	public void pickUpObject(int i) {
		if(i != 999) {

		}
	}

	public void interactNPC(int index) {
		if(index != 999) {

			if(gamePanel.keyHandler.interactPressed) {
				gamePanel.gameState = gamePanel.dialogueState;
				gamePanel.npc[index].speak();
			}
			gamePanel.keyHandler.interactPressed = false;
		}
	}
	public void draw(Graphics2D g2) {

		BufferedImage image = null;
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

		g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
	}


}
