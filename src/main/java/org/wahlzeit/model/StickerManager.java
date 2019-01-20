/**
 * SitckerManager
 *
 * Version: 1.0
 *
 * Date: 20.01.2018
 *
 * License: AGPLv3
 */
package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

import java.util.ArrayList;
import java.util.List;

@PatternInstance(
		name = "Singleton",
		participants = {"Singleton"}
)
@PatternInstance(
		name = "Type Object Pattern",
		participants = {"Object Manager"}
)
public class StickerManager {

	private static StickerManager instance = null;
	private List<Sticker> stickers = new ArrayList<Sticker>();

	private StickerManager(){

	}

	/**
	 * Method Type: Factory
	 * @return current instance of the singleton
	 */
	public static StickerManager getInstance(){
		if (instance == null){
			instance = new StickerManager();
		}

		return instance;
	}

	/**
	 * @Methodtype get
	 * @return List of all stickers ever created by the manager
	 */
	public List<Sticker> getStickers(){
		return this.stickers;
	}

	/**
	 * Create a new Sticker with a corresponding StickerType
	 * @param width width of the StickerType
	 * @param height height of the StickerType
	 * @param text text of the StickerType
	 * @param stickerGroup StickerGroup of the StickerType
	 * @return Sticker Object
	 */
	public Sticker createSticker(double width, double height, String text, StickerGroup stickerGroup){
		StickerType type = StickerType.createStickerType(width, height, text, stickerGroup);
		Sticker result = type.createInstance();
		stickers.add(result);
		return result;
	}

	/**
	 * Create a new Sticker with a corresponding StickerType
	 * @param width width of the StickerType
	 * @param height height of the StickerType
	 * @param text text of the StickerType
	 * @param stickerGroup StickerGroup of the StickerType
	 * @param location Location of the Sticker
	 * @return Sticker Object
	 */
	public Sticker createSticker(double width, double height, String text, StickerGroup stickerGroup, Location location){
		StickerType type = StickerType.createStickerType(width, height, text, stickerGroup);
		Sticker result = type.createInstance(location);
		stickers.add(result);
		return result;
	}
}
