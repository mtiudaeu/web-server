package com.mtiudaeu.pipeline;

public class DefaultPipeline<A,B> extends Pipeline2<A,B> {
    private Pipeline1<A> inputFunc;
    private Pipeline2<A,B> func;

    public DefaultPipeline(Pipeline1<A> inputFunc, Pipeline2<A,B> func) {
        this.inputFunc = inputFunc;
        this.func = func;
    }

    public PipelineData<B> run(A input) {
        return func.run(input);
    }

    public PipelineData<B> runDefault () throws Exception {
        PipelineData<A> input = inputFunc.run();
        if(input.error) {
            System.out.println(input.errorMsg);
            Exception e = new Exception(input.errorMsg);
            e.printStackTrace();
            throw e;
        }

        PipelineData<B> output = run(input.data);
        if(output.error) {
            System.out.println(output.errorMsg);
            Exception e = new Exception(output.errorMsg);
            e.printStackTrace();
            throw e;
        }
        return output;
    }
}
