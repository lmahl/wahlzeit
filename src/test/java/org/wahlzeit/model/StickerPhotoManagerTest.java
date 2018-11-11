/**
 * StickerPhotoManagerTest
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

import static junit.framework.TestCase.assertNotNull;

public class StickerPhotoManagerTest {

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
		assertNotNull(StickerPhotoManager.getInstance());

		//initialized once before
		assertNotNull(StickerPhotoManager.getInstance());
	}
}
