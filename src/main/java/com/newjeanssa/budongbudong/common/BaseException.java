package com.newjeanssa.budongbudong.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseException extends RuntimeException{

    private BaseExceptionStatus status;

}
