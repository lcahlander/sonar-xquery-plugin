package org.sonar.xquery;

import org.sonar.api.resources.Language;

public final class XQueryLanguage implements Language {
  public static final String KEY = "xquery";
  public static final String NAME = "XQuery";
  public static final String[] FILE_SUFFIXES = {"xq", "xql", "xqm", "xqy"};

  @Override
  public String getKey() {
    return KEY;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public String[] getFileSuffixes() {
    return FILE_SUFFIXES.clone();
  }
}