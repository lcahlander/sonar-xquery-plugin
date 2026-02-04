# sonar-xquery-plugin

Maven scaffold for a SonarQube / SonarLint XQuery language module using ANTLR4 grammar files.

How to use
1. Put the canonical grammar files under:
   sonar-xquery-plugin/src/main/antlr4/org/xqdoc

2. Build:
   mvn -pl sonar-xquery-plugin -am clean package

   This runs the ANTLR4 plugin to generate parser/lexer sources from the grammar files.

3. Local quick-scan (CLI):
   java -jar sonar-xquery-plugin/target/sonar-xquery-plugin-0.1.0-SNAPSHOT.jar --scan path/to/xquery/files

   The CLI flags basic "forbidden update" matches to demonstrate a rule.

4. Developing Sonar rules:
   - The plugin contains a RulesDefinition skeleton (see `XQueryRulesDefinition`).
   - Add Sensors / Checks later by implementing SonarQube Sensor or Issuable checks that use generated ANTLR parser.

Notes
- This scaffold is intentionally minimal and intended as a starting point. I can:
  - push it to GitHub (lcahlander) for you, or
  - extend sensors/rules that use the generated ANTLR parser once you've added the grammar files.