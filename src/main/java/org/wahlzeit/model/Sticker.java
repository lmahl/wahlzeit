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

public class Sticker {
	private final double width;
	private final double height;
	private final String text;
	private final StickerGroup stickerGroup;

	/**
	 * @methodtype constructor
	 * @param width width of the sticker in cm
	 * @param height height of the sticker in cm
	 * @param text any text written on the sticker
	 * @param stickerGroup kind of sticker
	 */
	public Sticker(double width, double height, String text, StickerGroup stickerGroup) {
		assertIsPositive(width);
		assertIsPositive(height);
		this.width = width;
		this.height = height;
		this.text =text;
		this.stickerGroup = stickerGroup;
	}

	/**
	 * Checks a double value to be positive or 0
	 * @methodtype assertion
	 * @param val value to be checked
	 * @throws IllegalArgumentException
	 */
	private void assertIsPositive(double val) throws IllegalArgumentException{
		if(val<0){
			throw new IllegalArgumentException();
		}
	}

    /**
     * @methodtype get
     * @return width
     */
	public double getWidth(){
		return this.width;
	}

    /**
     * @methodtype get
     * @return height
     */
    public double getHeight(){
        return this.height;
    }

    /**
     * @methodtype get
     * @return text
     */
    public String getStickerText() {
        return text;
    }

    /**
     * @methodtype get
     * @return Sticker Group
     */
    public StickerGroup getStickerGroup() {
        return stickerGroup;
    }
}
