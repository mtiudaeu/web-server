package com.mtiudaeu;

import com.mtiudaeu.Lambda.Lambda0;
import com.mtiudaeu.Lambda.Lambda1;
import com.mtiudaeu.Lambda.Lambda2;
import com.mtiudaeu.Lambda.LambdaData;
import com.mtiudaeu.Pipe.Pipe;
import com.mtiudaeu.Pipe.Pipe0;
import com.mtiudaeu.Pipe.Pipe1;
import com.mtiudaeu.Pipe.Pipe2;
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
    /*

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
    */

    static Lambda0<AutoKeyArray> get1(){
        return () -> {
            AutoKeyArray keyInputs = new AutoKeyArray();
            keyInputs.keys.add(new AutoKeyArray.Keys("0", "0", "0"));
            keyInputs.keys.add(new AutoKeyArray.Keys("1", "1", "z"));
            for (Integer i = 2; i < 10; i++) {
                keyInputs.keys.add(new AutoKeyArray.Keys(i.toString(), i.toString(), i.toString()));
            }
            for (Integer i = 0; i < 10; i++) {
                keyInputs.keys.add(new AutoKeyArray.Keys("+" + i.toString(), i.toString(), "{Shift down}" + i.toString() + "{Shift up}"));
            }
            for (Integer i = 1; i < 6; i++) {
                keyInputs.keys.add(new AutoKeyArray.Keys("F" + i.toString(), "F" + i.toString(), "{F" + i.toString() + "}"));
            }
            for (Integer i = 1; i < 6; i++) {
                keyInputs.keys.add(new AutoKeyArray.Keys("+F" + i.toString(), "F" + i.toString(), "{Shift down}{F" + i.toString() + "}{Shift up}"));
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

            return new LambdaData<>(keyInputs);
        };
    }

    static Lambda1<AutoKeyArray, String> get2(){
        return (LambdaData<AutoKeyArray> keys) -> {
            String value = new String();

            for(AutoKeyArray.Keys it : keys.data.keys) {
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

                // mdtmp todo comment
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

            return new LambdaData<>(value);
        };
    }

    static Lambda0<String> get3(){
        return () -> new LambdaData<>("out/tmp.txt");
    }

    static Lambda2<String, String, DataToWrite> get4(){
        return (LambdaData<String> value, LambdaData<String> path) -> {
            DataToWrite data = new DataToWrite();
            data.path = path.data;
            data.valueToWrite = value.data;
            return new LambdaData(data);
        };
    }

    static Lambda1<DataToWrite, Object> get5(){
        return (LambdaData<DataToWrite> dataToWrite) -> {
            LambdaData ret = new LambdaData<Object>();
            try {
                Files.write(Paths.get(dataToWrite.data.path), dataToWrite.data.valueToWrite.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                ret.setError("Fail to write file"); //mdtmp line and file....
            }
            return ret;
        };
    }

    static class PipeInput0Output1<Output1> implements Lambda0<Output1> {
        PipeInput0Output1(Lambda0<Output1> func){
            this.func = func;
        }
        private  Lambda0<Output1> func;

        public LambdaData<Output1> run() {
            return func.run();
        }
    }

    public static void main(String[] args) {
        Lambda0<AutoKeyArray> keysFunc = get1();
        Lambda1<AutoKeyArray, String> keysToValueFunc = get2();
        Lambda0<String> pathFunc = get3();
        Lambda2<String, String, DataToWrite> mergeKeysWithPathFunc = get4();
        Lambda1<DataToWrite, Object> writeDataFunc = get5();




        Pipe<Object> end = new Pipe1<DataToWrite, Object>(
                new Pipe2<String, String, DataToWrite>(
                        new Pipe1<AutoKeyArray, String>(
                                new Pipe0<AutoKeyArray>(keysFunc),
                                keysToValueFunc),
                        new Pipe0<String>(pathFunc),
                        mergeKeysWithPathFunc),
                writeDataFunc);

        //mdtmp end.run();
        end.traverse(0);

        //mdtmp LambdaData<Object> end = writeDataFunc.run(mergeKeysWithPathFunc.run(keysToValueFunc.run(keysFunc.run()), pathFunc.run()));
        //mdtmp Pipe<Object> end2 = (new Pipe0<AutoKeyArray>(keysFunc))
/*
        Lambda2<Lambda0<AutoKeyArray>, Lambda1<AutoKeyArray, String>, String> func1 =
                (Lambda0<AutoKeyArray> in1, Lambda1<AutoKeyArray, String> in2)->
                {
                    LambdaData<AutoKeyArray> out1 = in1.run();
                    if(out1.error){
                        //mdtmp
                        System.out.println(out1.errorMsg);
                    }
                    LambdaData<String> out2 = in2.run(out1);
                    if(out2.error){
                        //mdtmp
                        System.out.println(out2.errorMsg);
                    }
                    return out2;
                };

        LambdaData<String> keysString = func1.run(keysFunc, keysToValueFunc);
        LambdaData<String> path = pathFunc.run();
        LambdaData<DataToWrite> writeToFile = mergeKeysWithPathFunc.run(keysString.data, path.data);
        LambdaData<Object> end = writeDataFunc.run(writeToFile.data);
*/
/*
        LambdaData<AutoKeyArray> keys = keysFunc.run();
        LambdaData<String> keysString = keysToValueFunc.run(keys);
        LambdaData<String> path = pathFunc.run();
        LambdaData<DataToWrite> writeToFile = mergeKeysWithPathFunc.run(keysString, path);
        LambdaData<Object> end = writeDataFunc.run(writeToFile);
*/

        /*
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
        */
    }
}
