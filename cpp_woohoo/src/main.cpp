#include "ehAppSample.h"

#include <iostream>
#include <fstream>
#include <functional>

struct WriteData {
  std::string path;
  std::string valueToWrite;
};

struct PipelineData {
  eh::Ret ret;
};

std::function<PipelineData(WriteData)> writeTestInFile = [](WriteData input) {
  std::ofstream fs;
  fs.open(input.path, std::ios::in);
  if(!fs.is_open()){

    PipelineData data;
    data.ret = RET_ERROR(input.path, "doesn't exist");
    return data;
  }
  fs << input.valueToWrite;
  fs.close();

  return PipelineData();
};

template<typename Out, typename In>
Out callFunc(std::function<Out(In)> func, In& input) {
  return func(input);
}


struct Pipeline{
  std::vector<std::function<PipelineData()>> funcs;
};
void pipelineAdd(Pipeline& pipeline, std::function<PipelineData()> func){
  pipeline.funcs.push_back(func);
}
eh::Ret pipelineRun(Pipeline& pipeline){
  for(auto it:pipeline.funcs){
    PipelineData data = it();
    if(data.ret.error) {
      return data.ret;
    } 
  }
  return RET_SUCCESS();
}


int main() {
  Pipeline pipeline;

  std::function<PipelineData()> func = []() {
    WriteData data{.path="tmp.txt", .valueToWrite="test\n"};
    return callFunc<PipelineData, WriteData>(writeTestInFile, data);
  };

  pipelineAdd(pipeline, func);
  eh::Ret ret = pipelineRun(pipeline);
  if(ret.error) {
    LOG_ERROR(ret.msg);
    return 1;
  } 

  return 0;
}
