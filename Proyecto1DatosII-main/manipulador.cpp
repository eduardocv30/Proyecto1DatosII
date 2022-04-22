//
// Created by jose on 10/4/22.
//

#include "manipulador.hpp"
#include "matrizpaginada.hpp"
#include "string"
#include "iostream"
#include "fstream"
#include "stdlib.h"
using namespace std;

manipulador::manipulador() {
    i=0;
    j=0;
    _remontar=false;

}

string manipulador::iguales(string casillas) {
    retorno="d"; // agrego la clave para desactivar
    retorno+=casillas;
    return retorno;
}
string  manipulador::diferentes(string casillas) {
    retorno="v"; // clave para voltear
    retorno+=casillas;
    return retorno;
}

string manipulador::puntos(int jugador, bool en_disco, string casillas) {
     // indica a cual jugador se le daran puntos
    if(jugador%2==0){
        retorno+="1";
        puntosj1++;
    }else{
        retorno+="2";
        puntosj2++;
    }


    if (en_disco){ // si la ficha estaba en la matriz virtual
        retorno+="2";
        if(jugador%2==0){
            puntosj1++;
        }else{
            puntosj2++;}
    }
    else{
        retorno+="1";
    }

    if((casillas[0]-48==i && casillas[1]-48==j) ||(casillas[2]-48==i && casillas[3]-48==j) ){ // analiza si se acertó la casilla extrella
        retorno+="e";
        if(jugador%2==0){
            puntosj1+=3;
        }else{
            puntosj2+=3;}
    }

    if(remontar_aux(jugador)){ // si se puede remontar
        int n=remontar(jugador)-1;
        if (n<0){
            n=n*(-1);
        }
        retorno+= to_string(n);
        if(jugador%2==0){
            puntosj1+=n;
        }else{
            puntosj2+=n;}
    }

    return retorno;
}


string manipulador::especial() { // power de la estrella
    retorno="e";
    srand(time(NULL));
    // las primera 2 son las coordenadas a restablecer
    retorno+= to_string(i);
    retorno+= to_string(j);
    i=rand()%6;
    j=rand()%7;
    // nueva posición de la estrella
    retorno+= to_string(i);
    retorno+= to_string(j);
    return retorno;
}

bool manipulador::remontar_aux(int jugador) { // activa el power up de remontar
    if(puntosj2-puntosj1>4 && jugador%2==0){
        return true;
    }
    else if(puntosj1-puntosj2>4 && jugador%2==1){
        return true;
    }
    else{
        return false;
    }
}

int manipulador::remontar(int jugador) { //realiza los calculos para remontar
    int puntos=(puntosj1-puntosj2)/2;

    if(puntos<0){
        puntos=puntos*-1;
    }

    if (puntos>9){
        puntos=9;
    }
}

int manipulador::getpuntos1() {
    return puntosj1
}

int manipulador::getpuntos2() {
    return puntosj2
}