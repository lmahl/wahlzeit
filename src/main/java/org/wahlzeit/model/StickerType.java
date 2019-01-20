/**
 * SitckerType
 *
 * Version: 1.0
 *
 * Date: 20.01.2018
 *
 * License: AGPLv3
 */
package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.PatternInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@PatternInstance(
		name = "Type Object Pattern",
		participants = {"Object Type"}
)
public class StickerType {
	private final double width;
	private final double height;
	private final String text;
	private final StickerGroup stickerGroup;
	private StickerType parent = null;

	private final static double EPSILON = 0.00001;
	private final static int MAX_TEXT_LENGTH = 200;
	private final static String ARGUMENT_SMALLER_0 = "Argument must be greater than 0";
	private final static String LOG_ARGUMENT_SMALLER_0 = "Argument must be greater than 0";
	private final static String TEXT_TOO_LONG = "Argument text must not be longer than " + MAX_TEXT_LENGTH +" characters";
	private final static String LOG_TEXT_TOO_LONG = "Argument text must not be longer than " + MAX_TEXT_LENGTH + "characters";

	private final static Logger log = Logger.getLogger(Sticker.class.getName());

	private static Map<String, StickerType> stickerTypes = new HashMap<String, StickerType>();


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
	private StickerType(double width, double height, String text, StickerGroup stickerGroup) throws IllegalArgumentException {
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

	public static StickerType createStickerType(double width, double height, String text, StickerGroup stickerGroup) throws IllegalArgumentException {
		String key = width + " " + height + " " + stickerGroup + " " + text;
		StickerType result = stickerTypes.get(key);

		if(result == null){
			result = new StickerType(width, height, text, stickerGroup);
			stickerTypes.put(key, result);
		}

		return result;
	}

	/**
	 * @Methodtype Factory
	 * Creates an instance of a Sticker with this Type as its Type in Type Object Pattern
	 * @return instance of Sticker
	 */
	public Sticker createInstance(){
		return new Sticker(this);
	}

	/**
	 * @Methodtype Factory
	 * Creates an instance of a Sticker with this Type as its Type in Type Object Pattern
	 * @param location Location the sticker is found
	 * @return instance of Sticker
	 */
	public Sticker createInstance(Location location){
		return new Sticker(this, location);
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

	@Override
	public boolean equals(Object other){
		if(!(other instanceof StickerType)){
			return false;
		}
		StickerType st = (StickerType)other;
		if(isDoubleEqual(this.height, st.getHeight())
				&& isDoubleEqual(this.width, st.getWidth())
				&& this.text.equals(st.getStickerText())
				&& this.stickerGroup == st.stickerGroup){
			return true;
		}

		return false;
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

	/**
	 * checks if two double values are equal within a specified precision
	 * @methodype helper
	 * @return whether the two values are equal
	 */
	private boolean isDoubleEqual(double a, double b) {
		return (Math.abs(a - b) < EPSILON);
	}

	public boolean isSubtype(StickerType other){
		if(other == null){
			return false;
		}

		if(parent == null){
			return false;
		}

		if(other.equals(parent)||other.equals(this)){
			return true;
		}

		return parent.isSubtype(other);
	}

	/**
	 * Set the Parent type of this type in the type hierarchy
	 * @Methodtype set
	 */
	public void setParent(StickerType parentType){
		this.parent = parentType;
	}

	/**
	 * @Methodtype get
	 */
	public StickerType getParent(){
		return this.parent;
	}


}
