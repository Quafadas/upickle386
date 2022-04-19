case class BigClass_64 (
	 field1:String , 
	 field2:Option[String] , 
	 field3:Option[Double] , 
	 field4:String , 
	 field5:Double , 
	 field6:Option[String] , 
	 field7:Double , 
	 field8:Option[String] , 
	 field9:Double , 
	 field10:Double , 
	 field11:String , 
	 field12:Double , 
	 field13:Double , 
	 field14:Double , 
	 field15:Double , 
	 field16:Double , 
	 field17:Double , 
	 field18:Option[String] , 
	 field19:Double , 
	 field20:Double , 
	 field21:Option[String] , 
	 field22:String , 
	 field23:Option[Double] , 
	 field24:Option[String] , 
	 field25:Double , 
	 field26:Option[String] , 
	 field27:Double , 
	 field28:String , 
	 field29:Option[String] , 
	 field30:Option[String] , 
	 field31:Double , 
	 field32:String , 
	 field33:String , 
	 field34:Double , 
	 field35:Option[Double] , 
	 field36:Option[String] , 
	 field37:Option[String] , 
	 field38:Double , 
	 field39:Option[Double] , 
	 field40:String , 
	 field41:Double , 
	 field42:String , 
	 field43:Double , 
	 field44:Option[String] , 
	 field45:String , 
	 field46:Option[String] , 
	 field47:Double , 
	 field48:Double , 
	 field49:Double , 
	 field50:Option[String] , 
	 field51:Double , 
	 field52:Double , 
	 field53:String , 
	 field54:Double , 
	 field55:String , 
	 field56:Double , 
	 field57:Double , 
	 field58:Double , 
	 field59:Option[String] , 
	 field60:String , 
	 field61:Double , 
	 field62:Double , 
	 field63:Option[Double] , 
	 field64:String )
object BigClass_64 {
 
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
   implicit val rw_auth: ReadWriter[BigClass_64] = macroRW
}   
    