package object;

import entity.Entity;
import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends Entity {
	
	public OBJ_Key(GamePanel gamePanel) {
		super(gamePanel);

		name = "Key";
		down1 = setup("/objects/key.png");

	}

}
