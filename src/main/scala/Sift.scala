import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_features2d._
import org.bytedeco.javacpp.opencv_xfeatures2d.SIFT

object Sift{

  private val sift: SIFT = {
    val nFeatures = 0
    val nOctaveLayers = 3
    val contrastThreshold = 0.03
    val edgeThreshold = 10
    val sigma = 1.6
    SIFT.create(nFeatures, nOctaveLayers, contrastThreshold, edgeThreshold, sigma)
  }

  def detect(img: Mat): KeyPointVector = {
    val keyPoints = new KeyPointVector()
    sift.detect(img, keyPoints)
    keyPoints
  }

  def compute(img: Mat): Mat = {
    val keyPoints = detect(img)
    val descriptors = new Mat
    sift.compute(img, keyPoints, descriptors)
    descriptors
  }

  def withKeyPoints(img: Mat): Mat = {
    val featureImage = new Mat()
    val keyPoints = detect(img)
    drawKeypoints(img, keyPoints, featureImage, new Scalar(255, 255, 255, 0), DrawMatchesFlags.DRAW_RICH_KEYPOINTS)
    featureImage
  }
}
