
project.the<SourceSetContainer>().apply {
  getByName("main") {
    withConvention(ScalaSourceSet::class) {
      scala.srcDir("../nejc4s/src/main/scala")
    }
    java.srcDir("../nejc4s/src/main/java")
  }
  getByName("test") {
    withConvention(ScalaSourceSet::class) {
      scala.srcDir("../nejc4s/src/test/scala")
    }
  }
}
val scalaCompilerPlugin: Configuration = configurations.create("scalaCompilerPlugin")

val scalaVersion: String by project
val scalaMinorVersion = project.name.substringAfterLast('_')

repositories {
  jcenter()
}
dependencies {
  scalaCompilerPlugin("org.scalamacros:paradise_$scalaVersion:2.1.1")
  "api"("org.scala-lang:scala-library:$scalaVersion")

  "testImplementation"("junit:junit:4.12")
  "testImplementation"("org.scalatest:scalatest_$scalaMinorVersion:3.0.5-M1")
  "testCompileOnly"("com.chuusai:shapeless_$scalaMinorVersion:2.3.3")
}

tasks.withType<ScalaCompile> {
  scalaCompileOptions.additionalParameters = listOf(
          "-Xplugin:" + scalaCompilerPlugin.asPath,
          "-Ypartial-unification",
          "-language:higherKinds",
          "-language:implicitConversions")
}
