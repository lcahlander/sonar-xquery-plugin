package org.sonar.xquery;

import org.sonar.api.Plugin;
import java.util.Arrays;

public class XQueryPlugin implements Plugin {
  @Override
  public void define(Context context) {
    context.addExtensions(Arrays.asList(
      XQueryLanguage.class,
      org.sonar.xquery.rules.XQueryRulesDefinition.class
      // add Sensors / Check classes here later
    ));
  }
}