import java.io.File

import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_imgcodecs._
import org.bytedeco.javacpp.opencv_imgproc._

import Utils._
object Main {
  def main(args: Array[String]) {

    val img: Mat = OpenCVUtils.loadOrExit(new File("img/ppl.jpg"))
    val grayImg = new Mat()

    cvtColor(img, grayImg, COLOR_RGB2GRAY)

    val hogDescriptors = Hog.computePhog(grayImg, 3)
    hogDescriptors.foreach(println)

    Sift.compute(grayImg)

  }
}
