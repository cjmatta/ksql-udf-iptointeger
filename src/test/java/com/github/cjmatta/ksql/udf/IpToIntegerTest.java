package com.github.cjmatta.ksql.udf;

import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class IpToIntegerTest {

  @Test
  public void ipToInteger() throws UnknownHostException {
    IpToInteger ipToI = new IpToInteger();
    int localInt = ipToI.ipToInteger("127.0.0.1");
    assertEquals(2130706433, localInt);
  }
}