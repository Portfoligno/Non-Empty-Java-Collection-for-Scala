plugins {
  maven
  scala
  `java-library`
}
val scalaCompilerPlugin: Configuration = configurations.create("scalaCompilerPlugin")

tasks.getByName<Wrapper>("wrapper") {
  gradleVersion = "5.3.1"
}

repositories {
  jcenter()
}
dependencies {
  scalaCompilerPlugin("org.scalamacros:paradise_2.12.8:2.1.1")
  api("org.scala-lang:scala-library:2.12.8")

  compileOnly("com.chuusai:shapeless_2.12:2.3.3")
}

tasks.withType<ScalaCompile> {
  scalaCompileOptions.additionalParameters = listOf(
          "-Xplugin:" + scalaCompilerPlugin.asPath,
          "-Ypartial-unification",
          "-language:higherKinds",
          "-language:implicitConversions")
}
