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
 *
 * Sequence of method calls that lead to the creation of a StickerPhoto object
 * 1. A StickerPhotoManager object is instantiated by  client and it's createPhoto method is called. E.g. from UploadPhotoFormHandler
 * 2. In StickerPhotoManager's createPhoto method, PhotoUtil.createPhoto method is called
 * 3. In PhotoUtil.createPhoto, StickerPhotoFactory's createPhoto method is called
 * 4. In this createPhoto method, a new StickerPhoto instance is created via the StickerPhoto Constructor
 * 5. The StickerPhoto object is returned to the PhotoUtil.createPhoto method
 * 6. The StickerPhoto object is returned to StickerPhotoManager.createPhoto method
 * 7. The StickerPhoto object is passed to the client
 *
 * Object Creation Six Tuple
 * 1. Delegation: separate-object -> A StickerPhotoFactory object is responsible for creating a StickerPhoto object
 * 2. Selection: by-subclassing -> Which concrete object is created is decided by subclassing PhotoFactory.
 * 3. Configuration: in-code -> currently, the mappping is hard-coded. The only concrete subclasses, that are ever used are the Sticker-specific ones
 * 4. Instantiation: in-code -> StickerPhoto is instantiated by a call to its constructor
 * 5. Initialization: by-key-value-pair -> SitckerPhoto has multiple constructors with differing variable lists
 * 6. Building: default -> the StickerPhoto is creating the structure by itself
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
