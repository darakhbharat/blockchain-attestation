package com.alphawallet.attestation.ticket;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.alphawallet.attestation.core.AttestationCrypto;
import com.alphawallet.attestation.ticket.Ticket.TicketClass;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestTicket {
  private static final String MAIL = "test@test.ts";
  private static final BigInteger TICKET_ID = new BigInteger("546048445646851568430134455064804806");
  private static final TicketClass TICKET_CLASS = TicketClass.REGULAR;
  private static final int CONFERENCE_ID = 6;
  private static final BigInteger SECRET = new BigInteger("48646");

  private static AsymmetricCipherKeyPair senderKeys;
  private static AsymmetricCipherKeyPair otherKeys;
  private static SecureRandom rand;

  @BeforeAll
  public static void setupKeys() throws Exception {
    rand = SecureRandom.getInstance("SHA1PRNG");
    rand.setSeed("seed".getBytes());
    AttestationCrypto crypto = new AttestationCrypto(rand);
    senderKeys = crypto.constructECKeys();
    otherKeys = crypto.constructECKeys();
  }

  @Test
  public void testFullDecoding() throws Exception {
    Ticket ticket = new Ticket(MAIL, TICKET_ID, TICKET_CLASS, CONFERENCE_ID, senderKeys, SECRET);
    byte[] encoded = ticket.getDerEncoding();
    Ticket newTicket = (new TicketDecoder(senderKeys.getPublic())).decode(encoded);
    assertTrue(ticket.verify());
    assertArrayEquals(encoded, newTicket.getDerEncoding());

    Ticket otherConstructor = new Ticket(newTicket.getTicketId(), newTicket.getTicketClass(), newTicket.getConferenceId(),
        newTicket.getRiddle(), newTicket.getSignature(), newTicket.getPublicKey());
    assertEquals(ticket.getTicketId(), otherConstructor.getTicketId());
    assertEquals(ticket.getTicketClass(), otherConstructor.getTicketClass());
    assertEquals(ticket.getConferenceId(), otherConstructor.getConferenceId());
    assertEquals(ticket.getAlgorithm(), otherConstructor.getAlgorithm());
    assertArrayEquals(ticket.getRiddle(), otherConstructor.getRiddle());
    assertArrayEquals(ticket.getSignature(), otherConstructor.getSignature());
    SubjectPublicKeyInfo ticketSpki = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(ticket.getPublicKey());
    SubjectPublicKeyInfo otherSpki = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(otherConstructor.getPublicKey());
    assertArrayEquals(ticketSpki.getEncoded(), otherSpki.getEncoded());

    assertArrayEquals(encoded, otherConstructor.getDerEncoding());
  }


  @Test
  public void testIllegalKeys() throws Exception {
    Ticket ticket = new Ticket(MAIL, TICKET_ID, TICKET_CLASS, CONFERENCE_ID, senderKeys, SECRET);
    Field field = ticket.getClass().getDeclaredField("signature");
    field.setAccessible(true);
    // Change a bit in the signature
    ((byte[]) field.get(ticket))[20] ^= 1;
    assertFalse(ticket.verify());
    // Check we cannot make a new ticket with invalid signature
    try {
      Ticket newTicket = new Ticket(ticket.getTicketId(), ticket.getTicketClass(),
          ticket.getConferenceId(), ticket.getRiddle(), ticket.getSignature(),
          senderKeys.getPublic());
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    }
  }

  @Test
  public void testWrongKey() throws Exception {
    Ticket ticket = new Ticket(MAIL, TICKET_ID, TICKET_CLASS, CONFERENCE_ID, senderKeys, SECRET);
    byte[] encoding = ticket.getDerEncoding();
    try {
      Ticket otherTicket = (new TicketDecoder(otherKeys.getPublic())).decode(encoding);
      fail();
    } catch (IllegalArgumentException e) {
      // Expected
    }
  }
}
