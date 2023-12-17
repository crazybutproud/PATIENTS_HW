

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Dump {
    static String file = "C:\\Users\\Анна\\PROJECTS\\Patients\\src\\dump";
    public static List<Patient> getDump() {
        try(Stream<String> strings = Files.lines(Paths.get(file))) { // считываем файл в строки
            return strings
                    .map(e -> e
                            .replaceAll(",", "") // убираем знаки
                            .replaceAll("'", "")
                            .replace("(", "")
                            .replace(")", ""))
                    .map(Patient::new)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

