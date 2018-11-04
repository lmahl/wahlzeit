/**
 * EmailServiceTestSuite
 *
 * Version: 1.0
 *
 * Date: 04.11.2018
 *
 * License: AGPLv3
 */
package org.wahlzeit.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.services.mailing.EmailServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({EmailAddressTest.class, EmailServiceTest.class})
public class EmailServiceTestSuite {

}
