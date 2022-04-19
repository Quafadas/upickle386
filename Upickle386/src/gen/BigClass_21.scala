case class BigClass_21 (
	 field1:Double , 
	 field2:Double , 
	 field3:Double , 
	 field4:Option[String] , 
	 field5:Double , 
	 field6:String , 
	 field7:String , 
	 field8:Option[String] , 
	 field9:Double , 
	 field10:Double , 
	 field11:Double , 
	 field12:Double , 
	 field13:Double , 
	 field14:String , 
	 field15:Double , 
	 field16:Option[Double] , 
	 field17:Double , 
	 field18:Double , 
	 field19:Option[Double] , 
	 field20:Option[String] , 
	 field21:Option[Double] )
object BigClass_21 {
 
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
   implicit val rw_auth: ReadWriter[BigClass_21] = macroRW
}   
    