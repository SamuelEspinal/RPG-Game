package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gamePanel;
	
	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.hitbox.x;
		int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
		int entityTopWorldY = entity.worldY + entity.hitbox.y;
		int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;
		
		int entityLeftCol = entityLeftWorldX/ gamePanel.tileSize;
		int entityRightCol = entityRightWorldX/ gamePanel.tileSize;
		int entityTopRow = entityTopWorldY/ gamePanel.tileSize;
		int entityBottomRow = entityBottomWorldY/ gamePanel.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/ gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
			if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/ gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
			if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/ gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/ gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
			if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}break;
		}
		
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i< gamePanel.obj.length; i++) {
			if(gamePanel.obj[i] != null) {
				// Get entity's hitbox position
				entity.hitbox.x = entity.worldX + entity.hitbox.x;
				entity.hitbox.y = entity.worldY + entity.hitbox.y;
				
				// Get objects hitbox position
				gamePanel.obj[i].hitbox.x = gamePanel.obj[i].worldX + gamePanel.obj[i].hitbox.x;
				gamePanel.obj[i].hitbox.y = gamePanel.obj[i].worldY + gamePanel.obj[i].hitbox.y;
				
				switch(entity.direction) {
				case "up":
					entity.hitbox.y -= entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.hitbox.y += entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.hitbox.x -= entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					entity.hitbox.x += entity.speed;
					break;
				}
				entity.hitbox.x = entity.hitboxDefaultX;
				entity.hitbox.y = entity.hitboxDefaultY;
				gamePanel.obj[i].hitbox.x = gamePanel.obj[i].hitboxDefaultX;
				gamePanel.obj[i].hitbox.y = gamePanel.obj[i].hitboxDefaultY;
			}
		}
		return index;
	}

	//NPC OR MONSTER COLLISION
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;

		for(int i=0;i<target.length;i++) {
			if(target[i] != null) {
				// Get entity's hitbox position
				entity.hitbox.x = entity.worldX + entity.hitbox.x;
				entity.hitbox.y = entity.worldY + entity.hitbox.y;

				// Get objects hitbox position
				target[i].hitbox.x = target[i].worldX + target[i].hitbox.x;
				target[i].hitbox.y = target[i].worldY + target[i].hitbox.y;

				switch(entity.direction) {
					case "up":
						entity.hitbox.y -= entity.speed;
						if(entity.hitbox.intersects(target[i].hitbox)) {
							entity.collisionOn = true;
							index = i;
						}
						break;
					case "down":
						entity.hitbox.y += entity.speed;
						if(entity.hitbox.intersects(target[i].hitbox)) {
							entity.collisionOn = true;
							index = i;
						}
						break;
					case "left":
						entity.hitbox.x -= entity.speed;
						if(entity.hitbox.intersects(target[i].hitbox)) {
							entity.collisionOn = true;
							index = i;
						}
						break;
					case "right":
						if(entity.hitbox.intersects(target[i].hitbox)) {
							entity.collisionOn = true;
							index = i;
						}
						entity.hitbox.x += entity.speed;
						break;
				}
				entity.hitbox.x = entity.hitboxDefaultX;
				entity.hitbox.y = entity.hitboxDefaultY;
				target[i].hitbox.x = target[i].hitboxDefaultX;
				target[i].hitbox.y = target[i].hitboxDefaultY;
			}
		}
		return index;
	}
	public void checkPlayer(Entity entity) {
		// Get entity's hitbox position
		entity.hitbox.x = entity.worldX + entity.hitbox.x;
		entity.hitbox.y = entity.worldY + entity.hitbox.y;

		// Get objects hitbox position
		gamePanel.player.hitbox.x = gamePanel.player.worldX + gamePanel.player.hitbox.x;
		gamePanel.player.hitbox.y = gamePanel.player.worldY + gamePanel.player.hitbox.y;

		switch(entity.direction) {
			case "up":
				entity.hitbox.y -= entity.speed;
				if(entity.hitbox.intersects(gamePanel.player.hitbox)) {
					entity.collisionOn = true;
				}
				break;
			case "down":
				entity.hitbox.y += entity.speed;
				if(entity.hitbox.intersects(gamePanel.player.hitbox)) {
					entity.collisionOn = true;
				}
				break;
			case "left":
				entity.hitbox.x -= entity.speed;
				if(entity.hitbox.intersects(gamePanel.player.hitbox)) {
					entity.collisionOn = true;
				}
				break;
			case "right":
				if(entity.hitbox.intersects(gamePanel.player.hitbox)) {
					entity.collisionOn = true;
				}
				entity.hitbox.x += entity.speed;
				break;
		}
		entity.hitbox.x = entity.hitboxDefaultX;
		entity.hitbox.y = entity.hitboxDefaultY;
		gamePanel.player.hitbox.x = gamePanel.player.hitboxDefaultX;
		gamePanel.player.hitbox.y = gamePanel.player.hitboxDefaultY;
	}
	
}
