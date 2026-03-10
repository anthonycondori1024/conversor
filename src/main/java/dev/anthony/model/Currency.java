package dev.anthony.model;

public record Currency(String code, String name) {
    @Override
    public String toString() {
        return String.format("%s (%s)", code, name);
    }
}
