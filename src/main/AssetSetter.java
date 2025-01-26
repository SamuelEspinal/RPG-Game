package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;

public class AssetSetter {

	GamePanel gamePanel;
	
	public AssetSetter(GamePanel gamePanel){

		this.gamePanel = gamePanel;
	}
	
	public void setObject() {
		

	}
	public void setNPC() {
		gamePanel.npc[0] = new NPC_OldMan(gamePanel);
		gamePanel.npc[0].worldX = gamePanel.tileSize*69;
		gamePanel.npc[0].worldY = gamePanel.tileSize*55;


	}

	public void setMonster() {

		gamePanel.monster[0] = new MON_GreenSlime(gamePanel);
		gamePanel.monster[0].worldX = gamePanel.tileSize*60;
		gamePanel.monster[0].worldY = gamePanel.tileSize*57;

		gamePanel.monster[1] = new MON_GreenSlime(gamePanel);
		gamePanel.monster[1].worldX = gamePanel.tileSize*61;
		gamePanel.monster[1].worldY = gamePanel.tileSize*57;

		gamePanel.monster[2] = new MON_GreenSlime(gamePanel);
		gamePanel.monster[2].worldX = gamePanel.tileSize*62;
		gamePanel.monster[2].worldY = gamePanel.tileSize*57;
	}
}
