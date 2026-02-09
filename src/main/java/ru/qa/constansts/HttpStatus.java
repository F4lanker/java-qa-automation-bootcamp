package ru.qa.constansts;

import lombok.Getter;


@Getter

/**
 * HTTP status codes with corresponding messages.
 * Provides type-safe access to standard HTTP responses.
 */
public enum HttpStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    SERVER_ERROR(500, "Server Error"),
    UNKNOWN(-1, "Unknown");

    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Returns the HttpStatus corresponding to the given status code.
     * @param statusCode HTTP status code (e.g., 200, 404)
     * @return matching HttpStatus, or UNKNOWN if not found
     */
    public static HttpStatus fromCode(int statusCode){
        for (HttpStatus status : values()){
            if (status.code == statusCode){
                return status;
            }
        }
        return UNKNOWN;
    }
}
