/***************************************************************************************/
/* @file    server_secuencial.c                                                        */
/* @brief   Secuencial server. TCP sockets                                             */
/***************************************************************************************/

/*standard symbols */
#include <unistd.h>

/* sockets */
#include <netdb.h>
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


    int  len_rx, len_tx = 0;                     /* received and sent length, in bytes */
    char buff_tx[BUF_SIZE] = "Hello client, I am the server";
    char buff_rx[BUF_SIZE];   /* buffers for reception  */
    int jugador=rand()%2;
    string* casillas=new string;
    string* puntaje=new string;
    bool desab=false;
    bool reset= false;
    bool notificar=true;
    int turno=0;
    string* id1=new string;
    matrizpaginada matrizpaginada;
    matrizpaginada.crearmatrizdisco();
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
                len_rx = recv(connfd, buff_rx, sizeof(buff_rx),0);

                if(len_rx == -1)
                {
                    fprintf(stderr, "[SERVER-error]: connfd cannot be read. %d: %s \n", errno, strerror( errno ));
                }
                else if(len_rx == 0) /* if length is 0 client socket closed, then exit */
                {
                    //printf("[SERVER]: client socket closed \n\n");
                    close(connfd);
                    break;
                }
                else
                {
                    std::string s(buff_rx,len_rx);
                    if(s.find("rev")==string::npos){
                        string id;
                        char i=s[2];
                        char j=s[3];
                        id=matrizpaginada.leermatrizpaginada(i,j);
                        if(turno==0){
                            *id1=id;
                            turno++;
                            *casillas=i;
                        }
                        else{
                            *casillas+=i;
                            turno=0;
                            jugador++;
                            notificar=true;
                            if (id==*id1){
                                jugador--;
                                desab=true;
                                matrizpaginada.tamano-=2;
                                if(jugador%2==0){
                                    *puntaje="11";
                                }
                                else{
                                    *puntaje="21";
                                }
                            }
                            else{
                                reset=true;
                            };
                        } 
                        *casillas+=j;
                        int mlen=id.length();
                        send(connfd,id.c_str(),mlen,0);
                    }
                    else{
                        string m;
                        if(desab){
                            m="d";
                            m+=*casillas;
                            m+=*puntaje;
                            desab=false;
                        }
                        else if(reset){
                            m="v";
                            m+=*casillas;
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
                        }

                        else{
                            m="n";
                        }
                        int mlen=m.length();
                        send(connfd,m.c_str(),mlen,0);
                        }
                    }
                }
            }
        }
    }

