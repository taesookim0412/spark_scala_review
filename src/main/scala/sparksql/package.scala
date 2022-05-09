import org.apache.spark.sql.{DataFrame, SparkSession}

class SparkSql(spark: SparkSession){
  def main(): Unit ={
    val df = this.readJsonAsDf(spark)
    this.dfOperations(df)
  }
  def readJsonAsDf(spark: SparkSession): DataFrame ={
    val df = spark.read.json("people.json")
    df.show()
    return df;
  }

  def dfOperations(df: DataFrame): Unit ={
    df.select("name").show();
  }




}