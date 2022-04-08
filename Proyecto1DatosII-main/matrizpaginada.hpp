//
// Created by jose on 30/3/22.
//

#ifndef PROYECTO1DATOSII_MAIN_MATRIZPAGINADA_H
#define PROYECTO1DATOSII_MAIN_MATRIZPAGINADA_H

#include <vector>
#include "map"
#include "string"
#include "iostream"
#include "fstream"

using namespace std;

class matrizpaginada {
private:
    map<string ,string> imagenes;
    vector<string> matrizvirtual;
    string leerdisco(int i, int j);
    void redimencionar();
public:
    int tamano;
    string leermatrizpaginada(char i,char j);
    void crearmatrizdisco();
    matrizpaginada();
};



#endif //PROYECTO1DATOSII_MAIN_MATRIZPAGINADA_H