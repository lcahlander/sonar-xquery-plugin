package org.sonar.xquery.sensor;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;
import org.sonar.xquery.XQueryLanguage;
import org.sonar.xquery.checks.ForbiddenUpdateCheck;
import org.sonar.xquery.checks.XQueryCheck;
import org.sonar.xquery.rules.XQueryRulesDefinition;

import java.util.List;

public class XQuerySensor implements Sensor {

  private final List<XQueryCheck> checks = List.of(
    new ForbiddenUpdateCheck()
  );

  @Override
  public void describe(SensorDescriptor descriptor) {
    descriptor.name("XQuery ANTLR Sensor").onlyOnLanguage(XQueryLanguage.KEY);
  }

  @Override
  public void execute(SensorContext context) {
    Iterable<InputFile> inputFiles = context.fileSystem().inputFiles(
      context.fileSystem().predicates().hasLanguage(XQueryLanguage.KEY)
    );

    XQueryAntlrParser parser = new XQueryAntlrParser();

    for (InputFile inputFile : inputFiles) {
      AntlrParseResult result;
      try {
        result = parser.parse(inputFile);
      } catch (Exception e) {
        // If parsing infrastructure fails, don't kill the analysis; just skip the file.
        continue;
      }

      for (XQueryCheck check : checks) {
        check.scan(new XQueryCheckContext(context, inputFile, result));
      }

      // Optional: if you want a "syntax error" rule later, you can report result.syntaxErrors here.
    }
  }

  public static void reportIssue(SensorContext context,
                          InputFile file,
                          String ruleKey,
                          int line,
                          int column,
                          String message) {
    RuleKey rk = RuleKey.of(XQueryRulesDefinition.REPO_KEY, ruleKey);
    NewIssue issue = context.newIssue().forRule(rk);

    NewIssueLocation location = issue.newLocation()
      .on(file)
      .at(file.selectLine(line)) // simplest stable location
      .message(message + (column >= 0 ? " (col " + (column + 1) + ")" : ""));

    issue.at(location);
    issue.save();
  }
}
