import org.apache.spark
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object Main {
  val field = "Field";

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("HelloWorld")
    val sc = new SparkContext(sparkConf);
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    // this.parallelize(sc);

    // this.persist(sc);

    //this.broadcastData(sc);

    this.readJsonAsDf(spark);

    // This is the data for the value.



    println("Hello world!")
  }

  def parallelize(sc: SparkContext): Unit ={
    // parallelize an array to RDD across clusters.
    val data = Array(1, 2, 3, 4, 5)
    val distData = sc.parallelize(data)
  }

  def persistAndBroadcast(sc: SparkContext): Unit ={
    // Read data from file or in memory -- lazily evaluate.
    val newData = sc.textFile("README.md")
    val linesLength = newData.map(s => s.length)
    val totalLengths = linesLength.reduce((a, b) => a + b)

    // save linesLength variable -- similar to ToList()
    linesLength.persist()
  }

  def broadcastData(sc: SparkContext): Unit ={
    val data = sc.textFile("README.md")
    val broadcastedData = sc.broadcast(data);

    val broadcastedObjectData = broadcastedData.value;
  }

  def transformAndPersist(sc: SparkContext): Unit ={
    val strDataArr = Array("A", "B", "C")
    val strData = sc.parallelize(strDataArr)
    val newStrData = this.doStuff(strData)

    val broadcastedData = sc.broadcast(newStrData);
  }

  def doStuff(rdd: RDD[String]): RDD[String] = {
    // store data to memory before transforming so this context is not sent to clusters.
    val field = this.field;
    return rdd.map(x => field + x)
  }

  def readJsonAsDf(spark: SparkSession): Unit ={
    val df = spark.read.json("people.json")
    df.show()
  }


}