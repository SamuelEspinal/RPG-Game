package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gamePanel;
	public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed, endDialogue;

	public KeyHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	// Not Used
	@Override
	public void keyTyped(KeyEvent event) {}

	@Override
	public void keyPressed(KeyEvent event) {

		int code = event.getKeyCode();

		// TITLE STATE
		if(gamePanel.gameState == gamePanel.titleState) {

			if(gamePanel.ui.titleScreenState == 0) {
				if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
					gamePanel.ui.commandNumber --;
					if(gamePanel.ui.commandNumber <0) {
						gamePanel.ui.commandNumber = 2;
					}
				}
				if(code == KeyEvent.VK_A || code == KeyEvent.VK_DOWN) {
					gamePanel.ui.commandNumber ++;
					if(gamePanel.ui.commandNumber >2) {
						gamePanel.ui.commandNumber = 0;
					}
				}
				if(code == KeyEvent.VK_A || code == KeyEvent.VK_ENTER) {
					switch (gamePanel.ui.commandNumber) {
						case 0:
							gamePanel.ui.titleScreenState = 1;
							break;

						case 1:
							//Add Later
							break;

						case 2:
							System.exit(0);
							break;
					}
				}
			}
			else if(gamePanel.ui.titleScreenState == 1) {
				if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
					gamePanel.ui.commandNumber --;
					if(gamePanel.ui.commandNumber <0) {
						gamePanel.ui.commandNumber = 3;
					}
				}
				if(code == KeyEvent.VK_A || code == KeyEvent.VK_DOWN) {
					gamePanel.ui.commandNumber ++;
					if(gamePanel.ui.commandNumber >3) {
						gamePanel.ui.commandNumber = 0;
					}
				}
				if(code == KeyEvent.VK_A || code == KeyEvent.VK_ENTER) {
					switch (gamePanel.ui.commandNumber) {
						case 0:
							System.out.println("SHOW FIGHTER CLASS STUFF");
							//Right now we play game... will add custom screen for fighter info etc
							gamePanel.gameState = gamePanel.playState;
							//gamePanel.playMusic(0);
							break;

						case 1:
							System.out.println("SHOW ROGUE CLASS STUFF");
							//Right now we play game... will add custom screen for rogue info etc
							gamePanel.gameState = gamePanel.playState;
							//gamePanel.playMusic(0);
							break;

						case 2:
							System.out.println("SHOW SORCERER CLASS STUFF");
							//Right now we play game... will add custom screen for sorcerer info etc
							gamePanel.gameState = gamePanel.playState;
							//gamePanel.playMusic(0);
							break;

						case 3:
							gamePanel.ui.titleScreenState = 0; // GO BACK TO MAIN MENU
					}
				}
			}
		}
		// PLAY STATE
		if(gamePanel.gameState == gamePanel.playState) {
			handlePlayStateKeys(code);
		}
		// PAUSE STATE
		else if(gamePanel.gameState == gamePanel.pauseState) {
			handlePauseStateKeys(code);
		}
		// DIALOGUE STATE
		else if(gamePanel.gameState == gamePanel.dialogueState) {
			handleDialogueStateKeys(code);
		}
	}

	private void handlePlayStateKeys(int code) {
		switch (code) {
			case KeyEvent.VK_W:
				upPressed = true;
				break;
			case KeyEvent.VK_A:
				leftPressed = true;
				break;
			case KeyEvent.VK_S:
				downPressed = true;
				break;
			case KeyEvent.VK_D:
				rightPressed = true;
				break;
			case KeyEvent.VK_ESCAPE:
				gamePanel.gameState = gamePanel.pauseState;
				break;
			case KeyEvent.VK_E:
				interactPressed = true;
				break;
		}
	}

	private void handlePauseStateKeys(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gamePanel.gameState = gamePanel.playState;
		}
	}

	private void handleDialogueStateKeys(int code) {
		if (code == KeyEvent.VK_ENTER) {
			gamePanel.gameState = gamePanel.playState;
			endDialogue = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
	
		int code = event.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
}
