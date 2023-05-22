package com.backend.unskoolify.util;

import org.slf4j.LoggerFactory;

public class Logger {
    
    public static org.slf4j.Logger log(Class<?> clazz){
        return LoggerFactory.getLogger(clazz);
    }
}
