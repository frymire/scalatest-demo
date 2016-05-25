
// This is used in the LoanFixtureTest example.
object DatabaseServer { 
  
  type dbType = StringBuffer
  
  private val databases = new java.util.concurrent.ConcurrentHashMap[String, dbType]
  
  def createDb(name: String): dbType = {
    val db = new StringBuffer
    databases.put(name, db)
    db
  }
  
  def removeDb(name: String) { databases.remove(name) }
  
}