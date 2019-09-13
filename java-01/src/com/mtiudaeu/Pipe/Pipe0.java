package com.mtiudaeu.Pipe;

import com.mtiudaeu.Lambda.Lambda0;
import com.mtiudaeu.Lambda.LambdaData;

public class Pipe0<Output> implements Pipe<Output> {
    private Lambda0<Output> func;

    public Pipe0(Lambda0<Output> func){
        this.func = func;
    }

    public LambdaData<Output> run() {
        return func.run();
    }

    public LambdaData<Output> traverse(int i) {
        LambdaData<Output> output = func.run();
        System.out.println("Layer " + i + ", Output : " + output.data.getClass().getSimpleName());
        return output;
    }
}