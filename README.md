# Sparkling Fun


This projects implements big data algorithms with a hands on approach and applying these algorithms to the twitter stream. 
The template for this project is based upon ludwsam's repo: (https://github.com/Ludwsam/SparkTemplate/)


## How-To use
To use the Project open intellij and import as sbt project.
To use the twitter stream, twitter api-keys are necessary which can be generated on https://apps.twitter.com/ and need to be filled into a scala object similar to:


```scala
package sparkapps

 object TwitterCredentials {
   val consumerKey = "***"
   val consumerSecret = "***"
   val accessToken = "***"
   val accessTokenSecret = "***"
 }
```