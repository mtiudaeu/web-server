package com.mtiudaeu;

import com.mtiudaeu.pipeline.DefaultPipeline;
import com.mtiudaeu.pipeline.Pipeline2;
import com.mtiudaeu.pipeline.Pipeline3;
import com.mtiudaeu.pipeline.PipelineData;
import javafx.util.Pair;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {



    static public class DataToWrite {
        public String path;
        public String valueToWrite;
    }
    static public class AddPathToDataToWrite extends Pipeline2<String,DataToWrite> {
        private String path;
        public AddPathToDataToWrite(String path){
            this.path = path;
        }
        public PipelineData<DataToWrite> run(String valueToWrite) {
            PipelineData ret = new PipelineData();
            DataToWrite data = new DataToWrite();
            data.path = this.path;
            data.valueToWrite = valueToWrite;
            ret.data = data;
            return ret;
        }
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
                if(it.keyWaitKeyCodes!=null){
                    value += "KeyWait ";
                    value += it.keyWaitKeyCodes;
                    value += "\n";
                }

                // mdtmp comment
                value += "if(State = 2)\n";
                value += "{\n";

                value += "\tIfWinActive ahk_id %wowid1%\n";
                value += "\t{\n";
                value += "\t\tControlSend,, "; value += it.outputKeyCodes; value += ", ahk_id %wowid2%\n";
                value += "\t}\n";

                value += "\tIfWinActive ahk_id %wowid2%\n";
                value += "\t{\n";
                value += "\t\tControlSend,, "; value += it.outputKeyCodes; value += ", ahk_id %wowid1%\n";
                value += "\t}\n";

                value += "}\n";
                value += "Return\n\n";

            }

            PipelineData ret = new PipelineData();
            ret.data = value;
            return ret;
        }
    }




    public static void main(String[] args) {

        AddPathToDataToWrite path = new AddPathToDataToWrite("out/tmp.txt");

        AutoKeyWrap wrap = new AutoKeyWrap();
        Pipeline3<AutoKeyArray, String, DataToWrite> prepareToWrite = new Pipeline3<AutoKeyArray, String, DataToWrite>(wrap, path);
        Pipeline3<AutoKeyArray, DataToWrite, Object> finalPipeline = new Pipeline3<AutoKeyArray, DataToWrite, Object>(prepareToWrite, new WriteToFile());

        DefaultPipeline<AutoKeyArray, Object> pipeline = new DefaultPipeline<AutoKeyArray, Object>(finalPipeline);
        try {
            AutoKeyArray keyInputs = new AutoKeyArray();
            keyInputs.keys.add(new AutoKeyArray.Keys("0", "0", "0"));
            keyInputs.keys.add(new AutoKeyArray.Keys("1", "1", "z"));
            for(Integer i=2; i<10; i++) {
                keyInputs.keys.add(new AutoKeyArray.Keys(i.toString(), i.toString(), i.toString()));
            }
            for(Integer i=0; i<10; i++) {
                keyInputs.keys.add(new AutoKeyArray.Keys("+"+i.toString(), i.toString(), "{Shift down}"+i.toString()+"{Shift up}"));
            }
            for(Integer i=1; i<6; i++) {
                keyInputs.keys.add(new AutoKeyArray.Keys("F"+i.toString(), "F"+i.toString(), "{F"+i.toString()+"}"));
            }

            //mouvement key
            keyInputs.keys.add(new AutoKeyArray.Keys("Up", null, "{w down}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("Up up", null, "{w up}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("Down", null, "{s down}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("Down up", null, "{s up}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("Left", null, "{a down}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("Left up", null, "{a up}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("Right", null, "{d down}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("Right up", null, "{d up}"));

            PipelineData<Object> output = pipeline.runDefault(keyInputs);
        } catch(Exception e) {
            e.printStackTrace();
        }



        /*

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
*/
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
