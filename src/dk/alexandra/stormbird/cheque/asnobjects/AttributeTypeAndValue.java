/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.4.2, Date: 22-Jul-2020.
 */
package dk.alexandra.stormbird.cheque.asnobjects;

import com.objsys.asn1j.runtime.*;

public class AttributeTypeAndValue extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_InformationFrameworkRtkey._rtkey);
      Asn1Type._setLicLocation(_InformationFrameworkRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "AttributeTypeAndValue";
   }

   public AttributeType type;
   public AttributeValue value;

   public AttributeTypeAndValue () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public AttributeTypeAndValue (
      AttributeType type_,
      AttributeValue value_
   ) {
      super();
      type = type_;
      value = value_;
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public AttributeTypeAndValue (int[] type_,
      AttributeValue value_
   ) {
      super();
      type = new AttributeType (type_);
      value = value_;
   }

   public void init () {
      type = null;
      value = null;
   }

   public int getElementCount() { return 2; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return type;
         case 1: return value;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "type";
         case 1: return "value";
         default: return null;
      }
   }


   public void decode
      (Asn1BerDecodeBuffer buffer, boolean explicit, int implicitLength)
      throws Asn1Exception, java.io.IOException
   {
      int llen = (explicit) ?
         matchTag (buffer, Asn1Tag.SEQUENCE) : implicitLength;

      init ();

      // decode SEQUENCE

      Asn1BerDecodeContext _context =
         new Asn1BerDecodeContext (buffer, llen);

      IntHolder elemLen = new IntHolder();

      // decode type

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.PRIM, 6, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("type", -1);

         this.type = new AttributeType();
         this.type.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("type", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "type");

      // decode value

      if (!_context.expired()) {
         buffer.getContext().eventDispatcher.startElement("value", -1);

         this.value = new AttributeValue();
         this.value.decode (buffer, true, 0);

         buffer.getContext().eventDispatcher.endElement("value", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "value");

      if (!_context.expired()) {
         Asn1Tag _tag = buffer.peekTag ();
         if (_tag.equals (Asn1Tag.UNIV, Asn1Tag.PRIM, 6))  {
            throw new Asn1UnexpectedElementException();
         }

      }
   }

   public int encode (Asn1BerEncodeBuffer buffer, boolean explicit)
      throws Asn1Exception
   {
      int _aal = 0, len;

      // encode value

      if (this.value != null) {
         buffer.getContext().eventDispatcher.startElement("value", -1);

         len = this.value.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("value", -1);
      }
      else throw new Asn1MissingRequiredException ("value");

      // encode type

      if (this.type != null) {
         buffer.getContext().eventDispatcher.startElement("type", -1);

         len = this.type.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("type", -1);
      }
      else throw new Asn1MissingRequiredException ("type");

      if (explicit) {
         _aal += buffer.encodeTagAndLength (Asn1Tag.SEQUENCE, _aal);
      }

      return (_aal);
   }

   public void print (java.io.PrintWriter _out, String _varName, int _level)
   {
      indent (_out, _level);
      _out.println (_varName + " {");
      if (type != null) type.print (_out, "type", _level+1);
      if (value != null) value.print (_out, "value", _level+1);
      indent (_out, _level);
      _out.println ("}");
   }

}
