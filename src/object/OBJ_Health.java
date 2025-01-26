package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Health extends Entity {

    public OBJ_Health(GamePanel gamePanel) {

        super(gamePanel);
        name = "Health";

        image6 = setup("/objects/full_Health");
        image5 = setup("/objects/health4");
        image4 = setup("/objects/health3");
        image3 = setup("/objects/health2");
        image2 = setup("/objects/health1");
        image = setup("/objects/no_health");
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, gamePanel.tileSize*3, gamePanel.tileSize*3);
        }catch(IOException error) {
            error.printStackTrace();
        }
        return image;
    }
}
