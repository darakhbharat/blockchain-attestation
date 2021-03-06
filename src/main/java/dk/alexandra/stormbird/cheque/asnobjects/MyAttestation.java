/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.4.2, Date: 22-Jul-2020.
 */
package dk.alexandra.stormbird.cheque.asnobjects;

import com.objsys.asn1j.runtime.*;
import dk.alexandra.stormbird.cheque.asnobjects.AlgorithmIdentifier;

public class MyAttestation extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_AttestationFrameworkRtkey._rtkey);
      Asn1Type._setLicLocation(_AttestationFrameworkRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "MyAttestation";
   }

   public SignedInfo signedInfo;
   public AlgorithmIdentifier signatureAlgorithm;
   public Asn1BitString signatureValue;

   public MyAttestation () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public MyAttestation (
      SignedInfo signedInfo_,
      AlgorithmIdentifier signatureAlgorithm_,
      Asn1BitString signatureValue_
   ) {
      super();
      signedInfo = signedInfo_;
      signatureAlgorithm = signatureAlgorithm_;
      signatureValue = signatureValue_;
   }

   public void init () {
      signedInfo = null;
      signatureAlgorithm = null;
      signatureValue = null;
   }

   public int getElementCount() { return 3; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return signedInfo;
         case 1: return signatureAlgorithm;
         case 2: return signatureValue;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "signedInfo";
         case 1: return "signatureAlgorithm";
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

      // decode signedInfo

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.CONS, 16, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("signedInfo", -1);

         this.signedInfo = new SignedInfo();
         this.signedInfo.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("signedInfo", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "signedInfo");

      // decode signatureAlgorithm

      if (_context.matchElemTag (Asn1Tag.UNIV, Asn1Tag.CONS, 16, elemLen, false)) {
         buffer.getContext().eventDispatcher.startElement("signatureAlgorithm", -1);

         this.signatureAlgorithm = new AlgorithmIdentifier();
         this.signatureAlgorithm.decode (buffer, true, elemLen.value);

         buffer.getContext().eventDispatcher.endElement("signatureAlgorithm", -1);
      }
      else throw new Asn1MissingRequiredException (buffer, "signatureAlgorithm");

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

      // encode signatureAlgorithm

      if (this.signatureAlgorithm != null) {
         buffer.getContext().eventDispatcher.startElement("signatureAlgorithm", -1);

         len = this.signatureAlgorithm.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("signatureAlgorithm", -1);
      }
      else throw new Asn1MissingRequiredException ("signatureAlgorithm");

      // encode signedInfo

      if (this.signedInfo != null) {
         buffer.getContext().eventDispatcher.startElement("signedInfo", -1);

         len = this.signedInfo.encode (buffer, true);
         _aal += len;

         buffer.getContext().eventDispatcher.endElement("signedInfo", -1);
      }
      else throw new Asn1MissingRequiredException ("signedInfo");

      if (explicit) {
         _aal += buffer.encodeTagAndLength (Asn1Tag.SEQUENCE, _aal);
      }

      return (_aal);
   }

   public void print (java.io.PrintWriter _out, String _varName, int _level)
   {
      indent (_out, _level);
      _out.println (_varName + " {");
      if (signedInfo != null) signedInfo.print (_out, "signedInfo", _level+1);
      if (signatureAlgorithm != null) signatureAlgorithm.print (_out, "signatureAlgorithm", _level+1);
      if (signatureValue != null) signatureValue.print (_out, "signatureValue", _level+1);
      indent (_out, _level);
      _out.println ("}");
   }

}
