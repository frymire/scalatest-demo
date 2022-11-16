
import org.scalatest.{FlatSpec, Matchers}


class CodeAssertionsTest extends FlatSpec with Matchers {

  // You can even test code directly.
  
  "Some sample code" should "compile." in {
    assertCompiles("val a: Int = 1")    
  }
  
  it should "not compile." in {
    assertDoesNotCompile("val a: Int = x")    
  }
  
  it should "result in a type error." in {
    assertTypeError("val d: Double = 1.0; val a: Int = d")
  }
  
}