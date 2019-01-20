/**
 * AllTests
 *
 * Version: 1.0
 *
 * Date: 04.11.2018
 *
 * License: AGPLv3
 */
package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.TellFriendTest;
import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.AbstractAdapterTest;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;
import org.wahlzeit.services.EmailServiceTestSuite;
import org.wahlzeit.services.LogBuilderTest;
import org.wahlzeit.utils.StringUtilTest;
import org.wahlzeit.utils.VersionTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({TellFriendTest.class, DatastoreAdapterTest.class,
        AccessRightsTest.class, CoordinateTest.class, FlagReasonTest.class, GenderTest.class, GuestTest.class,
        LocationTest.class, PhotoFilterTest.class, TagsTest.class, UserStatusTest.class, ValueTest.class,
        EmailServiceTestSuite.class, LogBuilderTest.class, StringUtilTest.class, VersionTest.class, StickerTest.class,
        StickerPhotoTest.class, StickerPhotoManagerTest.class, StickerPhotoFactoryTest.class,
        SphericCoordinateTest.class, CartesianCoordinateTest.class, CoordinateTest.class, StickerManagerTest.class,
		StickerTypeTest.class})
public class AllTests {

}
