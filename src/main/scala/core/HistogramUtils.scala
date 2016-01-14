package core

object HistogramUtils {

  def intersection(h1: Array[Float], h2: Array[Float]): Float = {
    if(h1.length != h2.length)
      throw new IllegalArgumentException("lengths does not match")
    (h1 zip h2).map{case (x, y) => Math.max(x, y)}.sum
  }
}
