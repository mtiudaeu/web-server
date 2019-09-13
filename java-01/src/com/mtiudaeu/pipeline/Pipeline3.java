package com.mtiudaeu.pipeline;

public class Pipeline3<A,B,C> extends Pipeline2<A,C> {
    private Pipeline2<A,B> func1;
    private Pipeline2<B,C> func2;

    public Pipeline3(Pipeline2<A,B> func1, Pipeline2<B,C> func2) {
        this.func1 = func1;
        this.func2 = func2;
    }

    public PipelineData<C> run(A input1) {
        PipelineData<B> input2 = func1.run(input1);
        if(input2.error) {
            PipelineData<C> error = new PipelineData<C>();
            error.setError(input2.errorMsg);
            return error;
        }
        return func2.run(input2.data);
    }
}