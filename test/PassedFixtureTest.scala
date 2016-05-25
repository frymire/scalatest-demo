
import org.scalatest.{fixture, Matchers}

// You can also pass multiple fixtures into tests with fixture.FlatSpec
class PassedFixtureTest extends fixture.FlatSpec with Matchers {
  
  // Define the type of fixture object to be passed to the test.
  type FixtureParam = (String, Array[Int]) 
  
  // Override withFixture to define a shared setup to be run before 
  // each test and do any cleanup afterwards.
  override def withFixture(test: OneArgTest) = {
    
    // Create a fixture object of type FixtureParam
    val fixture1 = ("123", Array(1, 2, 3)) 
    val fixture2 = ("4567", Array(4, 5, 6, 7))
    
    // Pass each fixture into each test. Each test has to pass for all fixtures to pass.
    test(fixture1)
    test(fixture2)

    // If you need to do any clean up, use try syntax, putting it in a finally block.
//    try test(fixture)
//    finally { /* cleanup here */ }

  }
  
  // The fixture is passed to the test as a local variable.
  "The passed fixture" should "have size 3." in { f =>  
    val (name, a) = f
    a should have size 3
  }
  
  it should "let you update entries." in { f =>
    val (name, a) = f
    a(0) = 8
    a(0) shouldBe 8
  }
  
}