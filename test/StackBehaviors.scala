
import org.scalatest.FlatSpec

// This is used in the SharedTest Example.
trait StackBehaviors { this: FlatSpec =>

  // Define a behavior function for tests to be run for a stack that isn't empty. 
  // Note that you can use 'it' here without previously defining a subject.
  def nonEmptyStack(newStack: => MyStack[Int], lastItemAdded: Int) {

    it should "be non-empty" in { assert(!newStack.isEmpty) }

    it should "return the top item on peek" in { assert(newStack.peek === lastItemAdded) }

    it should "not remove the top item on peek" in {
      val stack = newStack
      val size = stack.size
      assert(stack.peek === lastItemAdded)
      assert(stack.size === size)
    }

    it should "remove the top item on pop" in {
      val stack = newStack
      val size = stack.size
      assert(stack.pop === lastItemAdded)
      assert(stack.size === size - 1)
    }
  }

  // Define a behavior function for tests to be run for a stack that isn't full. 
  def nonFullStack(newStack: => MyStack[Int]) {

    it should "not be full" in { assert(!newStack.isFull) }

    it should "add to the top on push" in {
      val stack = newStack
      val size = stack.size
      stack.push(7)
      assert(stack.size === (size + 1) )
      assert(stack.peek === 7)     
    }
  }
  
}
