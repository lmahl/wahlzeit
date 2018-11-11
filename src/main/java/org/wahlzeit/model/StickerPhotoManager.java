/**
 * StickerPhotoManager
 *
 * Version: 1.0
 *
 * Date: 10.11.2018
 *
 * License: AGPLcv3
 */

package org.wahlzeit.model;

public class StickerPhotoManager extends PhotoManager {
	protected static final StickerPhotoManager stickerPhotoManager = new StickerPhotoManager();

	/**
	 * @methodtype constructor
	 */
	private StickerPhotoManager() {
		super();
		photoTagCollector = StickerPhotoFactory.getInstance().createPhotoTagCollector();
	}

}
