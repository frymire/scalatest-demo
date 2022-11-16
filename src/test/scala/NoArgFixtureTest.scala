
import org.scalatest.{Failed, FlatSpec}

class NoArgFixtureTest extends FlatSpec {

  // Override withFixture if you want to do setup, tear-down, or something special  
  // with any failures. Use a NoArgTest if you don't need to pass any fixtures.
  override def withFixture(test: NoArgTest) = {

    // Run the test, and match its outcome for additional processing. Calling 
    // super.withFixture(test) instead of test() enables fixture stacking. 
    test() match {
      
      // Do special processing on any failures.
      case failed: Failed =>
        info("Caught a failed test.")
        failed
        
      case other => other
      
    }
    
  }

  "This test" should "succeed" in { assert(1 + 1 === 2) }
  it should "fail" in { assert(1 + 1 === 3) }
  
}