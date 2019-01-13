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
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.PatternInstance;

import java.util.logging.Logger;

/**
 * Class that represents a Wahlzeit Photo containing a Sticker
 */
@PatternInstance(
		name = "Abstract Factory",
		participants = { "Abstract Product, Concrete Product" }
)
@Entity
public class StickerPhoto extends Photo{

	private Sticker sticker;
	private final static Logger log = Logger.getLogger(StickerPhoto.class.getName());

	/**
	 * @methodtype constructor
	 */
	public StickerPhoto(){
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public StickerPhoto(PhotoId id) throws IllegalArgumentException{
		super(id);
		assertNotNull(id);
	}

	/**
	 * @methodtype constructor
	 */
	public StickerPhoto(Sticker sticker) throws IllegalArgumentException{
		super();
		assertNotNull(sticker);
		this.sticker = sticker;
	}

	/**
	 * @methodtype constructor
	 */
	public StickerPhoto(Sticker sticker, PhotoId id) throws IllegalArgumentException{
		super(id);
		assertNotNull(sticker);
		assertNotNull(id);
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
	public void setSticker(Sticker sticker) throws IllegalArgumentException{
		assertNotNull(sticker);
		this.sticker = sticker;
	}


	/**
	 * Checks that the parameter is not null
	 * @param val
	 * @throws IllegalArgumentException
	 */
	private void assertNotNull(Object val) throws IllegalArgumentException{
		if(val == null){
			IllegalArgumentException ex = new IllegalArgumentException("Argument must not be null");
			log.warning(LogBuilder.createSystemMessage().
					addException("Argument must not be null", ex).toString());
			throw ex;
		}
	}

}
