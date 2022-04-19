case class BigClass_16(
    field1: Option[String],
    field2: Double,
    field3: String,
    field4: Option[Double],
    field5: Double,
    field6: String,
    field7: Double,
    field8: String,
    field9: String,
    field10: Option[String],
    field11: Double,
    field12: String,
    field13: Option[Double],
    field14: String,
    field15: Double,
    field16: Double
)
object BigClass_16 {

  object OptionPickler extends upickle.AttributeTagged {
    override implicit def OptionWriter[T: Writer]: Writer[Option[T]] =
      implicitly[Writer[T]].comap[Option[T]] {
        case None    => null.asInstanceOf[T]
        case Some(x) => x
      }

    override implicit def OptionReader[T: Reader]: Reader[Option[T]] = {
      new Reader.Delegate[Any, Option[T]](implicitly[Reader[T]].map(Some(_))) {
        override def visitNull(index: Int) = None
      }
    }
  }
  import OptionPickler._
  implicit val rw_auth: ReadWriter[BigClass_16] = macroRW
}
