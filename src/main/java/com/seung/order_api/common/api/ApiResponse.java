package com.seung.order_api.common.api;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@Getter
public class ApiResponse<T> {

    private final Boolean result;
    private final Error error;
    private final T message;

    public ApiResponse(Boolean result, String error, String errorMessage, T message) {
        this.result = result;
        this.error = Error.builder()
                .errorCode(error)
                .errorMessage(errorMessage)
                .build();
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<>(true, "", "", result);
    }

    public static <T> ResponseEntity<ApiResponse<T>> ResponseException(String code,
                                                                       String errorMessage) {

        if (Objects.equals(code,"DUPLICATE_COUPON_CODE")){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(false, code, errorMessage, null));
        }
        return ResponseEntity.ok(new ApiResponse<>(false, code, errorMessage, null));
    }
    public static <T> ResponseEntity<ApiResponse<T>> ValidException(String code,
                                                                    String errorMessage) {
        return ResponseEntity.status(400).body(new ApiResponse<>(false, code, errorMessage, null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> ServerException(String code,
                                                                     String errorMessage) {
        return ResponseEntity.status(500)
                .body(new ApiResponse<>(false, code, errorMessage, null));
    }


    @Getter
    public static class Error {

        private final String errorCode;
        private final String errorMessage;

        @Builder
        public Error(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }
}