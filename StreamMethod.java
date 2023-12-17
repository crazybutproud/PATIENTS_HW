

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMethod {

    // список расходов каждого пациента
    private static void forEachMethod(List<Patient> patients) {
        patients.stream()
                .forEach(p -> System.out.println("Список расходов: " + p.getExpenses() + " - " + p.getFio() + " |СУММАРНО: " + sumExp(p)));
    }
    // есть ли хоть один старше 100 лет
    private static void noneMatchMethod(List<Patient> patients) {
        boolean bool = patients.stream()
                .noneMatch(p -> (p.getBirthDate().getYear() < LocalDate.now().getYear() - 100));
        System.out.println("НЕТ НИ ОДНОГО пациента старше 100 лет - " + bool);
    }
    // преобразование стрима пациентов в map
    private static void collectMethod(List<Patient> patients) {
        Map<LocalDate, List<String>> pat;
        pat = patients.stream() // делаем map
                .collect(Collectors.groupingBy(
                        Patient::getBirthDate,
                        Collectors.mapping(
                                Patient::getFio,
                                Collectors.toList()
                        )
                ));

        for (Map.Entry<LocalDate, List<String>> pair : pat.entrySet()) // выводим каждую пару отдельно, чтоб было красиво
        {
            String key = String.valueOf(pair.getKey());
            String value = String.valueOf(pair.getValue());
            System.out.println(key + ":" + value);
        }
    }
    // первый пациент который родился в нужном году
    private static void findFirstMethod(List<Patient> patients) {
        Patient first = patients.stream()
                .filter(patient -> patient.getBirthDate()
                        .getYear() == 1980)
                .findFirst().get();
        System.out.println(first.getFio() + " - "+ first.getBirthDate() +" - " + first.getId());
    }
    // есть ли хоть один старше 100 лет
    private static void anyMatchMethod(List<Patient> patients) {
        boolean anyMatch = patients.stream()
                .anyMatch(p -> (p.getBirthDate().getYear() < LocalDate.now().getYear() - 100));
        System.out.println("Пациент старше 100 лет - " + anyMatch);
    }
    // проверка есть ли хоть один полностью здоровый
    private static void allMatchMethod(List<Patient> patients) {
        boolean allMatch = patients.stream()
                .anyMatch(patient -> sumExp(patient) == 0);
        System.out.println("Полностью здоровый человек - " + allMatch);
    }
    // минимум и максимум расходов пациента из потока(написать компаратор)
    private static void minMaxMethod(List<Patient> patients) {
        Patient minPat = patients
                .stream()
                .min(Comparator.comparingInt(StreamMethod::sumExp)).get();
        Patient maxPat = patients
                .stream()
                .max(Comparator.comparingInt(StreamMethod::sumExp)).get();
        System.out.println(minPat.getFio() + "  " + sumExp(minPat) + " - минимум");
        System.out.println(maxPat.getFio() + "  " + sumExp(maxPat) + " - максимум");
    }
    // сумма расходов
    private static int sumExp(Patient p) {
        return p.getExpenses()
                .stream()
                .reduce(Integer::sum)
                .get();
    }
    // общий метод, выводящий все остальные
    public static void start(List<Patient> patients) {
        System.out.println("MAP из пациентов: \n");
        collectMethod(patients);
        System.out.println(".............................................");
        System.out.println("Список расходов каждого пациента: \n");
        forEachMethod(patients);
        System.out.println(".............................................");
        System.out.println("Минимум и максимум расходов пациентов: \n");
        minMaxMethod(patients);
        System.out.println(".............................................");
        System.out.println("Первый пациент который родился в 1980 году: \n");
        findFirstMethod(patients);
        System.out.println(".............................................");
        System.out.println("Наличие полностью здорового пациента: \n");
        allMatchMethod(patients);
        System.out.println(".............................................");
        System.out.println("Наличие хоть одного пациента старше 100 лет: \n");
        noneMatchMethod(patients);
        System.out.println(".............................................");
        System.out.println("Наличие пациента старше 100 лет: \n");
        anyMatchMethod(patients);
    }
}
