package org.sonar.xquery.sensor;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.sonar.api.batch.fs.InputFile;
import org.xqdoc.XQueryLexer;
import org.xqdoc.XQueryParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class XQueryAntlrParser {

  public AntlrParseResult parse(InputFile inputFile) throws Exception {
    Charset charset = inputFile.charset();
    if (charset == null) {
      charset = StandardCharsets.UTF_8;
    }

    try (InputStream in = inputFile.inputStream();
         Reader reader = new InputStreamReader(in, charset)) {

      CharStream input = CharStreams.fromReader(reader);

      XQueryLexer lexer = new XQueryLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);

      XQueryParser parser = new XQueryParser(tokens);

      List<AntlrParseResult.SyntaxError> errors = new ArrayList<>();
      BaseErrorListener listener = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                                Object offendingSymbol,
                                int line,
                                int charPositionInLine,
                                String msg,
                                RecognitionException e) {
          errors.add(new AntlrParseResult.SyntaxError(line, charPositionInLine, msg));
        }
      };

      lexer.removeErrorListeners();
      parser.removeErrorListeners();
      lexer.addErrorListener(listener);
      parser.addErrorListener(listener);

      // entry rule from your grammar: `module`
      ParseTree tree = parser.module();

      tokens.fill();
      return new AntlrParseResult(tree, tokens.getTokens(), errors);
    }
  }
}