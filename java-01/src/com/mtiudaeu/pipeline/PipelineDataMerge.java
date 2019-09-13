package com.mtiudaeu.pipeline;

abstract public class PipelineDataMerge<T,A,B1> extends Pipeline2<A,B1> {
    protected T data;
    public PipelineDataMerge(T data){
        this.data = data;
    }
}