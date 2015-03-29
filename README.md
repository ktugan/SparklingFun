# Sparkling Fun



This projects implements big data algorithms with a hands on approach and applying these algorithms to the twitter stream. 
The template for this project is based upon ludwsam's repo: (https://github.com/Ludwsam/SparkTemplate/)

![](https://raw.githubusercontent.com/wiki/ktugan/SparklingFun/screenshot-running.png)

## Requisites
- Intellij
- Scala Plugin
- Java 1.7 (Java 1.8 currently not working)

## How-To use
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
