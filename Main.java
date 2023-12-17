import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Patient> patients = Dump.getDump(); // считываем файл в лист
        StreamMethod.start(patients); // основной метод, запускающий остальные и выводящий в консоль
    }
}