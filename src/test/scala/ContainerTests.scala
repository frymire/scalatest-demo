
import org.scalatest.Inspectors.forAll
import org.scalatest.{FlatSpec, Matchers}

// We can use high-level matching syntax to check the contents of containers.
class ContainerTests extends FlatSpec with Matchers {

  // You can check whether a container contains an element.
  "A bunch of containers" should "contain a specific element." in {
    List(1,2,3) should contain (2)
    Set(1,2,3) should contain (2)
    Array(1,2,3) should contain (2)
    Map('a' -> 1, 'b' -> 2, 'c' -> 3) should contain ('b' -> 2)
    "123" should contain ('2')
    Some(2) should contain (2)
  }

  // Or exactly one of a set of elements.
  they should "contain one of a set of elements." in {
    List(1, 2, 3, 4, 5) should contain oneOf (5, 7, 9)
    Some(7) should contain oneOf (5, 7, 9)
    "howdy" should contain oneOf ('a', 'b', 'c', 'd')
  }
  
  // This test will fail, because the container has more than one element in the set.
  it should "contain exactly one of the elements, in fact." in { List(1,2,3) should contain oneOf(2,3,4) }
  
  // Here are other ways to test membership in a container.  
  it should "contain at least one of the elements." in { List(1,2,3) should contain atLeastOneOf(2,3,4) }
  it should "contain at most one of the elements." in { List(1,2,3) should contain atMostOneOf(3,4,5) }
  it should "contain none of the elements." in { List(1,2,3) should contain noneOf(4,5) }
  it should "contain all of the elements." in { List(1,2,3) should contain allOf(1,3) }
  it should "contain specific elements, duplicates allowed." in { List(1, 2, 3, 2, 1) should contain only (1, 2, 3)} 
  
  // Check if the list is sorted.
  it should "be sorted." in { List(1, 2, 3) shouldBe sorted }
  
  
  // You can also check conditions on several elements in a container.  
  it should "contain elements between 0 and 10." in {
    
    val xs = List(1, 2, 3)
    
    // Verbose version...
    forAll (xs) { x =>
      x should be > 0 
      x should be < 10
    }
    
    // Cleaner syntax. Can also use 'every' instead of 'all'.
    all (xs) should (be > 0 and be < 10)
    
  }
    
  
  it should "have elements that satisfy some other restrictions." in {
    
    val xs = List(1, 2, 3, 4, 5)
    
    // These pass...    
    exactly (2, xs) should be <= 2
    atLeast(3, xs) should be < 5
    atMost(2, xs) should be >= 4
    between(2, 3, xs) should (be > 1 and be < 5)
        
    // And this one fails...
    exactly (2, xs) shouldEqual 2
    
  }
  
  
  it should "work for strings, too." in { atMost (2, "Hello.") shouldBe 'l' }

  
  // The same syntax works on Java collections.
  it should "work on Java collections as well." in {
    
    val xs = new java.util.ArrayList[Int]()
    xs shouldBe 'empty
    
    1 to 5 foreach { xs add _ }
    xs should have length 5   
    xs should contain (4)
   
  }
  
}
