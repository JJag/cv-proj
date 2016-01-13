object Utils {

  implicit class RepeatMapSeq[T](col: Seq[T]) {

    def repeat(n: Int, f: T => Seq[T]): Seq[T]  = {
      if(n == 0)
        col.map(identity)
      else
        col ++ col.map(f).flatMap(_.repeat(n - 1, f))
    }
  }

  def main(args: Array[String]) {
    def f(x:Int) = Seq.fill(2)(x / 2)
    val xs = List(16).repeat(2, f)
    println(xs)
  }
}
