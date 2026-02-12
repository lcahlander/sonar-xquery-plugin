package org.sonar.xquery.checks;

import org.sonar.xquery.sensor.XQueryCheckContext;

public interface XQueryCheck {
  void scan(XQueryCheckContext ctx);
}
