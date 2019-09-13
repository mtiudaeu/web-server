package com.mtiudaeu.Pipe;

import com.mtiudaeu.Lambda.Lambda1;
import com.mtiudaeu.Lambda.LambdaData;

public class Pipe1<Input1, Output> implements Pipe<Output> {
    private Pipe<Input1> input;
    private Lambda1<Input1, Output> func;

    public Pipe1(Pipe<Input1> input, Lambda1<Input1,Output> func){
        this.input = input;
        this.func = func;
    }

    public LambdaData<Output> run() {
        return func.run(input.run());
    }
}