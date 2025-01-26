package entity;

import main.GamePanel;

import java.util.Random;


public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage() {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 =setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }
    public void setDialogue() {
        dialogues[0] = "Hello, young whipper snapper!";
        dialogues[1] = "What are yah doin' starin' at me like that...";
        dialogues[2] = "Back in my day people would greet you with a firm handshake. \nBut now you cringe zoomers cant even maintain eye contact without getting yer palms sweaty. *hmph*";
        dialogues[3] = "The names Pete.. *Spits on floor* Nice tah meet yah.";
    }
    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Pick up a number from 1 to 100

            if(i<=25) {
                direction = "up";
            }
            if(i>25 && i<=50) {
                direction = "down";
            }
            if(i>50 && i<=75) {
                direction = "left";
            }
            if(i>75 && i<=100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    public void speak() {

        // DO character specific stuff
        super.speak();
        switch (gamePanel.player.direction) {
            case "up":
                direction = "up";
                break;
            case "down":
                direction = "down";
                break;
            case "left":
                direction = "left";
                break;
            case "right":
                direction = "right";
                break;
        }
    }

}
