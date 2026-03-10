package dev.anthony.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Conversion(
        Currency source,
        Currency target,
        double amount,
        double result,
        LocalDateTime timestamp
) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public String toString() {
        return String.format("[%s] %s %.2f -> %s %.2f",
                timestamp.format(FORMATTER), source.code(), amount, target.code(), result);
    }
}
