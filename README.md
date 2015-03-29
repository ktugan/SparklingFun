# Sparkling Fun

Sparkling Fun is a kind of micro-framework to test different types of algorithms on the twitter stream. Its the result of the course *Modern Algorithms for Big Data* at the Freie Universität of Berlin. It allows the easy implementation of algorithms to encapsulate the use of the twitter stream, without needing to know spark-functionality or inner workings.

The visualization happens either over JavaFX and their chart functionality, the console or any provider you chose to implement.
The template for this project is based upon ludwsam's repo: (https://github.com/Ludwsam/SparkTemplate/)

![](https://raw.githubusercontent.com/wiki/ktugan/SparklingFun/screenshot-running.png)

## Requisites
- Intellij
- Scala Plugin
- Java 1.7 (Java 1.8 currently not working)

## Configuration
Open Intellij and import as sbt project. Twitter API keys are necessary and can be generated at <https://apps.twitter.com/>.
These need to be filled into the following File ```src/main/scala/sparkapps/TwitterCredentials.scala```.
```
package sparkapps

object TwitterCredentials_example {
  val consumerKey = "***"
  val consumerSecret = "***"
  val accessToken = "***"
  val accessTokenSecret = "***"
}
```

You can also find an example configuration in the same folder.

## How-To
### Step 1:
Implement an algorithm, extending from the BigDataAlgorithm class. It is necessary to make the new algorithm from type object and not class. It allows Spark to synchronize the object between the instances.:

```
abstract class BigDataAlgorithm extends Serializable{
  val name = this.getClass.getSimpleName

  def calculate(x: RDD[Status])
  def getResults: String
}
```

Example:
```
object SimpleCounter extends BigDataAlgorithm {
  var i = 0
  override def calculate(rdd: RDD[Status]): Unit = {
    rdd.foreach(count)
  }
  private def count(status: Status): Unit = {
    i += 1
  }
  override def getResults: String = i.toString
}
```

### Step 2:
Add the algorithm to the load function of the ```Algorithms``` class.

```
object Algorithms {
  def load(): Seq[BigDataAlgorithm] ={
    List(
    //algorithms
    MyNewAlgorithm
    )
  }
}
```

### Step 3:
Start the application, choose your algorithms and lean back, enjoy
