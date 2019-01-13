/**
 * StickerGroup
 *
 * Version: 1.0
 *
 * Date: 10.11.2018
 *
 * License: AGPLcv3
 */

package org.wahlzeit.model;

import org.wahlzeit.model.exceptions.FailedToCreateInstanceException;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.PatternInstance;

import java.util.logging.Logger;

@PatternInstance(
		name = "Singleton",
		participants = { "Singleton" }
)
@PatternInstance(
		name = "Abstract Factory",
		participants = { "Abstract Factory, Concrete Factory" }
)
public class StickerPhotoFactory extends PhotoFactory {

	private static StickerPhotoFactory stickerPhotoFactory = null;
	private final static Logger log = Logger.getLogger(StickerPhotoFactory.class.getName());

	/**
	 * @methodtype constructor
	 */
	private StickerPhotoFactory(){

	}

	/**
	 * Returns an instance of the factory
	 * @methodtype get
	 * @return Instance of StickerPhotoFactory
	 */
	public static StickerPhotoFactory getInstance(){
		if(stickerPhotoFactory == null){
			stickerPhotoFactory = new StickerPhotoFactory();
		}
		return stickerPhotoFactory;
	}

	/**
	 * @methodtype factory
	 * @return Sticker Photo Instance
	 */
	@Override
	public Photo createPhoto() {
		return new StickerPhoto();
	}

	/**
	 * @methodtype factory
	 * @param id
	 * @return Sticker Photo Instance
	 */
	@Override
	public Photo createPhoto(PhotoId id) throws FailedToCreateInstanceException {
		StickerPhoto photo;
		try{
			photo = new StickerPhoto(id);
		} catch (IllegalArgumentException e){
			FailedToCreateInstanceException ex = new FailedToCreateInstanceException("Could not create Instance of Photo", e);
			log.warning(LogBuilder.createSystemMessage().
					addException("Could not create Instance of Photo", ex).toString());
			throw ex;
		}

		return photo;
	}

	/**
	 * @methodtype factory
	 * @return Sticker Photo Instance
	 */
	public StickerPhoto createStickerPhoto() {
		return new StickerPhoto();
	}

	/**
	 * @methodtype factory
	 * @param id
	 * @return Sticker Photo Instance
	 */

	public StickerPhoto createStickerPhoto(PhotoId id, Sticker sticker) throws FailedToCreateInstanceException {
		StickerPhoto photo;
		try{
			photo = new StickerPhoto(sticker, id);
		} catch (IllegalArgumentException e){
			FailedToCreateInstanceException ex = new FailedToCreateInstanceException("Could not create Instance of Photo", e);
			log.warning(LogBuilder.createSystemMessage().
					addException("Could not create Instance of Photo", ex).toString());
			throw ex;
		}

		return photo;
	}

}
