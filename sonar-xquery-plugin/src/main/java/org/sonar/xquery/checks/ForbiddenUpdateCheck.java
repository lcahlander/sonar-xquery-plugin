package org.sonar.xquery.checks;

import org.antlr.v4.runtime.Token;
import org.sonar.xquery.rules.ForbiddenUpdateRule;
import org.sonar.xquery.sensor.XQueryCheckContext;
import org.sonar.xquery.sensor.XQuerySensor;
import org.xqdoc.XQueryLexer;

public final class ForbiddenUpdateCheck implements XQueryCheck {

  @Override
  public void scan(XQueryCheckContext ctx) {
    for (Token t : ctx.parseResult().tokens()) {
      if (t.getType() == XQueryLexer.KW_UPDATE) {
        int line = t.getLine();
        int col = t.getCharPositionInLine();
        XQuerySensor.reportIssue(
          ctx.sensorContext(),
          ctx.inputFile(),
          ForbiddenUpdateRule.KEY,
          line,
          col,
          "Avoid 'update' expressions; they can cause side effects and reduce portability."
        );
      }
    }
  }
}
