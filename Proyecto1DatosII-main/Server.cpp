/***************************************************************************************/
/* @file    server_secuencial.c                                                        */
/* @brief   Secuencial server. TCP sockets                                             */
/***************************************************************************************/

/*standard symbols */
#include <unistd.h>

/* sockets */
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>

/* strings / errors*/
#include <errno.h>
#include <stdio.h>
#include <string.h>
#include <string>
#include <stdlib.h>
#include "iostream"
#include "matrizpaginada.hpp"
#include "manipulador.hpp"

using namespace std;

/* server parameters */
#define SERV_PORT       8000              /* port */
#define SERV_HOST_ADDR "127.0.0.1"     /* IP, only IPV4 support  */
#define BUF_SIZE        100               /* Buffer rx, tx max size  */
#define BACKLOG         1               /* Max. client pending connections  */

int main(int argc, char* argv[])          /* input arguments are not used */
{
    int sockfd, connfd ;  /* listening socket and connection socket file descriptors */
    unsigned int len;     /* length of client address */
    struct sockaddr_in servaddr, client;


    int  len_rx= 0;                     /* received and sent length, in bytes */

    char buff_rx[BUF_SIZE];   /* buffers for reception  */
    srand(time(NULL)); // necesario para obtener un número diferente en el rand()
    int jugador=rand()%2; //define el primer turno
    string* casillas=new string;// guardo las casillas que salen en un turno
    bool desab=false; //activa el desabilitar fichas
    bool reset= false; //activa el resetear imagenes
    bool especial=true; //activa la ficha especial
    bool notificar=true; //activa las notificaciones
    int turno=0;
    string* id1=new string;//guardo el id obtenido de la primer ficha para comparaciones
    matrizpaginada matrizpaginada;// inico la matriz paginada
    matrizpaginada.crearmatrizdisco();// crear la matriz que va al disco como un .txt
    matrizpaginada.shuffle();
    manipulador manipulador; // manipula el envio de mensajes
    /* socket creation */
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == -1)
    {
        fprintf(stderr, "[SERVER-error]: socket creation failed. %d: %s \n", errno, strerror( errno ));
        return -1;
    }
    else
    {
        printf("[SERVER]: Socket successfully created..\n");
    }

    /* clear structure */
    memset(&servaddr, 0, sizeof(servaddr));

    /* assign IP, SERV_PORT, IPV4 */
    servaddr.sin_family      = AF_INET;
    servaddr.sin_addr.s_addr = inet_addr(SERV_HOST_ADDR);
    servaddr.sin_port        = htons(SERV_PORT);


    /* Bind socket */
    if ((bind(sockfd, (struct sockaddr *)&servaddr, sizeof(servaddr))) != 0)
    {
        fprintf(stderr, "[SERVER-error]: socket bind failed. %d: %s \n", errno, strerror( errno ));
        return -1;
    }
    else
    {
        printf("[SERVER]: Socket successfully binded \n");
    }

    /* Listen */
    if ((listen(sockfd, BACKLOG)) != 0)
    {
        fprintf(stderr, "[SERVER-error]: socket listen failed. %d: %s \n", errno, strerror( errno ));
        return -1;
    }
    else
    {
        printf("[SERVER]: Listening on SERV_PORT %d \n\n", ntohs(servaddr.sin_port) );
    }

    len = sizeof(client);

    /* Accept the data from incoming sockets in a iterative way */
    while(1)
    {
        connfd = accept(sockfd, (struct sockaddr *)&client, &len);
        if (connfd < 0)
        {
            fprintf(stderr, "[SERVER-error]: connection not accepted. %d: %s \n", errno, strerror( errno ));
            return -1;
        }
        else
        {
            while(1) /* read data from a client socket till it is closed */
            {
                /* read client message, copy it into buffer */
                len_rx = recv(connfd, buff_rx, sizeof(buff_rx),0);// recibe los mensajes del cliente

                if(len_rx == -1)
                {
                    fprintf(stderr, "[SERVER-error]: connfd cannot be read. %d: %s \n", errno, strerror( errno ));
                }
                else if(len_rx == 0) /* if length is 0 client socket closed, then exit */
                {
                    close(connfd);
                    break;
                }
                else
                {
                    std::string s(buff_rx,len_rx);// analiza el mensaje como un string
                    if(s.find("rev")==string::npos){ // mientras el mensaje sea diferente de rev
                        string id;
                        char i=s[2];
                        char j=s[3];
                        id=matrizpaginada.leermatrizpaginada(i-48,j-48);// el id en realidad es la imagen obtenida de la matriz paginada

                        if(turno==0){// volteo la primer carta
                            *id1=id;
                            turno++;
                            *casillas=i;
                            especial=true;
                        }

                        else{// volteo la segunda carta
                            *casillas+=i;
                            turno=0;

                            
                            if (id==*id1){// comparo si las 2 imagenes son iguales
                                desab=true; // activo el desabilitar las imagenes
                                matrizpaginada.tamano-=2; //reduzco el tamaño de la matriz
                                matrizpaginada.shuffle();

                            }
                            else{
                                jugador++; // avanza el turno
                                reset=true; // activo el reseteo de las imagenes
                                notificar=true; // notifico el cambio de turno
                            };
                        }
                        *casillas+=j;
                        send(connfd,id.c_str(),id.length(),0);// envio la imagen al cliente
                        cout<<"puntaje jugador 1: "<<manipulador.getpuntos1()<<endl;
                        cout<<"puntaje jugador 2: "<<manipulador.getpuntos2()<<endl;
                        cout<<"----------------------"<<endl;
                    }

                    else{
                        string m; // va a ser mi mensaje
                        if(desab){
                            manipulador.iguales(*casillas);//agrego cuales casillas voy a desabilitar
                            m=manipulador.puntos(jugador,matrizpaginada.en_matriz,*casillas); // agrego los puntos y puntos exta
                            desab=false;
                        }
                        else if(reset){
                            m=manipulador.diferentes(*casillas); // envio las casillas a restablecer
                            reset=false;
                        }
                        else if(turno==0 && notificar){
                            if(jugador%2==0){
                                m="p1";
                            }
                            else{
                                m="p2";
                            }
                            notificar= false;

                            if(manipulador.remontar_aux(jugador)){ // agrego un r si se puede remontar
                                m+="r";
                            }
                        }
                        else if(especial && turno==0){
                            m=manipulador.especial();// agrega las casillas donde agregara la estrella
                            especial=false;
                        }

                        else{
                            m="n";
                        }
                        send(connfd,m.c_str(),m.length(),0); // envio el mensaje que provocara una modificacion
                        }
                    }
                }
            }
        }
    }

