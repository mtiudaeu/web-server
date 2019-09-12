package com.mtiudaeu;

import javafx.util.Pair;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static public class PipelineData<T> {
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


    abstract static public class Pipeline2<A,B> {
        abstract public PipelineData<B> run(A data);
    }
    static public class Pipeline3<A,B,C> extends Pipeline2<A,C> {
        private Pipeline2<A,B> func1;
        private Pipeline2<B,C> func2;

        Pipeline3(Pipeline2<A,B> func1, Pipeline2<B,C> func2) {
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

    static public class DefaultPipeline<A,B> extends Pipeline2<A,B> {
        private Pipeline2<A,B> func;

        DefaultPipeline(Pipeline2<A,B> func) {
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




    static public class DataToWrite {
        public String path;
        public String valueToWrite;
    }
    static public class WriteToFile extends Pipeline2<DataToWrite,Object> {
        public PipelineData<Object> run(DataToWrite data) {
            PipelineData ret = new PipelineData();
            try {
                Files.write(Paths.get(data.path), data.valueToWrite.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                ret.setError("Fail to write file"); //mdtmp line and file....
            }
            return ret;
        }
    }
    static public class GetPathAndGenerateValue extends Pipeline2<String,DataToWrite> {
        public PipelineData<DataToWrite> run(String input) {
            PipelineData ret = new PipelineData();

            DataToWrite data = new DataToWrite();
            data.path = input;
            data.valueToWrite = "mdtmp NEW";

            ret.data = data;

            return ret;
        }
    }

    public static void main(String[] args) {
        Pipeline3<String, DataToWrite, Object> pipelinedTest = new Pipeline3(new GetPathAndGenerateValue(), new WriteToFile());
        DefaultPipeline<String,Object> pipeline = new DefaultPipeline(pipelinedTest);

        try {
            PipelineData<Object> output = pipeline.runDefault("out/tmp.txt");
        } catch(Exception e) {
            //mdtmp ...
        }

    }
}
