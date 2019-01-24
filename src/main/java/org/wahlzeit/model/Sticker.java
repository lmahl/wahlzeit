/**
 * Sticker
 *
 * Version: 1.0
 *
 * Date: 10.11.2018
 *
 * License: AGPLcv3
 */

package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

/**
 * Sequence of method calls that lead to the creation of a Sticker object
 * 1. One of the two available createSticker methods in StickerManager is called by the client. Either StickerManager.createSticker(double width, double height, String text, StickerGroup stickerGroup) or StickerManager.createSticker(double width, double height, String text, StickerGroup stickerGroup, Location location)
 * 2. The chosen method creates a StickerType object via the StickerType.createStickerType method
 * 3. The created StickerType creates a new Sticker object via the Sticker constructor and sets the Sticker.type field tho the StickerType
 * 4. The Sticker object is returned to the createSticker method
 * 5. The Sticker object is returned by the createSticker method to the client
 *
 * Object Creation Six Tuple
 * 1. Delegation: seperate-object -> A StickerType object is responsible for creating a Sicker object
 * 2. Selection: on-the-spot -> Sticker is created hard-code in place by StickerType.createInstance method
 * 3. Configuration: N/A -> No Stickers with diferent Configuration are created. therefore no configuration is necessary
 * 4. Instantiation: in-code -> Sticker is instantiated by a call to its constructor
 * 5. Initialization: by-key-value-pair -> Sitcker has two constructors with differing variable lists
 * 6. Building: default -> the Sticker is creating the structure by itself
 */
@PatternInstance(
		name = "Type Object Pattern",
		participants = {"Object"}
)
public class Sticker {
	private final StickerType type;

	private Location location = null;

	public Sticker(StickerType type){
		this.type = type;
	}

	public Sticker(StickerType type, Location location){
		this.type = type;
		this.location = location;
	}

	/**
	 *
	 * @Methodtype set
	 */
	public StickerType getType(){
		return this.type;
	}

	/**
	 *
	 * @Methodtype set
	 */
	public Location getLocation(){
		return this.location;
	}
}
