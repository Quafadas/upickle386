import scala.util.Random
import mill._, scalalib._

val sizes = Seq("16", "21", "27", "30", "31", "32", "64", "250")

object generator extends mill.Cross[GenModule](sizes:_*)
class GenModule(size: String) extends Module {  

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
 
    object OptionPickler extends upickle.AttributeTagged {
      override implicit def OptionWriter[T: Writer]: Writer[Option[T]] =
        implicitly[Writer[T]].comap[Option[T]] {
          case None => null.asInstanceOf[T]
          case Some(x) => x
        }

      override implicit def OptionReader[T: Reader]: Reader[Option[T]] = {
        new Reader.Delegate[Any, Option[T]](implicitly[Reader[T]].map(Some(_))){
          override def visitNull(index: Int) = None
        }
      }
    }
    import OptionPickler._
   implicit val rw_auth: ReadWriter[BigClass_$size] = macroRW
}   
    """
    builder.toString()

    //os.Source(builder.toString())
  }

  def writeSource = T.sources {
    val filename = s"BigClass_$size.scala"
    val thisPath = T.ctx().dest / filename
    os.write.over(T.workspace / "Upickle386" / "src" / "gen" / filename , generate)
    os.write( thisPath  ,  generate )
    thisPath
  }
}

object Upickle386 extends ScalaModule {

   def scalacOptions = Seq[String](
    "-Xmax-inlines:128", // 64 solves the problem up to some point, 128 gets stack overflow
  )
  
  def ivyDeps = Agg {
    ivy"com.lihaoyi::upickle:1.6.0"
  }

  def scalaVersion = "3.1.1"

  //def dependsOn = generator

  //def additionalSources = T.sources { os.Path(generator(size).generate) }
  //override def allSources = super.allSources()
 

  //def dependsOn = generator(size).generate

}
