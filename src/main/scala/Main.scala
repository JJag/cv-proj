import java.io.File

import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_imgcodecs._
import org.bytedeco.javacpp.opencv_imgproc._

object Main {
  def main(args: Array[String]) {

    val img: Mat = OpenCVUtils.loadOrExit(new File("img/test.jpg"))
    val grayImg = new Mat()

    cvtColor(img, grayImg, COLOR_RGB2GRAY)

    imwrite(s"img/sift.jpg", Sift.withKeyPoints(grayImg))

    val hogDescriptors = Hog.compute(grayImg)
    hogDescriptors.foreach(println)

    Sift.compute(grayImg)

  }
}
