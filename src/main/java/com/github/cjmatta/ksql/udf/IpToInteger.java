package com.github.cjmatta.ksql.udf;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

@UdfDescription(name = "IpToInteger", description = "Convert a dot notation IP address to integer")
public class IpToInteger {
  private static final Pattern IP_VALIDATION_PATTERN = Pattern.compile(
    "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

  @Udf(description = "Convert dot notation IP address to an integer")
  public int ipToInteger(String ipaddress) throws UnknownHostException {
    if (!IP_VALIDATION_PATTERN.matcher(ipaddress).matches()) {
      System.out.println("Error, this is not an IP address");
      return -1;
    }

    byte[] inetAddress = InetAddress.getByName(ipaddress).getAddress();
    return pack(inetAddress);

  }

  private int pack(byte[] bytes) {
    int val = 0;
    for (int i = 0; i < bytes.length; i++) {
      val <<= 8;
      val |= bytes[i] & 0xff;
    }
    return val;
  }
}
