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
