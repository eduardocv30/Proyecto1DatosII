cmake_minimum_required(VERSION 3.21)
project(Proyecto1DatosII_main)

set(CMAKE_CXX_STANDARD 14)

add_executable(Proyecto1DatosII_main Server.cpp matrizpaginada.cpp matrizpaginada.hpp manipulador.cpp manipulador.hpp)
