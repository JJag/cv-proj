object Utils {

  implicit class RepeatMapSeq[T](col: Seq[T]) {

    def repeat(n: Int, f: T => Seq[T]): Seq[T]  = {
      if(n == 0)
        col.map(identity)
      else
        col.map(f).flatMap(_.repeat(n - 1, f))
    }
  }

  def main(args: Array[String]) {
    def f[T](x:T) = Seq.fill(2)(x)
    val xs = List(1, 2).repeat(3, f)
    println(xs)
  }
}
