package com.danny.book.error;

import lombok.Data;

@Data
public class ErrorResponse {


    private String timeStamp;
    private String details;
    private String title;
    private int status;
    private String error;

}