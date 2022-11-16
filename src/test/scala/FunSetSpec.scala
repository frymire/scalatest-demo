
import org.scalatest.FunSuite
import org.scalatest.tagobjects.Slow

// FunSuite is just an alternative to FlatSpec that provides syntax more similar to JUnit.
class FunSetSpec extends FunSuite {

  // Define tests with 'test([test name])' and a test body in curly braces.
  test("An empty Set should have size 0") { assert(Set.empty.size == 0) }

  // To ignore a test, change 'test' to 'ignore'.
  ignore("Invoking head on an empty Set should produce NoSuchElementException") {
    intercept[NoSuchElementException] { Set.empty.head }
  }

  // You can indicate a pending test.
  test("An empty Set's isEmpty method should return false") (pending)

  // Tag a test by placing a tag object after the test name
  test("An empty Set's nonEmpty method should return true", Slow) { assert(!Set.empty.nonEmpty) }
  
}