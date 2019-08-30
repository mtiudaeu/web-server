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
    RET_ERROR(input.path, "doesn't exist");

  }
  fs << input.valueToWrite;
  fs.close();

  return RET_SUCCESS();
};

template<typename Out, typename In>
Out callFunc(std::function<Out(In)> func, In& input) {
  return func(input);
}

int main() {
  WriteData data{.path="tmp.txt", .valueToWrite="test\n"};
//mdtmp We can build a pipeline that way and them start the application.
// we will then be able to inspect it.
  eh::Ret ret = callFunc<eh::Ret, WriteData>(writeTestInFile, data);
  if(ret.error){
    LOG_ERROR(ret);
    return 1;
  }

  return 0;
}
