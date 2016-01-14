package core

import org.bytedeco.javacpp.opencv_core.Mat
import org.bytedeco.javacpp.opencv_imgproc._
import util.Utils

class FeatureExtractor(codebook: Codebook, lvl: Int) {

  var counter = 0

  def fullFeatures(img: Mat): Vector[Float] = {
    val edges: Mat = EdgeDetector.process(img)
    val grayImg = new Mat
    cvtColor(img, grayImg, COLOR_RGB2GRAY)

    val hogVec: Vector[Float] = Hog.computePhog(edges, lvl)
    val siftDescriptors: Seq[Vector[Float]] = Utils.toSeq(Sift.compute(grayImg))
    val bowVec = codebook.getHistogram(siftDescriptors)
    hogVec ++ bowVec
    //hogVec
  }
}
