/**
 * StickerPhotoFactoryTest
 *
 * Version: 1.0
 *
 * Date: 10.11.2018
 *
 * License: AGPLcv3
 */

package org.wahlzeit.model;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.exceptions.FailedToCreateInstanceException;

import static junit.framework.TestCase.assertNotNull;

public class StickerPhotoFactoryTest {

	private final LocalServiceTestHelper helper =
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());


	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown(){
		helper.tearDown();
	}

	@Test
	public void testSingleton(){
		//lazy init
		assertNotNull(StickerPhotoFactory.getInstance());

		//initialized once before
		assertNotNull(StickerPhotoFactory.getInstance());
	}

	@Test
	public void testCreateSticker() throws FailedToCreateInstanceException {
		StickerPhotoFactory factory = StickerPhotoFactory.getInstance();
		Sticker st = new Sticker(1, 2, "foo", StickerGroup.MUSIC);
		StickerPhoto createdPhoto = new StickerPhoto(st,PhotoId.getRandomId());
		StickerPhoto factoryPhoto = factory.createStickerPhoto(PhotoId.getRandomId(),st);

		assert(createdPhoto.getSticker() == factoryPhoto.getSticker());
		assertNotNull(factory.createStickerPhoto());
	}
}
