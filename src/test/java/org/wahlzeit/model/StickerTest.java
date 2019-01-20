/**
 * StickerTest
 *
 * Version: 1.0
 *
 * Date: 10.11.2018
 *
 * License: AGPLv3
 */

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StickerTest {

	private StickerManager sm;
	private Sticker s1;
	private Sticker s2;
	private Sticker s3;
	private Sticker s4;
	private Location location;

	@Before
	public void setUp() throws Exception {
		sm = StickerManager.getInstance();
		location = new Location(CartesianCoordinate.createCartesianCoordinate(0,0,0));
		s1 = sm.createSticker(2.0/3.0,0.0,"foo", StickerGroup.MUSIC);
		s2 = sm.createSticker(11.11111111,2323.2323,"bar", StickerGroup.POLITICS);
		s3 = sm.createSticker(0.0,2.0/3.0,"bazz", StickerGroup.SPORTS);
		s4 = sm.createSticker(0.0,2.0/3.0,"bazz", StickerGroup.SPORTS, location);
	}

	@Test
	public void testWidth(){
		assertEquals(2.0/3.0,s1.getType().getWidth());
		assertEquals(11.11111111,s2.getType().getWidth());
		assertEquals(0.0,s3.getType().getWidth());
	}

	@Test
	public void testHeight(){
		assertEquals(0.0, s1.getType().getHeight());
		assertEquals(2323.2323, s2.getType().getHeight());
		assertEquals(2.0/3.0, s3.getType().getHeight());
	}

	@Test
	public void testText(){
		assertEquals("foo",s1.getType().getStickerText());
		assertEquals("bar",s2.getType().getStickerText());
		assertEquals("bazz",s3.getType().getStickerText());
	}

	@Test
	public void testGroup(){
		assert(StickerGroup.MUSIC == s1.getType().getStickerGroup());
		assert(StickerGroup.POLITICS == s2.getType().getStickerGroup());
		assert(StickerGroup.SPORTS == s3.getType().getStickerGroup());
	}

	@Test
	public void testLocation(){
		assert(location.equals(s4.getLocation()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidWidth(){
		Sticker s_in = sm.createSticker(-10,0,"",StickerGroup.MUSIC);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidHeight(){
		Sticker s_in = sm.createSticker(0,-10,"",StickerGroup.MUSIC);
	}
}
