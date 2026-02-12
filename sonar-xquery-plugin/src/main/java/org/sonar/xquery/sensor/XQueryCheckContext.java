package org.sonar.xquery.sensor;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;

public record XQueryCheckContext(
  SensorContext sensorContext,
  InputFile inputFile,
  AntlrParseResult parseResult
) {}
