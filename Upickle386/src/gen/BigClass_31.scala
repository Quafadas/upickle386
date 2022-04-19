case class BigClass_31 (
	 field1:String , 
	 field2:Double , 
	 field3:String , 
	 field4:String , 
	 field5:Double , 
	 field6:Double , 
	 field7:Option[Double] , 
	 field8:String , 
	 field9:Option[String] , 
	 field10:Double , 
	 field11:Double , 
	 field12:Double , 
	 field13:String , 
	 field14:Double , 
	 field15:String , 
	 field16:Option[String] , 
	 field17:Option[String] , 
	 field18:Option[Double] , 
	 field19:Option[Double] , 
	 field20:Option[String] , 
	 field21:Option[String] , 
	 field22:Double , 
	 field23:Double , 
	 field24:Double , 
	 field25:Double , 
	 field26:String , 
	 field27:Double , 
	 field28:Option[Double] , 
	 field29:Double , 
	 field30:Double , 
	 field31:String )
object BigClass_31 {
 
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
   implicit val rw_auth: ReadWriter[BigClass_31] = macroRW
}   
    