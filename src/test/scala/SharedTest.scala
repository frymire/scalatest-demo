
import org.scalatest.FlatSpec

// Import behavior functions defined in the StackBehaviors trait to be reused across tests.
class SharedTest extends FlatSpec with StackBehaviors {

  // Create alternate stack fixtures to be fed to the tests.
  
  def emptyStack = new MyStack[Int]

  def stackWithOneItem = {
    val stack = new MyStack[Int]
    stack.push(9)
    stack
  }

  def stackWithOneItemLessThanCapacity = {
    val stack = new MyStack[Int]
    for (i <- 1 to 9) stack.push(i)
    stack
  }

  def fullStack = {
    val stack = new MyStack[Int]
    for (i <- 0 until stack.MAX) stack.push(i)
    stack
  }

  val lastValuePushed = 9

  
  // To run a set of tests predefined in a behavior trait, we use 'should behave like'.
  
  // An empty stack is non-full, so run that batch of tests, plus some additional specific ones.
  "A Stack (when empty)" should "be empty" in { assert(emptyStack.isEmpty) }
  it should behave like nonFullStack(emptyStack)
  it should "complain on peek" in { intercept[IllegalStateException] { emptyStack.peek } }
  it should "complain on pop" in { intercept[IllegalStateException] { emptyStack.pop } }
  
  // A stack with one item is non-empty and non-full, so we test both sets of tests.
  "A Stack (with one item)" should behave like nonEmptyStack(stackWithOneItem, lastValuePushed)
  it should behave like nonFullStack(stackWithOneItem)

  // A near capacity stack is also non-empty and non-full, so we test both sets of tests.
  "A Stack (with one item less than capacity)" should behave like 
    nonEmptyStack(stackWithOneItemLessThanCapacity, lastValuePushed)
  it should behave like nonFullStack(stackWithOneItemLessThanCapacity)

  // A full stack is non-empty, but not non-full, so we only test non-empty.
  "A Stack (full)" should "be full" in { assert(fullStack.isFull) }
  it should behave like nonEmptyStack(fullStack, lastValuePushed)
  it should "complain on a push" in { intercept[IllegalStateException] { fullStack.push(10) } }
  
}
