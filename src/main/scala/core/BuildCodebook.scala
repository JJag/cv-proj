package core

import java.io.{File, FileWriter, PrintWriter}

import org.bytedeco.javacpp.opencv_core.Mat
import util.Utils._
import util.{Utils, OpenCVUtils}

object BuildCodebook extends App{

  val dirs: IndexedSeq[File] = new File("img").listFiles.filter(_.isDirectory)
  val files = dirs.flatMap(_.listFiles)
  val mats: IndexedSeq[Mat] = files.map(OpenCVUtils.loadOrExit(_))
  val sifts: IndexedSeq[Vector[Float]] = mats.flatMap(mat => toSeq(Sift.compute(mat)))
  // TODO VQ on sifts K = 1024
  val codewords: Seq[Vector[Float]] = Seq.fill(1024)(Vector.fill(128)(2000 * Math.random().toFloat - 1000))

  val csv = new File("codebook.csv")
  val writer = new PrintWriter(new FileWriter(csv))
  codewords.foreach(codeword => writer.println(codeword.mkString(",")))
}
