package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.AllHandlersTests;
import org.wahlzeit.model.AllModelTests;
import org.wahlzeit.services.AllServicesTests;
import org.wahlzeit.utils.AllUtilsTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllHandlersTests.class,
        AllModelTests.class,
        AllServicesTests.class,
        AllUtilsTests.class,
})

public class AllTests {
}