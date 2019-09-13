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

    public LambdaData<Output> traverse(int i) {
        LambdaData<Input1> ret1 = input.traverse(i+1);
        LambdaData<Output> output = func.run(ret1);
        System.out.println("Layer " + i + ", Input1 : " + ret1.data.getClass().getSimpleName() + ", Output : " + output.data.getClass().getSimpleName());
        return output;
    }
}