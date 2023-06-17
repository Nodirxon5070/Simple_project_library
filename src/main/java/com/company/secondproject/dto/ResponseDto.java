package com.company.secondproject.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private String message;
    /*
     * 0  - success
     * -1 - not found
     * -2 - database error
     * -3 - validation error
     * */
    private int code;
    private T data;
}
