package ml

import weka.classifiers.functions.supportVector.Kernel
import weka.core._

class PyramidKernel(numLvl: Int) extends Kernel {
  var data: Instances = _

  override def eval(i1: Int, i2: Int, instance: Instance): Double = {
    val x = data.instance(i1)
    val y = data.instance(i2)

    ???
  }

  override def buildKernel(data: Instances): Unit = {
    this.data = data
  }


  override def globalInfo(): String = ""

  override def numCacheHits(): Int = 0

  override def numEvals(): Int = 0

  override def clean(): Unit = {}

  private def toArray(instance: Instance): Seq[Double] = {
    (0 until instance.numAttributes()).map(instance.value)
  }
}
