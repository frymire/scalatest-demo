
import DatabaseServer._
import org.scalatest.{FlatSpec, Matchers}

import java.io.{File, FileWriter}

// Using standard Scala programming, you can use a Loan pattern to 
// compose fixtures made up of smaller mini-fixtures.
class LoanFixtureTest extends FlatSpec with Matchers {

  // Define a loan fixture method for a fixture with a database.
  def withDatabase(test: dbType => Any) {
    
    // Create the fixture
    val dbName = java.util.UUID.randomUUID.toString
    val db = createDb(dbName)
    db.append("ScalaTest is ")

    // Loan the fixture to the test, then clean it up.
    try test(db)
    finally removeDb(dbName)
    
  }

  // Define a loan fixture method for a fixture with a file and filewriter.
  def withFile(test: (File, FileWriter) => Any) {
    
    // Create the fixture
    val file = File.createTempFile("hello", "world")
    val writer = new FileWriter(file)
    writer.write("ScalaTest is ")
    
    // Loan the fixture to the test, then clean it up.
    try test(file, writer)
    finally writer.close()
    
  }

  
  // Run a test with the file fixture, by calling the withFile loan-fixture method.
  "Testing" should "be productive." in withFile { (file, writer) =>
    writer write "productive!"
    writer.flush()
    file should have length 24
  }

  // Run a test with the database fixture, by calling the withDatabase loan-fixture method.
  it should "be readable." in withDatabase { db =>
    db append "readable!"
    db.toString shouldBe "ScalaTest is readable!"
  }

  // Run a test that stacks both fixtures, by composing their loan-fixture methods.
  it should "be clear and concise." in withDatabase { db =>    

    withFile { (file, writer) => 
      
      // Act on both the database and file.
      db append "clear!"
      writer write "concise!"
      writer.flush()
      
      // Run asserts on both the database and file.
      db.toString shouldBe "ScalaTest is clear!"
      file should have length 21
      
    }
    
  }
  
}
