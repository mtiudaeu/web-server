package com.mtiudaeu.pipeline;

abstract public class PipelineDataMerge<T,A,B> extends Pipeline2<A,B> {
    protected T data;
    public PipelineDataMerge(T data){
        this.data = data;
    }
}