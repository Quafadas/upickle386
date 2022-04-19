case class BigClass_27 (
	 field1:Double , 
	 field2:String , 
	 field3:Double , 
	 field4:Option[Double] , 
	 field5:Double , 
	 field6:String , 
	 field7:Option[String] , 
	 field8:Double , 
	 field9:Double , 
	 field10:Double , 
	 field11:Double , 
	 field12:String , 
	 field13:String , 
	 field14:Option[Double] , 
	 field15:Double , 
	 field16:Option[String] , 
	 field17:Option[String] , 
	 field18:Option[String] , 
	 field19:Option[String] , 
	 field20:Option[String] , 
	 field21:Option[String] , 
	 field22:Option[String] , 
	 field23:Double , 
	 field24:Double , 
	 field25:String , 
	 field26:Option[String] , 
	 field27:Option[String] )
object BigClass_27 {
 
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
   implicit val rw_auth: ReadWriter[BigClass_27] = macroRW
}   
    