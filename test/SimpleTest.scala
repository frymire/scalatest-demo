
import collection.mutable.Stack

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.tagobjects.Slow
import org.scalatest.AppendedClues._

class SimpleTest extends FlatSpec with Matchers {
  
  // Define a test. You can also use 'must' or 'can' instead of 'should'. 
  "A Stack" should "pop values in last-in-first-out order." in {
    
    // Arrange, Act...
    val stack = new Stack[Int]
    1 to 4 foreach { stack push _ }
    
    // Assert, 4 different ways.
    assertResult(4) { stack.pop }
    stack.pop should be (3)
    stack.pop shouldBe 2
    stack.pop shouldEqual 1
    
  }
  
  // Define another test for the same subject with 'it' or 'they'.
  it should "have size 0 when created." in { (new Stack[Int]) should have size 0 }
  
  // You can use "shouldBe '[property]" to check boolean methods. ScalaTest uses 
  // reflection to find either an empty() or isEmpty() boolean method.
  it should "be empty when created." in { (new Stack[Int]) shouldBe 'empty }
  
  // Checking for a negation allows you to separate 'should' and 'be' (which is a bit confusing).
  it should "not be empty after you add an element." in {
    
    val s = new Stack[Int]
    s push 3    
    s should not be 'empty
    
    // empty happens to be a specially defined in ScalaTest, so in this case, you could also say.
    s should not be empty 
    
  }
  
  
  // Now start a new subject to test whether a number is "close enough."
  // The first test will fail for the integer 5, due to type mismatch.
  "A real number" should "be approximately right." in { 5.0 shouldBe 4.9 +- 0.2 }
  "An integer" should "be approximately right." in { 5 shouldBe 6 +- 2 }
  
  // Test inequalities.
  it should "satisfy some inequalities." in {
    
    val x = 5
    x should be < 7
    x should be >= 0
    
    // You can also write logical combinations of matcher expressions.
    x should (be >= 0 and be < 7)
    
  }
  
  
  // Check option values.
  "Some options" should "be checked." in {
    
    val notAString: Option[String] = None
    val aString = Some("Hi.")
    
    notAString shouldBe None
    notAString shouldBe empty       
    
    aString shouldBe Some("Hi.")
    aString should contain ("Hi.")

    // Import OptionValues To make it a little cleaner.
    import org.scalatest.OptionValues._
    aString.value shouldBe "Hi."
    
    // You can also just check if the option is defined.
    aString shouldBe defined
    
  }
  
  
  // You can use 'should have' to check properties of an instance.
  "A book" should "have certain attributes." in {    
    case class Book(title: String, pubYear: Int)    
    val b = Book("Programming in Scala", 2008)    
    b should have ( 'title ("Programming in Scala"), 'pubYear (2008) )    
  }
  
  
  
  // The inside trait lets you make assertions after a pattern match.
  import org.scalatest.Inside._
  case class Name(first: String, last: String)  
  
  // Match the name to a pattern, and do a test on its contents.
  "The name" should "start with S." in {    
    inside( Name("Sarah", "Programmer") ) { case Name(first, _) => first should startWith ("S") }    
  }
  
  // If you just want to make sure the instance matches a pattern, use 'matchPattern'.  
  it should "have first name Sarah." in {
    Name("Jane", "Programmer") should matchPattern { case Name("Sarah", _) => }
  }
  
  
    
  // Equality is tested based on instance identities, so this test will fail.
  "Two equivalent arrays" should "be different instances." in { Array(1,2) should be theSameInstanceAs Array(1,2) }
  
  // But this one will pass.
  they should "be the same instance." in {
    val a = Array(1,2)
    val b = a
    a should be theSameInstanceAs b
  }
  
  // ScalaTest performs deep comparisons on arrays, however, so the following test passes.
  they should "be considered equal." in { Array(1,2) shouldEqual Array(1,2) }
  
  
  
  // You can add your own clues to the error messages.
  "1 + 1" should "prepend the error message with a clue if it doesn't sum correctly." in {
    withClue("Are you testing for the wrong value? ") { (1+1) shouldBe 1 }
  }
    
  it should "append the error message with a clue if it doesn't sum correctly." in {
    (1+1) shouldBe 1 withClue ". Are you testing for the wrong value?"    
  }
  
  
  // Match partial strings, even using regexes.
  "A string" should "be partially matched." in {
    val hi = "Hello world"  
    hi should startWith ("Hello")
    hi should endWith ("world")
    hi should include ("o w")
    hi should fullyMatch regex """[A-Z][elo]* world"""
    hi should startWith regex """[A-Z][elo]*"""
  }
  
  
  // Check type.
  "An instance" should "be of type Person." in {
    case class Person(name: String)
    val x = Person("Donald Trump")
    x shouldBe a [Person]
  }
  

  // You can use string normalizations to help with string comparisons.
  import org.scalactic.StringNormalizations._
  "Two case mismatched strings" should "be equal after being lowercased." in {
    "Hi" should equal ("hi") (after being lowerCased)
  }

  
  // You can use logical operators in asserts.
  "Some arithmetic test" should "satisfy one of two relations." in { assert(1 == 1 || 2 > 2) }
    

  // You can verify exceptions.
  it should "throw an error when you divide by zero." in {
    
    // Verify that no exception is thrown.
    noException should be thrownBy { val x = 0 / 1 }

    // These are equivalent (and pass).
    intercept[ArithmeticException] { 1 / 0 }
    an [ArithmeticException] should be thrownBy { 1 / 0 }
    
    // You can inspect an expected exception like this.
    val thrown = the [ArithmeticException] thrownBy { 1 / 0 }
    thrown should have message "/ by zero"

    // This fails, because it gets an ArithmeticException, not an IllegalArgumentException.    
    an [IllegalArgumentException] should be thrownBy { 1 / 0 }

  }
    
  
  // To ignore a test, change either 'it' or 'in' to 'ignore'
  ignore should "be ignored." in { assert(true) }  
  it should "be ignored again." ignore { assert(true) }
    
  // You can directly force a fail.
  it should "fail prematurely." in { fail("Why even try?") }
  
  // You can also indicate a pending test or tag it (for instance) as slow.
  it should "be written later." is (pending)
  it should "be faster." taggedAs(Slow) in { assert(true, "This takes too long.") }
  
  // You can cancel at test directly, for instance, if a necessary resource is unavailable.
  // Or, you can cancel one conditionally, if an assumption is unsatisfied.
  it should "be cancelled." in { cancel("Let's just cancel this one.") }
  it should "be cancelled, if an assumption isn't satisfied." in { 
    assume(0 == 1) withClue "Zero didn't equal one."
    assert(true) 
  }

}
