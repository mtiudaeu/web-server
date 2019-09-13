package com.mtiudaeu.Lambda;

public class LambdaData<T> {
    public LambdaData(){}

    public LambdaData(T data){
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