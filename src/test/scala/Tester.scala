import sparkapps.SparkApp

class Tester {

  @org.junit.Test
  def test(){
    //Simple Test
    SparkApp.sparkJob("PathToWikipediaData");
  }
}
