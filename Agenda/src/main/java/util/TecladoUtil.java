package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TecladoUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static String lerString(String mensagem) {
        return  inicializaTeclado(mensagem).nextLine();
    }

    public static  Integer lerInteiro(String mensagem) {
        return  inicializaTeclado(mensagem).nextInt();
    }

    private static Scanner inicializaTeclado(String mensagem) {
        System.out.println(mensagem);
        return new Scanner(System.in);
    }

    public static LocalDate lerData(String mensagem) {
        System.out.print(mensagem + " (dd/MM/yyyy): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(scanner.nextLine(), formatter);
    }
}
