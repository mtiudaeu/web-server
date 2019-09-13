package com.mtiudaeu.pipeline;

abstract public class Pipeline2<A,B> {
    abstract public PipelineData<B> run(A data);
}