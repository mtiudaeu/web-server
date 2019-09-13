package com.mtiudaeu;

import javafx.util.Pair;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    static class MergeData<A,B> {
        public MergeData(A data1, B data2) {
            this.data1 = data1;
            this.data2 = data2;
        }
        A data1;
        B data2;
    }
    abstract static public class PipelineMerge3<A,B,Z> extends Pipeline2<MergeData<A,B>, Z> {
        public PipelineData<Z> run(MergeData<A,B> data) {
            return run(data.data1, data.data2);
        }
        abstract public PipelineData<Z> run(A input1, B input2);
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

    static public class AutoKeyArray {
        static public class Keys {
            public Keys(String inputKeyCodes,
                        String keyWaitKeyCodes,
                        String outputKeyCodes){
                this.inputKeyCodes = inputKeyCodes;
                this.keyWaitKeyCodes = keyWaitKeyCodes;
                this.outputKeyCodes = outputKeyCodes;
            }
            String inputKeyCodes;
            String keyWaitKeyCodes;
            String outputKeyCodes;
        }
        public List<Keys> keys = new ArrayList();
    }
    static public class AutoKeyWrap extends Pipeline2<AutoKeyArray,String> {
        public PipelineData<String> run(AutoKeyArray input) {
            String value = new String();

            for(AutoKeyArray.Keys it : input.keys) {
                // Input line
                value += "~";
                value += it.inputKeyCodes;
                value += "::\n";

                // Keywait line
                value += "KeyWait ";
                value += it.keyWaitKeyCodes;
                value += "\n";

                // mdtmp comment
                value += "if(State = 2)\n";
                value += "{\n";

                value += "\tIfWinActive ahk_id %wowid1%\n";
                value += "\t{\n";
                value += "\tControlSend,, "; value += it.outputKeyCodes; value += ", ahk_id %wowid2%\n";
                value += "\t}\n";

                value += "if(State = 2)\n";
                value += "{\n";
                value += "\tIfWinActive ahk_id %wowid2%\n";
                value += "\t{\n";
                value += "\tControlSend,, "; value += it.outputKeyCodes; value += ", ahk_id %wowid1%\n";
                value += "\t}\n";

                value += "}\n";
                value += "Return\n";

            }

            PipelineData ret = new PipelineData();
            ret.data = value;
            return ret;
        }
    }

    //mdtmp
    static public class AutoKeyMerge extends PipelineMerge3<String,String,DataToWrite> {
        public PipelineData<DataToWrite> run(String input1, String input2) {
            PipelineData ret = new PipelineData();
            DataToWrite data = new DataToWrite();
            data.path = input1;
            data.valueToWrite = input2;
            ret.data = data;
            return ret;
        }
    }



    public static void main(String[] args) {
        PipelineMerge3 pipelineMerge3 = new AutoKeyMerge();
        DefaultPipeline<MergeData<String,String>,DataToWrite> pipeline = new DefaultPipeline(pipelineMerge3);

        try {
            AutoKeyArray keyInputs = new AutoKeyArray();
            keyInputs.keys.add(new AutoKeyArray.Keys("1", "1", "1"));
            MergeData<String,String> input = new MergeData("out/tmp.txt", keyInputs);
            PipelineData<DataToWrite> data = pipeline.runDefault(input);
            //mdtmp connect to write file after
            System.out.println(data.data.path);
            System.out.println(data.data.valueToWrite);
        } catch(Exception e) {
            //mdtmp ...
            System.out.println(":(:( BOOOM ...");
            e.printStackTrace();
        }

        /*
        Pipeline3<String, DataToWrite, Object> pipelinedTest = new Pipeline3(new GetPathAndGenerateValue(), new WriteToFile());
        DefaultPipeline<String,Object> pipeline = new DefaultPipeline(pipelinedTest);

        try {
            PipelineData<Object> output = pipeline.runDefault("out/tmp.txt");
        } catch(Exception e) {
            //mdtmp ...
        }
        */

    }
}
