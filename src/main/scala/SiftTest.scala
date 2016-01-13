import java.io.File

import OpenCVUtils._
import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_features2d._
import org.bytedeco.javacpp.opencv_xfeatures2d.SIFT

object SiftTest extends App{
  // Read input image
  val image = loadOrExit(new File("img/test.jpg"))

  // Detect SIFT features.
  val keyPoints = new KeyPointVector()
  val nFeatures = 0
  val nOctaveLayers = 3
  val contrastThreshold = 0.03
  val edgeThreshold = 10
  val sigma = 1.6
  val sift = SIFT.create(nFeatures, nOctaveLayers, contrastThreshold, edgeThreshold, sigma)
  sift.detect(image, keyPoints)

  // Draw keyPoints
  val featureImage = new Mat()

  (0L until keyPoints.size()).foreach { i =>
  }
  drawKeypoints(image, keyPoints, featureImage, new Scalar(255, 255, 255, 0), DrawMatchesFlags.DRAW_RICH_KEYPOINTS)
  show(featureImage, "SIFT Features")
}
