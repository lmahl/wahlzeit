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

public class StickerPhotoFactory extends PhotoFactory {

	private static StickerPhotoFactory stickerPhotoFactory = null;

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
	public Photo createPhoto(PhotoId id) {
		return new StickerPhoto(id);
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

	public StickerPhoto createStickerPhoto(PhotoId id, Sticker sticker) {
		return new StickerPhoto(sticker, id);
	}

}
