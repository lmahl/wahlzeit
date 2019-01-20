/**
 * SitckerTypeTest
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

public class StickerTypeTest {
	private StickerType st;

	@Before
	public void setUp() throws Exception {
		st = StickerType.createStickerType(10,10,"foo", StickerGroup.MUSIC);
	}

	@Test
	public void TestInstance(){
		assertEquals(10, st.getWidth(),0.0001);
		assertEquals(10, st.getHeight(),0.0001);
		assertEquals("foo", st.getStickerText());
		assertEquals(StickerGroup.MUSIC, st.getStickerGroup());
	}

	@Test
	public void TestSubType(){
		StickerType st2 = StickerType.createStickerType(11,10,"foo", StickerGroup.MUSIC);
		StickerType st3 = StickerType.createStickerType(12,10,"foo", StickerGroup.MUSIC);

		st.setParent(st2);
		st2.setParent(st3);

		assert(st.isSubtype(st3));
		assert(st2.isSubtype(st3));
		assert(st.isSubtype(st2));
		assert(!st3.isSubtype(st));
		assert(st.isSubtype(st));
	}

}
