package main;

import entity.Entity;
import object.OBJ_Health;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {

	GamePanel gamePanel;
	Graphics2D graphics2D;
	Font maruMonica;
	BufferedImage fullHealth, health4, health3, health2, health1, noHealth;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNumber = 0;
	public int titleScreenState = 0; // 0: Main Screen, 1: Select a Class
	
	public UI(GamePanel gamePanel) throws IOException, FontFormatException {
		this.gamePanel=gamePanel;

		InputStream inputStream = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
        assert inputStream != null;
        maruMonica = Font.createFont(Font.TRUETYPE_FONT, inputStream);

		// CREATE HUD OBJECT
		Entity health = new OBJ_Health(gamePanel);
		fullHealth = health.image6;
		health4 = health.image5;
		health3 = health.image4;
		health2 = health.image3;
		health1 = health.image2;
		noHealth = health.image;
	}
	
	public void showMessage (String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D graphics2D) {

		this.graphics2D = graphics2D;

		graphics2D.setFont(maruMonica);
		graphics2D.setColor(Color.white);

		// TITLE STATE
		if(gamePanel.gameState == gamePanel.titleState) {
			drawTitleScreen();
		}
		// PLAY STATE
		if(gamePanel.gameState == gamePanel.playState) {
			drawPlayerHealth();
		}
		// PAUSE STATE
		if(gamePanel.gameState == gamePanel.pauseState) {
			drawPauseScreen();
			drawPlayerHealth();
		}
		// DIALOGUE STATE
		if(gamePanel.gameState == gamePanel.dialogueState) {
			drawDialogueScreen();
			drawPlayerHealth();
		}
	}
	public void drawPlayerHealth() {

		int x = gamePanel.tileSize/2;
		int y = gamePanel.tileSize/2;

		//DRAW BLANK LIFE
		graphics2D.drawImage(noHealth,x,y,null);

		//DRAW CURRENT LIFE
		if(gamePanel.player.life == gamePanel.player.maxLife) {
			graphics2D.drawImage(fullHealth,x,y,null);
		} else if (gamePanel.player.life == 4) {
			graphics2D.drawImage(health4,x,y,null);
		} else if (gamePanel.player.life == 3) {
			graphics2D.drawImage(health3,x,y,null);
		} else if (gamePanel.player.life == 2) {
			graphics2D.drawImage(health2,x,y,null);
		} else if (gamePanel.player.life == 1) {
			graphics2D.drawImage(health1,x,y,null);
		} else if (gamePanel.player.life == 0) {
			graphics2D.drawImage(noHealth,x,y,null);
		}


	}
	public void drawTitleScreen() {

		if(titleScreenState == 0) {
			// TITLE NAME
			graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD,96F));
			String text = "Eclipse of the Necromantic Saga";
			int x = getXForCenteredText(text);
			int y = gamePanel.tileSize*5;

			// TITLE BACKGROUND COLOR
			graphics2D.setColor((new Color(93, 93, 93)));
			graphics2D.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
			// SHADOW
			graphics2D.setColor(Color.black);
			graphics2D.drawString(text,x+4,y+4);
			// MAIN COLOR
			graphics2D.setColor(Color.WHITE);
			graphics2D.drawString(text, x, y);

			// TITLE IMAGE
			x = gamePanel.screenWidth/2 - 120; //subtract the drawImage width divided by 2 to center the image
			y += gamePanel.tileSize*3;
			graphics2D.drawImage(gamePanel.player.down1, x, y, 240, 240, null); //CREATE A BETTER TITLE IMAGE

			// MENU
			graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD,48F));

			text = "NEW GAME";
			x = getXForCenteredText(text);
			y += gamePanel.tileSize*7;
			graphics2D.drawString(text, x+4, y+4); // offset original text for button animation
			if(commandNumber == 0) {
				// ANIMATE BUTTON PRESS
				graphics2D.setColor(Color.black);
				graphics2D.drawString(text,x+4,y+4);

				graphics2D.setColor(Color.white);
				graphics2D.drawString(text,x,y);
			}

			text = "LOAD GAME";
			x = getXForCenteredText(text);
			y += gamePanel.tileSize*1;
			graphics2D.drawString(text, x+4, y+4);
			if(commandNumber == 1) {
				// ANIMATE BUTTON PRESS
				graphics2D.setColor(Color.black);
				graphics2D.drawString(text,x+4,y+4);

				graphics2D.setColor(Color.white);
				graphics2D.drawString(text,x,y);
			}

			text = "QUIT";
			x = getXForCenteredText(text);
			y += gamePanel.tileSize*1;
			graphics2D.drawString(text, x+4, y+4);
			if(commandNumber == 2) {
				// ANIMATE BUTTON PRESS
				graphics2D.setColor(Color.black);
				graphics2D.drawString(text,x+4,y+4);

				graphics2D.setColor(Color.white);
				graphics2D.drawString(text,x,y);
			}
		}
		else if(titleScreenState == 1) {

			// CLASS SELECTION SCREEN
			graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD,42F));
			String text = "Select a Class";
			int x = getXForCenteredText(text);
			int y = gamePanel.tileSize * 5;


			// TITLE BACKGROUND
			graphics2D.setColor((new Color(93, 93, 93)));
			graphics2D.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
			// SHADOW
			graphics2D.setColor(Color.black);
			graphics2D.drawString(text,x+4,y+4);
			// MAIN COLOR
			graphics2D.setColor(Color.white);
			graphics2D.drawString(text, x, y);

			// CLASS OPTIONS
			text = "Fighter";
			x = getXForCenteredText(text);
			y += gamePanel.tileSize * 5;
			graphics2D.drawString(text, x, y);
			if(commandNumber == 0) {
				// ANIMATE BUTTON PRESS
				graphics2D.setColor(Color.black);
				graphics2D.drawString(text,x+4,y+4);

				graphics2D.setColor(Color.white);
				graphics2D.drawString(text,x,y);
			}
			text = "Rogue";
			x = getXForCenteredText(text);
			y += gamePanel.tileSize * 2;
			graphics2D.drawString(text, x, y);
			if(commandNumber == 1) {
				// ANIMATE BUTTON PRESS
				graphics2D.setColor(Color.black);
				graphics2D.drawString(text,x+4,y+4);

				graphics2D.setColor(Color.white);
				graphics2D.drawString(text,x,y);
			}
			text = "Sorcerer";
			x = getXForCenteredText(text);
			y += gamePanel.tileSize * 2;
			graphics2D.drawString(text, x, y);
			if(commandNumber == 2) {
				// ANIMATE BUTTON PRESS
				graphics2D.setColor(Color.black);
				graphics2D.drawString(text,x+4,y+4);

				graphics2D.setColor(Color.white);
				graphics2D.drawString(text,x,y);
			}
			text = "Back";
			x = getXForCenteredText(text);
			y += gamePanel.tileSize * 4;
			graphics2D.drawString(text, x, y);
			if(commandNumber == 3) {
				// ANIMATE BUTTON PRESS
				graphics2D.setColor(Color.black);
				graphics2D.drawString(text,x+4,y+4);

				graphics2D.setColor(Color.white);
				graphics2D.drawString(text,x,y);
			}

		}


	}
	public void drawPauseScreen() {

		graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN,80f));
		String text = "PAUSED";

		int x = getXForCenteredText(text);
		int y = gamePanel.screenHeight/2;

		graphics2D.drawString(text, x, y);
	}
	public void drawDialogueScreen() {

		// WINDOW
		int x = gamePanel.tileSize*4;
		int y = gamePanel.tileSize;
		int width = gamePanel.screenWidth - (gamePanel.tileSize*8);
		int height = gamePanel.tileSize*5;

		drawSubWindow(x,y,width,height);

		graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 32F));
		x += gamePanel.tileSize;
		y += gamePanel.tileSize;

		for(String line : currentDialogue.split("\n")) {
			graphics2D.drawString(line, x, y);
			y+=40;
		}
	}
	public void drawSubWindow(int x, int y, int width, int height) {

		Color color = new Color(0, 0, 0,210);
		graphics2D.setColor(color);
		graphics2D.fillRoundRect(x,y,width,height,35,35);

		color = new Color(255,255,255);
		graphics2D.setColor(color);
		graphics2D.setStroke(new BasicStroke(5));
		graphics2D.drawRoundRect(x+5,y+5,width-10,height-10,25,25);

	}
	public int getXForCenteredText(String text) {
		int textLength = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return gamePanel.screenWidth/2 - textLength/2;
	}
}
