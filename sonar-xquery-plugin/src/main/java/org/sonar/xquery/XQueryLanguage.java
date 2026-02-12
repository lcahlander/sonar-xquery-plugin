package org.sonar.xquery;

import org.sonar.api.resources.Language; // older API compatibility placeholder

// Sonar modern API uses org.sonar.api.server.language.LanguageProperties; we keep a tiny class to host the language key.

public final class XQueryLanguage {
  public static final String KEY = "xquery";
  public static final String NAME = "XQuery";
  public static final String[] FILE_SUFFIXES = {"xq", "xql", "xqm", "xqy"};

  private XQueryLanguage() {}
}