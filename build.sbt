name := "cv-proj"

version := "1.0"

scalaVersion := "2.11.7"

classpathTypes += "maven-plugin"

libraryDependencies += "nz.ac.waikato.cms.weka" % "weka-dev" % "3.7.13"
libraryDependencies += "org.bytedeco" % "javacv" % "1.1"
libraryDependencies += "org.bytedeco.javacpp-presets" % "opencv" % "3.0.0-1.1"
libraryDependencies += "org.bytedeco.javacpp-presets" % "opencv" % "3.0.0-1.1" classifier "windows-x86_64"