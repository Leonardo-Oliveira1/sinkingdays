package main.core;

import main.entities.Entity;
import main.tile.TileManager;

public class CollisionChecker {
	
	TileManager tileManager;

	public CollisionChecker(TileManager tileManager) {
		this.tileManager = tileManager;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / tileManager.tileSize;
		int entityRightCol = entityRightWorldX / tileManager.tileSize;
		int entityTopRow = entityTopWorldY / tileManager.tileSize;
		int entityBottomRow = entityBottomWorldY / tileManager.tileSize;
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / tileManager.tileSize;
			tileNum1 = tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = tileManager.mapTileNum[entityRightCol][entityTopRow];
			
			if(tileManager.tile[tileNum1].collission == true || tileManager.tile[tileNum2].collission) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / tileManager.tileSize;
			tileNum1 = tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = tileManager.mapTileNum[entityRightCol][entityBottomRow];
			
			if(tileManager.tile[tileNum1].collission == true || tileManager.tile[tileNum2].collission) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / tileManager.tileSize;
			tileNum1 = tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			
			if(tileManager.tile[tileNum1].collission == true || tileManager.tile[tileNum2].collission) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / tileManager.tileSize;
			tileNum1 = tileManager.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = tileManager.mapTileNum[entityRightCol][entityBottomRow];
			
			if(tileManager.tile[tileNum1].collission == true || tileManager.tile[tileNum2].collission) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
}
