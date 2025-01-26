package main;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 Tile
	final int scale = 3;  
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 34;
	public final int maxScreenRow = 20;
	public final int screenWidth = tileSize * maxScreenCol; 
	public final int screenHeight = tileSize * maxScreenRow;  
	
	// WORLD SETTINGS 
	public final int maxWorldCol = 100;
	public final int maxWorldRow = 100;
	
	//FPS
	int FPS = 60;
	int nano = 1000000000;

	// INSTANTIATE SYSTEM CLASSES
	TileManager tileManager = new TileManager(this);
	public KeyHandler keyHandler = new KeyHandler(this);
	Sound music = new Sound();
	Sound soundEffects = new Sound();
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter assetSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eventHandler = new EventHandler(this);
	Thread gameThread;
	
	// ENTITY AND OBJECT
	public Player player = new Player(this,keyHandler);
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();

	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	// WINDOW CONSTRUCTOR 
	public GamePanel() throws IOException, FontFormatException {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		
	}
	
	public void setupGame() {
		assetSetter.setObject();
		assetSetter.setNPC();
		assetSetter.setMonster();
		gameState = titleState;
	}

	// Game Start Method
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	// Game Loop
	@Override
	public void run() {
		double drawInterval = nano/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if(delta >=1){update();repaint();delta--;drawCount++;} 
		if(timer >= nano){System.out.println("FPS:" + drawCount);drawCount=0;timer=0;}
		}
	}
	
	public void update() {

		if(gameState == playState) {
			// PLAYER
			player.update();

			// NPC
			for(int i=0; i< npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}

			// MONSTER
			for(int i=0; i< monster.length; i++) {
				if(monster[i] != null) {
					monster[i].update();
				}
			}

		}
		if(gameState == pauseState) {
			stopMusic();
		}
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D)graphics;

		// TITLE SCREEN
        if (gameState != titleState) {

            // TILE
            tileManager.draw(graphics2D);

            // ADD ALL ENTITIES TO DRAW LIST
            // PLAYER
            entityList.add(player);

            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            // OBJECTS
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

			// MONSTERS
			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					entityList.add(monster[i]);
				}
			}

            // SORT RENDER ORDER
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
                }
            });

            //DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(graphics2D);
            }

            // EMPTY ENTITY LIST
            for (int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }

            // UI
        } ui.draw(graphics2D);

        graphics2D.dispose();
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {

		soundEffects.setFile(i);
		soundEffects.play();
	}
	
	

}
