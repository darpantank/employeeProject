package com.example.demo.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
	private T data;
    private int totalPages;
    
}
