/**
 * StickerPhoto
 *
 * Version: 1.0
 *
 * Date: 10.11.2018
 *
 * License: AGPLcv3
 */

package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;
/**
 * Class that represents a Wahlzeit Photo containing a Sticker
 */
@Entity
public class StickerPhoto extends Photo{

	private Sticker sticker;

	/**
	 * @methodtype constructor
	 */
	public StickerPhoto(){
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public StickerPhoto(PhotoId id) {
		super(id);
	}

	/**
	 * @methodtype constructor
	 */
	public StickerPhoto(Sticker sticker) {
		super();
		this.sticker = sticker;
	}

	/**
	 * @methodtype constructor
	 */
	public StickerPhoto(Sticker sticker, PhotoId id) {
		super(id);
		this.sticker = sticker;
	}

	/**
	 * @methodtype get
	 * @return sticker
	 */
	public Sticker getSticker() {
		return sticker;
	}

	/**
	 * @methodtype set
	 * @param sticker
	 */
	public void setSticker(Sticker sticker) {
		this.sticker = sticker;
	}
}
