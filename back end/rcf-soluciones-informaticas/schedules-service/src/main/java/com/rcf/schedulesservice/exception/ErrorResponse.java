package com.rcf.schedulesservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
	private int status; // 200, 400, 404, etc.
    private String message; // error message
    private String time; // timestamp
}
