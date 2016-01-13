import java.io.{File, PrintWriter}
import java.nio.FloatBuffer

import org.bytedeco.javacpp.annotation.StdVector
import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_features2d._
import org.bytedeco.javacpp.opencv_objdetect.HOGDescriptor
import org.bytedeco.javacpp.opencv_imgcodecs._
import org.bytedeco.javacpp.opencv_imgproc._
import org.bytedeco.javacpp._

object Main {
  def main(args: Array[String]) {

    val img: Mat = OpenCVUtils.loadOrExit(new File("test.jpg"))
    val grayImg = new Mat()

    cvtColor(img, grayImg, COLOR_RGB2GRAY)

    imwrite(s"sift.jpg", Sift.withKeyPoints(grayImg))

    val parts = Pyramid.split(grayImg)
    parts.zipWithIndex.foreach {
      case (mat, i) => imwrite(s"sift$i.jpg", Sift.withKeyPoints(mat))
    }

    //val hogDescriptors: Array[Float]
    val winSize = new Size(128, 128)
    val blockSize = new Size(16, 16)
    val blockStride = new Size(8, 8)
    val cellSize = new Size(8, 8)
    val nbins = 9

    //val hogBuffer = FloatB
    val hog = new HOGDescriptor(winSize, blockSize, blockStride, cellSize, nbins)
    resize(grayImg, grayImg, winSize)
    //hog.compute(grayImg, hogDescriptors)
    //new PrintWriter("hog.txt").print(hogDescriptors.dump())

  }
}
