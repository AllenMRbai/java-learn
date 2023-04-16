package com.allen.bai.maven;

import org.junit.Test;
import com.allen.bai.maven.Calculator;

// 静态导入的效果是将Assert类中的静态资源导入当前类
// 这样一来，在当前类中就可以直接使用Asssert类中的静态资源，不需要写类名
import static org.junit.Assert.*;

public class CalculatorTest {
  @Test
  public void testSum() {
    Calculator c = new Calculator();

    int result = c.sum(1, 2);

    assertEquals(3, result);
  }
}
