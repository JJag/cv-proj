package core

import java.io.{File, FileWriter, PrintWriter}
import java.util.Random

import org.bytedeco.javacpp.opencv_core._
import util.OpenCVUtils
import weka.classifiers.Evaluation
import weka.classifiers.bayes.NaiveBayes
import weka.core.Instances
import weka.core.converters.CSVLoader

import scala.io.Source

object Main {
  def main(args: Array[String]) {

    val categories: IndexedSeq[File] = new File("img").listFiles.filter(_.isDirectory)
    val images: IndexedSeq[(String, Mat)] = categories.flatMap(c => {
      val files: IndexedSeq[File] = c.listFiles
      val mats = files.map(OpenCVUtils.loadOrExit(_))
      mats.map(mat => (c.getName, mat))
    })

    val csvLines = Source.fromFile(new File("codebook.csv")).getLines()
    val codewords = csvLines.map(line => line.split(",").toVector.map(_.toFloat)).toIndexedSeq
    val codebook = new Codebook(codewords)
    val extractor = new FeatureExtractor(codebook, 3)

    val dataset = images.map { case (c, mat) => (c, extractor.fullFeatures(mat)) }

    val tmpCsv = new File("data.txt")
    val writer = new PrintWriter(new FileWriter(tmpCsv))

    dataset.foreach {
      case (cat, vec) =>
        writer.println((vec ++ Vector(cat)).mkString(","))
    }
    writer.flush()
    writer.close()

    val loader = new CSVLoader
    loader.setNoHeaderRowPresent(true)
    loader.setSource(tmpCsv)
    val data: Instances = loader.getDataSet
    data.setClassIndex(data.numAttributes - 1)

    println(data.classAttribute().value(0))
    val naiveBayes = new NaiveBayes

    val eval = new Evaluation(data)

    eval.crossValidateModel(naiveBayes, data, 5, new Random)
    println(eval.toSummaryString())

  }
}
