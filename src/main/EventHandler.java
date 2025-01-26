package main;

import java.awt.*;

public class EventHandler {

    GamePanel gamePanel;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel) {


        this.gamePanel = gamePanel;

        eventRect = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        int column = 0;
        int row = 0;
        while(column < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
            eventRect[column][row] = new EventRect();
            eventRect[column][row].x = 23;
            eventRect[column][row].y = 23;
            eventRect[column][row].width = 2;
            eventRect[column][row].height = 2;
            eventRect[column][row].eventRectDefaultX = eventRect[column][row].x;
            eventRect[column][row].eventRectDefaultY = eventRect[column][row].y;

            column++;
            if(column == gamePanel.maxWorldCol) {
                column = 0;
                row++;
            }
        }



    }

    public void checkEvent() {

        //Check if the player character is more than 1 tile away from last event
        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if(distance > gamePanel.tileSize) {
            canTouchEvent = true;
        }
        if(canTouchEvent) {
            if(hit(76, 59, "any")) {damagePit(76, 59, gamePanel.dialogueState);}
        }

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection)  {

        boolean hit = false;
        gamePanel.player.hitbox.x = gamePanel.player.worldX + gamePanel.player.hitbox.x;
        gamePanel.player.hitbox.y = gamePanel.player.worldY + gamePanel.player.hitbox.y;
        eventRect[eventCol][eventRow].x = eventCol*gamePanel.tileSize +  eventRect[eventCol][eventRow].x;
        eventRect[eventCol][eventRow].y = eventRow*gamePanel.tileSize +  eventRect[eventCol][eventRow].y;

        if(gamePanel.player.hitbox.intersects(eventRect[eventCol][eventRow]) && eventRect[eventCol][eventRow].eventDone == false) {
            if(gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gamePanel.player.worldX;
                previousEventY = gamePanel.player.worldY;
            }
        }
        gamePanel.player.hitbox.x = gamePanel.player.hitboxDefaultX;
        gamePanel.player.hitbox.y = gamePanel.player.hitboxDefaultY;
        eventRect[eventCol][eventRow].x =  eventRect[eventCol][eventRow].eventRectDefaultX;
        eventRect[eventCol][eventRow].y =  eventRect[eventCol][eventRow].eventRectDefaultY;
        return hit;
    }

    public void damagePit(int column, int row, int gameState) {

        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "You fall into a pit!";
        gamePanel.player.life -= 1;
        //eventRect[column][row].eventDone = true;
        canTouchEvent = false;

    }
}
