case class BigClass_30 (
	 field1:Double , 
	 field2:Option[Double] , 
	 field3:Double , 
	 field4:String , 
	 field5:Double , 
	 field6:Double , 
	 field7:Double , 
	 field8:String , 
	 field9:String , 
	 field10:Double , 
	 field11:Double , 
	 field12:Option[String] , 
	 field13:Double , 
	 field14:Double , 
	 field15:String , 
	 field16:Double , 
	 field17:Double , 
	 field18:Double , 
	 field19:Option[Double] , 
	 field20:Double , 
	 field21:Option[Double] , 
	 field22:Double , 
	 field23:String , 
	 field24:Option[String] , 
	 field25:Double , 
	 field26:Option[Double] , 
	 field27:Double , 
	 field28:Double , 
	 field29:Double , 
	 field30:String )
object BigClass_30 {
 
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
   implicit val rw_auth: ReadWriter[BigClass_30] = macroRW
}   
    