
import org.scalatest.{FlatSpec, Matchers}

class SimpleCommonFixtureTest extends FlatSpec with Matchers {

  // To avoid duplicating code across tests, a simple strategy is to just use Scala
  // to define a common fixture method to be reused in each test. Note that this approach 
  // doesn't help you clean up after each test is run, as a BeforeAndAfter trait would.
  def commonFixture = new { val builder = new StringBuilder("ScalaTest is ")  }

  // Run a test, starting from the common fixture definition.
  "Testing" should "be easy" in {
    val f = commonFixture
    f.builder append "easy!"
    f.builder.toString shouldBe "ScalaTest is easy!"
  }

  // When we construct a new fixture from the common definition in the second test,
  // the "easy!" string from the first test is gone.
  it should "be fun" in {
    val f = commonFixture
    f.builder append "fun!"
    f.builder.toString shouldBe "ScalaTest is fun!"
  }

  
}