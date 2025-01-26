package object;

import entity.Entity;
import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends Entity {

public OBJ_Door(GamePanel gamePanel) {

		super(gamePanel);
		name = "Door";
		down1 = setup("/objects/door.png");
		collision = true;

		hitbox.x = 0;
		hitbox.y = 16;
		hitbox.width = 48;
		hitbox.height = 32;
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
	}

}
