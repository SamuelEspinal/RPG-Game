package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/mapv2.txt");
	}

	// Method to grab tile image files
	public void getTileImage() {

		setup(0,"enviroment/basic_tree", true);
		setup(1,"enviroment/bricks_grey_1", true);
		setup(2,"enviroment/bricks_grey_2", true);
		setup(3,"enviroment/earth", false);
		setup(4,"enviroment/grass_blades", false);
		setup(5,"enviroment/grass_corner01", false);
		setup(6,"enviroment/grass_corner02", false);
		setup(7,"enviroment/grass_corner03", false);
		setup(8,"enviroment/grass_corner04", false);
		setup(9,"enviroment/grass_edge01", false);
		setup(10,"enviroment/grass_edge02", false);
		setup(11,"enviroment/grass_edge03", false);
		setup(12,"enviroment/grass_edge04", false);
		setup(13,"enviroment/grass_flowers", false);
		setup(14,"enviroment/grass_plain", false);
		setup(15,"enviroment/sand", false);
		setup(16,"enviroment/sand01", false);
		setup(17,"enviroment/sand02", false);
		setup(18,"enviroment/sand03", false);
		setup(19,"enviroment/sand04", false);
		setup(20,"enviroment/sand_inside01", false);
		setup(21,"enviroment/sand_inside02", false);
		setup(22,"enviroment/sand_inside03", false);
		setup(23,"enviroment/sand_inside04", false);
		setup(24,"enviroment/water01", true);
		setup(25,"enviroment/water02", true);
		setup(26,"enviroment/water03", true);
		setup(27,"enviroment/water_corner01", true);
		setup(28,"enviroment/water_corner02", true);
		setup(29,"enviroment/water_corner03", true);
		setup(30,"enviroment/water_corner04", true);
		setup(31,"enviroment/water_dirty", true);
		setup(32,"enviroment/water_edge01", true);
		setup(33,"enviroment/water_edge02", true);
		setup(34,"enviroment/water_edge03", true);
		setup(35,"enviroment/water_edge04", true);
		setup(36,"enviroment/water_inside01", true);
		setup(37,"enviroment/water_inside02", true);
		setup(38,"enviroment/water_inside03", true);
		setup(39,"enviroment/water_inside04", true);
		setup(40,"enviroment/water_plain", true);
	}

	public void setup(int index, String imageName, boolean collision) {

		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}catch(IOException e) {e.printStackTrace();}

	}

	// Imports Text Files For Maps
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			// While loop to scan text file line by line
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();

				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch (Exception e) {

		}
	}

	// While Loop to draw tiles on screen
	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			worldCol++;
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
