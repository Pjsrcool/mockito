package org.mockito;

import javax.annotation.Nullable;

public class NullAwayUtil {
  public static <T> T castToNonNull(@Nullable T x) {
    if (x == null) {
      System.out.println("Did not expect null here");
    }
    return x;
  }
}
