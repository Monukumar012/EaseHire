package com.easehire.application.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JsonResponse {
    private boolean success;
    private Object error;
    private Object data;
}
