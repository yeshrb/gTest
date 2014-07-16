package racetrack

import static org.junit.Assert.*
import org.junit.*

class RaceIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testRaceDateBeforeToday() {
        def lastWeek = new Date() - 7
		def race = new Race(startDate:lastWeek)
		
		assertFalse "Calidation should not succeed",race.validate()
		assertTrue "There should be errors",race.hasErrors()
		
		println "\nErrors:"
		println race.errors?:"no errors found"
		
		def badField = race.errors.getFieldError("startDate")
		println "\nBadField:"
		println badField?:"startDate wasn't a bad field"
		assertNotNull "Expecting to find an error on the startDate field",badField
		
		def code = badField?.codes.find{it == 'race.startDate.validator.invalid'}
		
		println "\nCode:"
		println code?: "the custom validator for startDate wasn`t found"
		assertNotNull "startDate field should be the culprit",code
		
    }
}
