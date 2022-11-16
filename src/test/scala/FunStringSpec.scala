
import org.scalatest.tags.Slow
import org.scalatest.{DoNotDiscover, FunSuite, Ignore, fixture}

// You can also pass fixtures into tests with fixture.FunSuite
class FunStringSpec extends fixture.FunSuite {
  
  // Define the type of fixture object to be passed to the test.
  type FixtureParam = String
  
  // Define a shared setup to be run before each test.
  override def withFixture(test: OneArgTest) = {
    
    // Create a fixture object of type FixtureParam
    val fixture = "a fixture" 
    
    // Pass the fixture into the test
    try test(fixture) 
    
    // Perform any shared cleanup at end of each test
    finally {}
    
  }
  
  // The fixture is passed to the test as a local variable.
  test("The passed fixture can be used in the test") { f => assert(f == "a fixture") }
  
}

@DoNotDiscover // Disable discovery of a test class
class InvisibleSpec extends FunSuite { /*code omitted*/ }

@Ignore // Ignore all tests in a test class
class IgnoredSpec extends FunSuite { /*code omitted*/ }

@Slow // Mark all tests in a test class with a tag 
class SlowSpec extends FunSuite { /*code omitted*/ }