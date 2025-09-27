package com.rcf.ordersservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseValid {
    private int status; // 200, 400, 404, etc.
    private List<ErrorResponseValidItem> message; // error message
    private String time; // timestamp
}
