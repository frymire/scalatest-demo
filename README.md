# scalatest-demo
Just some idioms to remind me how to use scalatest.

### Build with Maven
To build with Maven, first set the JAVA_HOME environment variable to a Java 1.8 JDK (e.g. `C:\Program Files\Java\jdk1.8.0_333`). Scala will not work with a later version of the JDK, even while referencing 1.8 functionality. You will see an error like this...

`[ERROR] error: scala.reflect.internal.MissingRequirementError: object java.lang.Object in compiler mirror not found.`

Additionally, make sure that the Path variable includes the `bin` folder of the same Java 1.8 SDK directory as it's first or only Java SDK entry.

Alternatively, temporarily set these variables in PowerShell like this...
`$env:JAVA_HOME = 'C:\Program Files\Java\jdk1.8.0_333'; $env:Path = $env:JAVA_HOME + ';' + $env:Path`

You can then run `mvn clean test` to see the test results. Note that there are failures meant to show the scalatest functionality, so this build will appear to fail.

Start exploring SimpleTest.scala to see the syntax. Then try ContainerTests.scala.

### Import into IntelliJ

If you import as a Maven project and try to build while it is referencing a JDK version later than 1.8, you may see the error

`scalac: Error: java.lang.RuntimeException: /packages cannot be represented as URI`

In Project Settings | Project | SDK, be sure to set a Java 1.8 JDK as described above for Maven builds.
