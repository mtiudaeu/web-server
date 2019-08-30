#include "ehAppSample.h"

#include <iostream>
#include <fstream>
#include <functional>

struct WriteData {
  std::string path;
  std::string valueToWrite;
};
std::function<eh::Ret(WriteData)> writeTestInFile = [](WriteData input) {
  std::ofstream fs;
  fs.open(input.path, std::ios::in);
  if(!fs.is_open()){
    return RET_ERROR(input.path, "doesn't exist");
  }
  fs << input.valueToWrite;
  fs.close();

  return RET_SUCCESS();
};

template<typename Out, typename In>
Out callFunc(std::function<Out(In)> func, In& input) {
  return func(input);
}

struct Pipeline{
  std::vector<std::function<eh::Ret()>> funcs;
};
void pipelineAdd(Pipeline& pipeline, std::function<eh::Ret()> func){
  pipeline.funcs.push_back(func);
}
eh::Ret pipelineRun(Pipeline& pipeline){
  for(auto it:pipeline.funcs){
    eh::Ret ret = it();
    if(ret.error) {
      return ret;
    } 
  }
  return RET_SUCCESS();
}


int main() {
  Pipeline pipeline;

  std::function<eh::Ret()> func = []() {
    WriteData data{.path="tmp.txt", .valueToWrite="test\n"};
    return callFunc<eh::Ret, WriteData>(writeTestInFile, data);
  };

  pipelineAdd(pipeline, func);
  eh::Ret ret = pipelineRun(pipeline);
  if(ret.error) {
    LOG_ERROR(ret.msg);
    return 1;
  } 

  return 0;
}
