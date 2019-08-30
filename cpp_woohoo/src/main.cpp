#include "ehAppSample.h"

#include <iostream>
#include <fstream>

struct WriteData {
  std::string path;
  std::string valueToWrite;
};

eh::Ret writeTestInFile(WriteData input) {
  std::ofstream fs;
  fs.open(input.path, std::ios::in);
  if(!fs.is_open()){
    return RET_ERROR(input.path, "doesn't exist");

  }
  fs << input.valueToWrite;
  fs.close();

  return RET_SUCCESS();
}

int main() {

  WriteData data{.path="tmp.txt", .valueToWrite="test\n"};
  eh::Ret ret = writeTestInFile(data);
  if(ret.error){
    LOG_ERROR(ret);
    return 1;
  }

  return 0;
}
