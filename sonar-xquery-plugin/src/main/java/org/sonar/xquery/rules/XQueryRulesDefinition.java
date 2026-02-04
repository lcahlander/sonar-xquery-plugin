package org.sonar.xquery.rules;

import org.sonar.api.server.rule.RulesDefinition;

public class XQueryRulesDefinition implements RulesDefinition {
  public static final String REPO_KEY = "xquery-repo";

  @Override
  public void define(Context context) {
    NewRepository repo = context.createRepository(REPO_KEY, "xquery").setName("XQuery Rules");
    repo.createRule(ForbiddenUpdateRule.KEY)
      .setName("Avoid 'update' expression")
      .setHtmlDescription("This rule flags the use of 'update' which is often problematic in immutable XQuery contexts.")
      .setTags("bug", "convention");
    repo.done();
  }
}