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
    struct ficha{
        int i;
        int j;
        string imagen;
    };
    map<string ,string> imagenes;
    vector<ficha> matrizvirtual;
    int page_fault;
    int page_hit;
    string leerdisco(int i, int j);
    string estan_en_memoria(vector<ficha>);
    void mem_usage(double& vm_usage);
    int memoria();

public:
    int tamano;
    bool en_matriz;
    string leermatrizpaginada(int i,int j);
    void crearmatrizdisco();
    matrizpaginada();
    void shuffle();
};



#endif //PROYECTO1DATOSII_MAIN_MATRIZPAGINADA_H