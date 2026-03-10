package dev.anthony.ui;

import dev.anthony.service.ConverterService;

import java.util.Scanner;

public class ConsoleUI {
    private final ConverterService service = new ConverterService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        service.init();
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> handleConversion();
                case "2" -> System.out.println(service.getHistory());
                case "3" -> service.getAvailableCurrencies().forEach((k, v) ->
                        System.out.println(k + " - " + v));
                case "4" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }


    }

    private void printMenu() {
        System.out.println("\n----- CURRENCY CONVERTER -----");
        System.out.println("1) Convert\n2) History\n3) List Codes\n4) Exit");
        System.out.print("Selection: ");
    }

    private void handleConversion() {
        try {
            System.out.print("From (e.g. USD): ");
            String from = scanner.nextLine().toUpperCase();
            System.out.print("To (e.g. EUR): ");
            String to = scanner.nextLine().toUpperCase();
            System.out.print("Amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            var result = service.performConversion(from, to, amount);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Conversion failed: " + e.getMessage());
        }
    }
}
