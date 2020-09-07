/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.4.2, Date: 22-Jul-2020.
 */
package dk.alexandra.stormbird.cheque.asnobjects;

import com.objsys.asn1j.runtime.*;

public class SignedCheque extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_SignedChequeRtkey._rtkey);
      Asn1Type._setLicLocation(_SignedChequeRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SignedCheque";
   }

   public Cheque cheque;
   public Asn1BitString publicKey;
   public Asn1BitString signatureValue;

   public SignedCheque () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public SignedCheque (
      Cheque cheque_,
      Asn1BitString publicKey_,
      Asn1BitString signatureValue_
   ) {
      super();
      cheque = cheque_;
      publicKey = publicKey_;
      signatureValue = signatureValue_;
   }

   public void init () {
      cheque = null;
      publicKey = null;
      signatureValue = null;
   }

   public int getElementCount() { return 3; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return cheque;
         case 1: return publicKey;
         case 2: return signatureValue;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "cheque";
         case 1: return "publicKey";
         case 2: return "signatureValue";
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

      // decode cheque

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.CONS, 16, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("cheque", -1);

         this.cheque = new Cheque();
         this.cheque.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("cheque", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "cheque");

      // decode publicKey

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.PRIM, 3, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("publicKey", -1);

         this.publicKey = new Asn1BitString();
         this.publicKey.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("publicKey", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "publicKey");

      // decode signatureValue

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.PRIM, 3, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("signatureValue", -1);

         this.signatureValue = new Asn1BitString();
         this.signatureValue.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("signatureValue", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "signatureValue");

      if (!_context.expired()) {
         Asn1Tag _tag = buffer.peekTag ();
         if (_tag.equals (Asn1Tag.UNIV, Asn1Tag.CONS, 16) ||
             _tag.equals (Asn1Tag.UNIV, Asn1Tag.PRIM, 3))  {
            throw new Asn1UnexpectedElementException();
         }

      }
   }

   public int encode (Asn1BerEncodeBuffer buffer, boolean explicit)
      throws Asn1Exception
   {
      int _aal = 0, len;

      // encode signatureValue

      if (this.signatureValue != null) {
         buffer.getContext().eventDispatcher.startElement("signatureValue", -1);

         len = this.signatureValue.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("signatureValue", -1);
      }
      else throw new Asn1MissingRequiredException ("signatureValue");

      // encode publicKey

      if (this.publicKey != null) {
         buffer.getContext().eventDispatcher.startElement("publicKey", -1);

         len = this.publicKey.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("publicKey", -1);
      }
      else throw new Asn1MissingRequiredException ("publicKey");

      // encode cheque

      if (this.cheque != null) {
         buffer.getContext().eventDispatcher.startElement("cheque", -1);

         len = this.cheque.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("cheque", -1);
      }
      else throw new Asn1MissingRequiredException ("cheque");

      if (explicit) {
         _aal += buffer.encodeTagAndLength (Asn1Tag.SEQUENCE, _aal);
      }

      return (_aal);
   }

   public void print (java.io.PrintWriter _out, String _varName, int _level)
   {
      indent (_out, _level);
      _out.println (_varName + " {");
      if (cheque != null) cheque.print (_out, "cheque", _level+1);
      if (publicKey != null) publicKey.print (_out, "publicKey", _level+1);
      if (signatureValue != null) signatureValue.print (_out, "signatureValue", _level+1);
      indent (_out, _level);
      _out.println ("}");
   }

}
