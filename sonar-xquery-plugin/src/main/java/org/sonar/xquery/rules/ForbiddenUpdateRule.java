package org.sonar.xquery.rules;

/**
 * Rule metadata holder.
 * Actual detection can be implemented as:
 *  - a Sensor that runs the ANTLR parser and reports issues; or
 *  - checks based on Issuable APIs.
 *
 * This file just holds the rule key for registration.
 */
public final class ForbiddenUpdateRule {
  public static final String KEY = "forbidden-update";
  public ForbiddenUpdateRule() {}
}