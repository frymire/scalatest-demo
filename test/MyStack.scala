
// This is used in the SharedTest Example.
class MyStack[T] {

  val MAX = 10
  private val buf = new scala.collection.mutable.ListBuffer[T]

  def push(o: T) {
    if (!isFull)
      buf.prepend(o)
    else
      throw new IllegalStateException("can't push onto a full stack")
  }

  def pop(): T = {
    if (!isEmpty)
      buf.remove(0)
    else
      throw new IllegalStateException("can't pop an empty stack")
  }

  def peek: T = {
    if (!isEmpty)
      buf(0)
    else
      throw new IllegalStateException("can't pop an empty stack")
  }

  def isFull: Boolean = buf.size == MAX
  def isEmpty: Boolean = buf.size == 0
  def size = buf.size

  override def toString = buf.mkString("Stack(", ", ", ")")
  
}