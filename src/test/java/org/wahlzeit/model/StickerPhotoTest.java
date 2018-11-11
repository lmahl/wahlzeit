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

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class StickerPhotoTest {
	private StickerPhoto s1;
	private StickerPhoto s2;
	private StickerPhoto s3;
	private StickerPhoto s4;
	private Sticker st;
	private Sticker st_a;

	private final LocalServiceTestHelper helper =
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());


	@Before
	public void setUp(){
		helper.setUp();
		st = new Sticker(1, 2,"", StickerGroup.MUSIC);
		st_a = new Sticker(2, 4,"foo", StickerGroup.SPORTS);
		s1 = new StickerPhoto();
		s2 = new StickerPhoto(st);
		s3 = new StickerPhoto(PhotoId.getRandomId());
		s4 = new StickerPhoto(st, PhotoId.getRandomId());
	}

	@After
	public void tearDown(){
		helper.tearDown();
	}

	@Test
	public void testInit(){

		s1 = new StickerPhoto(PhotoId.getRandomId());
		assertNotNull(s1);
		assertNotNull(s2);
		assertNotNull(s3);
		assertNotNull(s4);
	}

	@Test
	public void testGetSticker(){
		assertNull(s1.getSticker());
		assert(s2.getSticker() == st);
		assertNull(s3.getSticker());
		assert(s4.getSticker() == st);
	}

	@Test
	public void testSetSticker(){
		s1.setSticker(st_a);
		s2.setSticker(st_a);
		s3.setSticker(st_a);
		s4.setSticker(st_a);

		assert(s1.getSticker() == st_a);
		assert(s2.getSticker() == st_a);
		assert(s3.getSticker() == st_a);
		assert(s4.getSticker() == st_a);
	}
}
