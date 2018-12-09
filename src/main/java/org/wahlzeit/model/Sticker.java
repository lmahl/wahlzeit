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

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class Sticker {
	private final double width;
	private final double height;
	private final String text;
	private final StickerGroup stickerGroup;
	private final static int MAX_TEXT_LENGTH = 200;
	private final static String ARGUMENT_SMALLER_0 = "Argument must be greater than 0";
	private final static String LOG_ARGUMENT_SMALLER_0 = "Argument must be greater than 0";
	private final static String TEXT_TOO_LONG = "Argument text must not be longer than " + MAX_TEXT_LENGTH +" characters";
	private final static String LOG_TEXT_TOO_LONG = "Argument text must not be longer than " + MAX_TEXT_LENGTH + "characters";

	private final static Logger log = Logger.getLogger(Sticker.class.getName());

	/**
	 * @methodtype constructor
	 * @pre width must  be greater than zero
	 * @pre height must be greater than zero
	 * @pre text can not be longer than 200 charcters
	 * @param width width of the sticker in cm
	 * @param height height of the sticker in cm
	 * @param text any text written on the sticker
	 * @param stickerGroup kind of sticker
	 */
	public Sticker(double width, double height, String text, StickerGroup stickerGroup) throws IllegalArgumentException {
		assertIsGreaterZero(width);
		assertIsGreaterZero(height);
		assertNotNull(width);
		assertNotNull(height);
		assertNotNull(text);
		assertNotNull(stickerGroup);
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
	private void assertIsGreaterZero(double val) throws IllegalArgumentException{
		if(val<0){
			IllegalArgumentException ex = new IllegalArgumentException(ARGUMENT_SMALLER_0);
			log.warning(LogBuilder.createSystemMessage().
					addException(LOG_ARGUMENT_SMALLER_0, ex).toString());
			throw ex;
		}
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

	/**
	 * Checks if the argumetn is smaller than the maximum allowed size
	 * @methodtype assertion
	 * @param text
	 * @throws IllegalArgumentException
	 */
	private void assertValidStickerText(String text) throws IllegalArgumentException{
		if(text.length() > MAX_TEXT_LENGTH){
			IllegalArgumentException ex = new IllegalArgumentException(TEXT_TOO_LONG);
			log.warning(LogBuilder.createSystemMessage().
					addException(LOG_TEXT_TOO_LONG, ex).toString());
			throw ex;
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
