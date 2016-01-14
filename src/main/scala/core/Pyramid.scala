package core

import org.bytedeco.javacpp.opencv_core.{Mat, Rect}

object Pyramid {

  def split(img: Mat): List[Mat] = {

    val width: Int = img.cols()
    val halfWidth: Int = width / 2
    val height: Int = img.rows()
    val halfHeight: Int = height / 2

    val img1 = new Mat(img, new Rect(0, 0, halfWidth, halfHeight))
    val img2 = new Mat(img, new Rect(halfWidth, 0, width - halfWidth, halfHeight))
    val img3 = new Mat(img, new Rect(0, halfHeight, halfWidth, height - halfHeight))
    val img4 = new Mat(img, new Rect(halfWidth, halfHeight, width - halfWidth, height - halfHeight))
    List(img1, img2, img3, img4)
  }
}
