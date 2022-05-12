package com.example.assignment_mad2019.Exceptions;

import androidx.annotation.Nullable;

public class mapException extends Exception {
    public mapException(String errorMsg)
    {
        super(errorMsg);
    }

    @Nullable
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
