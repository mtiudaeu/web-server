package com.mtiudaeu.Pipe;

import com.mtiudaeu.Lambda.Lambda2;
import com.mtiudaeu.Lambda.LambdaData;

public class Pipe2<Input1, Input2, Output> implements Pipe<Output> {
    private Pipe<Input1> input1;
    private Pipe<Input2> input2;
    private Lambda2<Input1, Input2, Output> func;

    public Pipe2(Pipe<Input1> input1, Pipe<Input2> input2, Lambda2<Input1, Input2, Output> func){
        this.input1 = input1;
        this.input2 = input2;
        this.func = func;
    }

    public LambdaData<Output> run() {
        return func.run(input1.run(), input2.run());
    }
}