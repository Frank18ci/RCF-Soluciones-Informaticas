package com.rcf.servicesservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
	private int status; // 200, 400, 404, etc.
    private String message; // error message
    private String time; // timestamp
}
