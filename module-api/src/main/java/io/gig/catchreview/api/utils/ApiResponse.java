package io.gig.catchreview.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author : JAKE
 * @date : 2022/04/03
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private HttpStatus status;

    private Object data;

    private String message;

    public ApiResponse(HttpStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static ApiResponse OK(Object data) {
        return new ApiResponse(HttpStatus.OK, data);
    }

    public static ApiResponse OK(Object data, String message) {
        return new ApiResponse(HttpStatus.OK, data, message);
    }


    public static ApiResponse ERROR(HttpStatus status, Object data, String message) {
        return new ApiResponse(status, data, message);
    }
    public static ApiResponse ERROR(HttpStatus status, Object data) {
        return new ApiResponse(status, data);
    }
}
