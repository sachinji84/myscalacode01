package com.intellij.SBT.p01

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

//This is a simple scala + SBT program with dependencies  in build.sbt file
//once we run this program. to run  it do the right click it and click on run and it shows the o/p in RUN consol under intellij
/*
Now to create the .jar file
open CMD and goto path where you have project in your PC
then put sbt package command and it will generate the .jar file

C:\Users\DELL\IdeaProjects\spark02_sbt_p01>sbt package
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=256m; support was removed in 8.0
[info] Loading global plugins from C:\Users\DELL\.sbt\1.0\plugins
[info] Loading project definition from C:\Users\DELL\IdeaProjects\spark02_sbt_p01\project
[info] Loading settings for project spark02_sbt_p01 from build.sbt ...
[info] Set current project to spark02_sbt_p01 (in build file:/C:/Users/DELL/IdeaProjects/spark02_sbt_p01/)
[info] Compiling 1 Scala source to C:\Users\DELL\IdeaProjects\spark02_sbt_p01\target\scala-2.11\classes ...
[info] Done compiling.
[info] Packaging C:\Users\DELL\IdeaProjects\spark02_sbt_p01\target\scala-2.11\spark02_sbt_p01_2.11-0.1.jar ...
[info] Done packaging.
[success] Total time: 8 s, completed May 29, 2019 9:33:01 AM

now you open  another CMD and submit spark-submit command to execute the code
spark-submit --class com.intellij.SBT.p01.wordcount_usingSBT C:\Users\DELL\IdeaProjects\spark02_sbt_p01\target\scala-2.11\spark02_sbt_p01_2.11-0.1.jar local D:\ETLHive\InputTestData\word.txt
*/

object wordcount_usingSBT {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordcountWithSBT").setMaster(args(0))
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