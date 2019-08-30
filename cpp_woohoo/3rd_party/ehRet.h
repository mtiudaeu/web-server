#ifndef EH_RET_H
#define EH_RET_H

#include <sstream>
#include <string>

#define EH_BUILD_FILE_INFO eh::Msg::FileInfo(__FILE__, __LINE__)

namespace eh {

struct Ret {
  bool error=false;
  std::string msg;

  Ret(bool _error, std::string _msg) {
    error=_error;
    msg=_msg;
  }

  Ret(){}

};

}

#endif
