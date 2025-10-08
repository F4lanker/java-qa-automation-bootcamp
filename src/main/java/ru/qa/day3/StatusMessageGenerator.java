package ru.qa.day3;

public class StatusMessageGenerator {
    // Реализуй двумя способами
    public static String getStatusMessageIfVersion(int statusCode) {
            if (statusCode == 200) return "OK";
            if (statusCode == 404) return "Not Found";
            if (statusCode == 400) return "Bad Request";
            if (statusCode == 500) return "Server Error";
            else return "Unknown";
    }
    public static String getStatusMessageSwitchCase(int statusCode) {
        return switch (statusCode) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 400 -> "Bad Request";
            case 500 -> "Server Error";
            default -> "Unknonwn";
        };
    }

}
