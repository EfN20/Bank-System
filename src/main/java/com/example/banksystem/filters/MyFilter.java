package com.example.banksystem.filters;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class MyFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord logRecord) {
        if(logRecord.getSourceClassName().startsWith("com.example.banksystem.repositories.ExchangeRateRepository")) {
            return false;
        }
        return true;
    }
}
