package core

import org.bytedeco.javacpp.FloatPointer
import org.bytedeco.javacpp.opencv_core.{Mat, Size}
import org.bytedeco.javacpp.opencv_imgproc._
import org.bytedeco.javacpp.opencv_objdetect.HOGDescriptor
import util.Utils
import util.Utils._

import scala.collection._

object Hog {

  private val IMG_SIZE = 128
  private val winSize = new Size(IMG_SIZE, IMG_SIZE)
  private val blockSize = new Size(IMG_SIZE, IMG_SIZE)
  private val blockStride = new Size(IMG_SIZE, IMG_SIZE)
  private val cellSize = new Size(IMG_SIZE, IMG_SIZE)
  private val nbins = 20
  val hog: HOGDescriptor = new HOGDescriptor(winSize, blockSize, blockStride, cellSize, nbins)

  val hogFeatures = hog.getDescriptorSize.toInt

  def compute(img: Mat): Vector[Float] = {
    val scaledImg = new Mat
    resize(img, scaledImg, new Size(IMG_SIZE, IMG_SIZE))
    val descriptors = new FloatPointer()
    hog.compute(scaledImg, descriptors)
    val arr = new Array[Float](descriptors.limit())
    descriptors.get(arr)
    arr.toVector
  }

  def computePhog(img: Mat, lvl: Int): Vector[Float] = {
    val pyramid: Seq[Mat] = Seq(img).repeat(lvl, Pyramid.split)
    pyramid.flatMap(compute).toVector
  }
}
