package com.intellij.maven.p01

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

//This is the best example of a maven project in Intellij idea
//This is a Perfect example  of a maven project + pom.xml + maven install
//pom.xml used for dependencies
/*
//wordcount_With_maven is created using a maven project + spark and scala dependencies in pom.xml file
//and .jar has been created by clicking right side maven option and thn
//maven clean + maven package + maven install
//.jar file goes C:\Users\DELL\IdeaProjects\spark01\target\spark01-1.0-SNAPSHOT.jar
//same jar is avilable at C:\Users\DELL\.m2\repository\com\intellij\maven\p01\spark01\1.0-SNAPSHOT\spark01-1.0-SNAPSHOT.jar
spark-submit --class com.intellij.maven.p01.wordcount_With_maven C:\Users\DELL\.m2\repository\com\intellij\maven\p01\spark01\1.0-SNAPSHOT\spark01-1.0-SNAPSHOT.jar local D:\ETLHive\InputTestData\word.txt
 */

object wordcount_With_maven {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordcount").setMaster(args(0))
    val sc = new SparkContext(conf)
    val inputpath = args(1)
    //val outputpath = args(2)
    val wc = sc.textFile(inputpath).
      flatMap(line => line.split(" ")).
      map(word => (word, 1)).
      reduceByKey((acc, value) => acc + value)
    wc.take(100).foreach(println)
    //wc.saveAsTextFile(outputpath)
  }
}