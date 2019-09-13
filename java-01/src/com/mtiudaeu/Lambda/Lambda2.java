package com.mtiudaeu.Lambda;

public interface Lambda2<Input1, Input2, Output1> {
    LambdaData<Output1> run(LambdaData<Input1> input1, LambdaData<Input2> input2);
}