package com.mtiudaeu;

import com.mtiudaeu.pipeline.*;

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
    static public class AddPathToDataToWrite extends PipelineDataMerge<String, String,DataToWrite> {
        public AddPathToDataToWrite(String data) {
            super(data);
        }
        public PipelineData<DataToWrite> run(String valueToWrite) {
            DataToWrite data = new DataToWrite();
            data.path = this.data;
            data.valueToWrite = valueToWrite;
            return new PipelineData(data);
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
        Pipeline1<AutoKeyArray> keyArray = (() -> {
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
            for(Integer i=1; i<6; i++) {
                keyInputs.keys.add(new AutoKeyArray.Keys("+F"+i.toString(), "F"+i.toString(), "{Shift down}{F"+i.toString()+"}{Shift up}"));
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

            keyInputs.keys.add(new AutoKeyArray.Keys("f", "f", "f"));
            keyInputs.keys.add(new AutoKeyArray.Keys("t", "t", "t"));
            keyInputs.keys.add(new AutoKeyArray.Keys("g", "g", "g"));
            keyInputs.keys.add(new AutoKeyArray.Keys("y", "y", "y"));
            keyInputs.keys.add(new AutoKeyArray.Keys("+f", "f", "{Shift down}f{Shift up}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("+t", "t", "{Shift down}t{Shift up}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("+g", "g", "{Shift down}g{Shift up}"));
            keyInputs.keys.add(new AutoKeyArray.Keys("+y", "y", "{Shift down}y{Shift up}"));

            return new PipelineData<AutoKeyArray>(keyInputs);
        });

        Pipeline3<AutoKeyArray, String, DataToWrite> prepareToWrite = new Pipeline3<AutoKeyArray, String, DataToWrite>(new AutoKeyWrap(), new AddPathToDataToWrite("out/tmp.txt"));
        Pipeline3<AutoKeyArray, DataToWrite, Object> finalPipeline = new Pipeline3<AutoKeyArray, DataToWrite, Object>(prepareToWrite, new WriteToFile());

        DefaultPipeline<AutoKeyArray, Object> pipeline = new DefaultPipeline<AutoKeyArray, Object>(keyArray, finalPipeline);
        try {
            PipelineData<Object> output = pipeline.runDefault(); //Add all in DataPipeline class. no parameter to runDefault.
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
