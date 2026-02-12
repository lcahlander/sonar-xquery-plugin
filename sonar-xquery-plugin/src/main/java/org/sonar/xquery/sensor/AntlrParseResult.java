package org.sonar.xquery.sensor;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

import static java.util.Objects.requireNonNull;

public record AntlrParseResult(
        ParseTree tree,
        List<? extends Token> tokens,
        List<SyntaxError> syntaxErrors
) {

  public AntlrParseResult {
    tree = requireNonNull(tree, "tree");
    tokens = List.copyOf(requireNonNull(tokens, "tokens"));
    syntaxErrors = List.copyOf(requireNonNull(syntaxErrors, "syntaxErrors"));
  }

  public record SyntaxError(int line, int charPositionInLine, String message) {
    public SyntaxError {
      requireNonNull(message, "message");
    }
  }
}