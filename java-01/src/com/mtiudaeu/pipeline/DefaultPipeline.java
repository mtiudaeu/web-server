package com.mtiudaeu.pipeline;

public class DefaultPipeline<A,B> extends Pipeline2<A,B> {
    private Pipeline2<A,B> func;

    public DefaultPipeline(Pipeline2<A,B> func) {
        this.func = func;
    }

    public PipelineData<B> run(A input) {
        return func.run(input);
    }

    public PipelineData<B> runDefault (A input) throws Exception {
        PipelineData<B> output = run(input);
        if(output.error) {
            System.out.println(output.errorMsg); //mdtmp
            Exception e = new Exception(output.errorMsg);
            e.printStackTrace();
            throw e;
        }
        return output;
    }
}
