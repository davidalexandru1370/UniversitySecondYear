#include <iostream>
#include "ShortTest.h"
#include "ExtendedTest.h"
#include <crtdbg.h>

int main(){
    testAll();
    testAllExtended();
    std::cout<<"Finished IL Tests!"<<std::endl;
    _CrtDumpMemoryLeaks();
    system("pause");
}