package core

class Codebook(cb: IndexedSeq[Vector[Float]]) {

  private val book = cb.zipWithIndex

  private def distance(v1: Vector[Float], v2: Vector[Float]): Float = {
    require(v1.length == v2.length)
    (v1 zip v2).map { case (a, b) => (a - b).abs }.sum
  }

  def getCodeword(v: Vector[Float]): Int = {
    book.minBy {
      case (vec, i) => distance(vec, v)
    }._2
  }

  def getHistogram(desc: Seq[Vector[Float]]): Vector[Float] = {
    val bins = cb.length
    val words = desc.map(getCodeword)
    Vector.range(0, bins).map(i => words.count(word => word == i).toFloat)
  }

}
