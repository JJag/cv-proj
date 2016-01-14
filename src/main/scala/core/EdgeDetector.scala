package core

import org.bytedeco.javacpp.opencv_core.{Mat, Size}
import org.bytedeco.javacpp.opencv_imgproc._

object EdgeDetector {
  def process(img: Mat): Mat = {
    val edges = new Mat
    GaussianBlur( img, img, new Size(7, 7), 0, 0 , 0 )
    Canny(img, edges, 100, 175)
    edges
  }
}
