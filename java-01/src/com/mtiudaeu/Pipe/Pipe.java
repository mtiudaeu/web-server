package com.mtiudaeu.Pipe;

import com.mtiudaeu.Lambda.LambdaData;

public interface Pipe<Output> {
    LambdaData<Output> run();
}
