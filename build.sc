import scala.util.Random
import mill._, scalalib._

val sizes = Seq("32", "33")

object generator extends mill.Cross[GenModule](sizes:_*)
class GenModule(size: String) extends ScalaModule {

  def scalaVersion = "3.1.1"

   def generate : String = {
    val builder = new StringBuilder()
    builder ++= s"case class BigClass_$size (\n"
    for(i <- 1 to size.toInt) {
      val r = Random.nextDouble()
      val typeRand = r match {
        case r if r <= 0.5 => "Double"
        case r if r <= 0.75 => "String"
        case r if r <= 0.9 => "Option[String]"
        case r if r <= 1 => "Option[Double]"
      }

      builder ++= s"\t field$i:$typeRand "
      if(i != size.toInt) builder ++= ", \n" else "\n"
    }    
    builder ++= ")"

    builder ++= s"""
object BigClass_$size {
 
  implicit def optionReader : Reader[Option[T]] = {
    new Reader.Delegate[Any, Option[T]](implicitly[Reader[T]].map(Some(_))) {
      override def visitNull(index: Int) = None
    }
  } 
   implicit val rw_auth: ReadWriter[BigClass_$size] = macroRW
}   
    """
    builder.toString()

    //os.Source(builder.toString())
  }

  def writeSource = T.sources {
    val filename = s"BigClass_$size.scala"
    val thisPath = T.ctx().dest / filename
    os.write( thisPath  ,  generate )
    thisPath
  }
}

object Upickle386 extends mill.Cross[GenModule](sizes:_*)
class Upickle386(size: String) extends mill.Cross[GenModule](sizes:_*) {
  

  def ivyDeps = Agg {
    ivy"upickle::upickle:1.6.0"
  }

 // def srcPath = T.sources(path)

  //def additionalSources = T.sources { os.Path(generator(size).generate) }
  override def allSources = super.allSources() ++ generator(size).writeSource
 

  //def dependsOn = generator(size).generate

}
