//
// Created by jose on 10/4/22.
//

#ifndef PROYECTO1DATOSII_MAIN_MANIPULADOR_HPP
#define PROYECTO1DATOSII_MAIN_MANIPULADOR_HPP
#include "string"
#include "iostream"
#include "fstream"
#include "matrizpaginada.hpp"
using namespace std;

class manipulador {
public:
    manipulador();
    int puntosj1;
    int puntosj2;
    string iguales(string casillas);
    string diferentes(string casillas);
    string puntos(int jugador, bool extra, string casillas);
    string especial();
    bool remontar_aux(int jugador);
    int remontar(int jugador);
    int getpuntos1();
    int getpuntos2();

private:
    string retorno;
    int i;
    int j;
    bool _remontar;
};


#endif //PROYECTO1DATOSII_MAIN_MANIPULADOR_HPP
