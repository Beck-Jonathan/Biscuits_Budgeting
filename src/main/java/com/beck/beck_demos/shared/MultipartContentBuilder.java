package com.beck.beck_demos.shared;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class MultipartContentBuilder {
  static public byte[] buildMultipartContent(
      String boundary,
      Map<String, String> fields,
      Map<String, byte[]> files
  ) throws IOException {

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    String lineSeparator = "\r\n";
    String boundaryPrefix = "--" + boundary;

    // Form fields
    for (Map.Entry<String, String> field : fields.entrySet()) {
      out.write((boundaryPrefix + lineSeparator).getBytes());
      out.write(("Content-Disposition: form-data; name=\"" + field.getKey() + "\"" + lineSeparator).getBytes());
      out.write(("Content-Type: text/plain; charset=UTF-8" + lineSeparator).getBytes());
      out.write((lineSeparator).getBytes());
      out.write((field.getValue() + lineSeparator).getBytes());
    }

    // File fields
    for (Map.Entry<String, byte[]> file : files.entrySet()) {
      out.write((boundaryPrefix + lineSeparator).getBytes());
      out.write(("Content-Disposition: form-data; name=\"" + file.getKey() + "\"; filename=\"" + file.getKey() + ".zip\"" + lineSeparator).getBytes());
      out.write(("Content-Type: application/zip" + lineSeparator).getBytes());
      out.write((lineSeparator).getBytes());
      out.write(file.getValue());
      out.write(lineSeparator.getBytes());
    }

    // Closing boundary
    out.write((boundaryPrefix + "--" + lineSeparator).getBytes());

    return out.toByteArray();
  }
}