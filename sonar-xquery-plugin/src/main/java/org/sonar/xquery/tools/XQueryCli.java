package org.sonar.xquery.tools;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * Tiny CLI scanner to demonstrate a simple rule:
 * - flags files containing the token "update" (example of a simple rule).
 *
 * Usage:
 *   java -jar sonar-xquery-plugin-0.1.0-SNAPSHOT.jar --scan path/to/xq/files
 *
 * This CLI is intentionally simple so you can run it locally while developing rules.
 */
public class XQueryCli {

  public static void main(String[] args) throws IOException {
    if (args.length < 2 || !"--scan".equals(args[0])) {
      System.out.println("Usage: --scan <path>");
      System.exit(1);
    }
    Path start = Paths.get(args[1]);
    try (Stream<Path> files = Files.walk(start)) {
      files.filter(p -> Files.isRegularFile(p))
           .filter(p -> {
             String s = p.getFileName().toString().toLowerCase();
             return s.endsWith(".xq") || s.endsWith(".xql") || s.endsWith(".xqm") || s.endsWith(".xqy") || s.endsWith(".xql");
           })
           .forEach(p -> {
             try {
               String content = new String(Files.readAllBytes(p));
               int idx = content.indexOf("update");
               if (idx >= 0) {
                 System.out.println("WARNING: 'update' keyword found in " + p + " (offset " + idx + ")");
               }
             } catch (IOException e) {
               System.err.println("Failed to read " + p + ": " + e.getMessage());
             }
           });
    }
  }
}