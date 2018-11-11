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

	private Sticker s1;
	private Sticker s2;
	private Sticker s3;

	@Before
	public void setUp() throws Exception {
		s1 = new Sticker(2.0/3.0,0.0,"foo", StickerGroup.MUSIC);
		s2 = new Sticker(11.11111111,2323.2323,"bar", StickerGroup.POLITICS);
		s3 = new Sticker(0.0,2.0/3.0,"bazz", StickerGroup.SPORTS);
	}

	@Test
	public void testWidth(){
		assertEquals(2.0/3.0,s1.getWidth());
		assertEquals(11.11111111,s2.getWidth());
		assertEquals(0.0,s3.getWidth());
	}

	@Test
	public void testHeight(){
		assertEquals(0.0, s1.getHeight());
		assertEquals(2323.2323, s2.getHeight());
		assertEquals(2.0/3.0, s3.getHeight());
	}

	@Test
	public void testText(){
		assertEquals("foo",s1.getStickerText());
		assertEquals("bar",s2.getStickerText());
		assertEquals("bazz",s3.getStickerText());
	}

	@Test
	public void testGroup(){
		assert(StickerGroup.MUSIC == s1.getStickerGroup());
		assert(StickerGroup.POLITICS == s2.getStickerGroup());
		assert(StickerGroup.SPORTS == s3.getStickerGroup());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidWidth(){
		Sticker s_in = new Sticker(-10,0,"",StickerGroup.MUSIC);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidHeight(){
		Sticker s_in = new Sticker(0,-10,"",StickerGroup.MUSIC);
	}
}
