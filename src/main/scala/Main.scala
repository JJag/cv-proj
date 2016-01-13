import java.io.PrintWriter


import org.opencv.core._
import org.opencv.features2d.{DescriptorExtractor, FeatureDetector, Features2d}
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.HOGDescriptor


object Main {
  def main(args: Array[String]) {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)

    val img = Imgcodecs.imread("img/mug.jpg")
    val grayImg = new Mat()

    Imgproc.cvtColor(img, grayImg, Imgproc.COLOR_RGB2GRAY)

    //    val parts = util.split(grayImg)
    //    parts.forEachIndexed { i, mat ->
    //        Imgcodecs.imwrite("img/mug$i.jpg", mat)
    //        util.split(mat).forEachIndexed { j, mat ->
    //            Imgcodecs.imwrite("img/mug$i-$j.jpg", mat)
    //        }
    //    }

    val hogDescriptors = new MatOfFloat()
    val winSize = new Size(128.0, 128.0)
    val blockSize = new Size(16.0, 16.0)
    val blockStride = new Size(8.0, 8.0)
    val cellSize = new Size(8.0, 8.0)
    val nbins = 9
    val hog = new HOGDescriptor(winSize, blockSize, blockStride, cellSize, nbins)
    Imgproc.resize(grayImg, grayImg, winSize)
    hog.compute(grayImg, hogDescriptors)
    //new PrintWriter("hog.txt").print(hogDescriptors.dump())

    val orb = DescriptorExtractor.create(DescriptorExtractor.ORB)
    val keyPoints = new MatOfKeyPoint()
    val orbDesc = new MatOfDouble()
    orb.compute(img, keyPoints, orbDesc)
    new  PrintWriter("orb.txt").print(orbDesc.dump())

    val fast = FeatureDetector.create(FeatureDetector.FAST)
    fast.detect(img, keyPoints)

    val outputImage = new Mat()
    val color = new Scalar(0.0, 0.0, 255.0)
    val flags = Features2d.DRAW_RICH_KEYPOINTS; // draw circles
    Features2d.drawKeypoints(img, keyPoints, outputImage, color, flags)

    Imgcodecs.imwrite("img/mug_keypoints.jpg", outputImage)


  }
}
