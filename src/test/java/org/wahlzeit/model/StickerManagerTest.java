/**
 * SitckerManagerTest
 *
 * Version: 1.0
 *
 * Date: 20.01.2018
 *
 * License: AGPLv3
 */
package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StickerManagerTest {

	private StickerManager sm;

	@Before
	public void setUp() throws Exception {
		sm = StickerManager.getInstance();
	}

	@Test
	public void testInstance(){
		StickerManager sm2 = StickerManager.getInstance();
		assert(sm.equals(sm2));
	}

	@Test
	public void testCreateSticker(){
		Sticker s1 = sm.createSticker(10, 10,"foo", StickerGroup.POLITICS);
		assert(s1 != null);
		assertEquals(10, s1.getType().getWidth(),0.0001);
		assertEquals(10, s1.getType().getHeight(),0.0001);
		assertEquals("foo", s1.getType().getStickerText());
		assertEquals(StickerGroup.POLITICS, s1.getType().getStickerGroup());
	}

	@Test
	public void testCreateStickerWithLocation(){
		Sticker s1 = sm.createSticker(10, 10,"foo", StickerGroup.POLITICS);
		assert(s1 != null);
		assertEquals(10, s1.getType().getWidth(),0.0001);
		assertEquals(10, s1.getType().getHeight(),0.0001);
		assertEquals("foo", s1.getType().getStickerText());
		assertEquals(StickerGroup.POLITICS, s1.getType().getStickerGroup());
	}

	@Test
	public void testCreateTwoObjectsOfSameType(){
		Sticker s1 = sm.createSticker(10, 10,"foo", StickerGroup.POLITICS);
		Sticker s2 = sm.createSticker(10, 10,"foo", StickerGroup.POLITICS);

		assert(s1.getType().equals(s2.getType()));
		assert(s1.getType().equals(s1.getType()));
	}

}
