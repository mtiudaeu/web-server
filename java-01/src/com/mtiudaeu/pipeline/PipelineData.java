package com.mtiudaeu.pipeline;

public class PipelineData<T> {
    public PipelineData(){}

    public PipelineData(T data){
        this.data = data;
    }

    public String errorMsg;
    public boolean error = false;

    public T data;

    public void setError(String msg){
        errorMsg = msg;
        error = true;
    }
    public void clearError(){
        error = false;
        errorMsg = "";
    }
}