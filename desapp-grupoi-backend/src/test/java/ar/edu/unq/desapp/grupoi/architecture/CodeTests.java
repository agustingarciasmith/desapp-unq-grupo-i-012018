package ar.edu.unq.desapp.grupoi.architecture;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CodeTests {

  @Test
  public void sytemOutNotAllowed() {
    assertThat(
        allJavaFiles(System.getProperty("user.dir"))
            .filter(file -> !file.getName().contains("CodeTests"))
            .flatMap(file -> getReader(file).lines())
            .filter(line -> line.contains("System.out"))
            .findFirst()
    ).isNotPresent();
  }

  private BufferedReader getReader(File file) {
    try {
      return new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private Stream<File> allJavaFiles(String path) {
    return Arrays.stream(Objects.requireNonNull(new File(path).listFiles())).flatMap(file1 -> {
      if (file1.isDirectory()) {
        return this.allJavaFiles(file1.getPath());
      } else {
        return Stream.of(file1);
      }
    });
  }
}
