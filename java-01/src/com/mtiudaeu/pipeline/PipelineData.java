package com.mtiudaeu.pipeline;

public class PipelineData<T> {
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