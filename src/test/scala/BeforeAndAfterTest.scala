
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class BeforeAndAfterTest extends FlatSpec with BeforeAndAfter with Matchers {

  // NOTE: Overriding withFixture is the preferred method for reusing fixtures.
  // It is very dangerous to share mutable fixtures across tests, since you
  // must reset the state after each test. But let's do it anyway. 

  // To do parallel tests, you would need to mix in the ParallelTestExecution trait, 
  // so that different threads don't overwrite the fixture.
  val builder = new StringBuilder
   
  // Define what should happen before and after each test. Note that if there is a 
  // failure during one of these methods, the entire test suite will abort.
  before { builder append "ScalaTest is " }
  after { 
//    fail("This will abort the test suite."); 
    builder.clear()     
  }

  "Testing" should "be easy." in {    
    builder append "easy!"
    builder.toString shouldBe "ScalaTest is easy!"    
  }

  
  // This will fail if you didn't run the 'after' block.
  it should "be fun." in {
    builder append "fun!"
    builder.toString shouldBe "ScalaTest is fun!"
  }
  
}