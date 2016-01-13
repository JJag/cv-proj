name := "cv-proj"

version := "1.0"

scalaVersion := "2.11.7"

classpathTypes += "maven-plugin"

libraryDependencies += "nz.ac.waikato.cms.weka" % "weka-stable" % "3.6.6"
libraryDependencies += "org.bytedeco" % "javacv" % "1.1"
libraryDependencies += "org.bytedeco.javacpp-presets" % "opencv" % "3.0.0-1.1"
libraryDependencies += "org.bytedeco.javacpp-presets" % "opencv" % "3.0.0-1.1" classifier "windows-x86_64"