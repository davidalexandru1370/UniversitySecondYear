/* UDP client in the internet domain */
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <netdb.h>
#include <strings.h>
#include <stdio.h>
#include <time.h>
#define or ||
void error(char *);
int main(int argc, char *argv[])
{
   int sock, length, n;
   struct sockaddr_in server, from; // IP Addressing(ip, port, type) Stuff
   struct hostent *hp; // DNS stuff
   char buffer[256];
   char receivedMessage[256];
   char template[] = "PING ";
   char requestMessageBody[256];
   time_t sendRequestTime;
   time_t receiveResponseTime;

   if (argc != 3) { printf("Usage: %s <server_name> <port>\n",argv[0]);
                    exit(1);
   }

   sock= socket(AF_INET, SOCK_DGRAM, 0);
   if (sock < 0) error("socket");

   server.sin_family = AF_INET;
   hp = gethostbyname(argv[1]);
   if (hp==0) error("Unknown host");
   bcopy((char *)hp->h_addr, (char *)&server.sin_addr, hp->h_length);
   server.sin_port = htons(atoi(argv[2]));
   length=sizeof(struct sockaddr_in);
   printf("Please enter the message: ");
   bzero(buffer,256);
   bzero(requestMessageBody,256);
   bzero(receivedMessage,256);
   fgets(buffer,255,stdin);
   strcpy(requestMessageBody,template);
   strcpy(requestMessageBody+strlen(template),buffer);

   n=sendto(sock,requestMessageBody,strlen(requestMessageBody),0,&server,length);
   time(&sendRequestTime);
   if (n < 0) error("Sendto");

   n = recvfrom(sock,receivedMessage,256,0,&from, &length);
   time(&receiveResponseTime);
   if (n < 0) error("recvfrom");
   //printf("%lu %lu %d \n", strlen(buffer),strlen(receivedMessage),n);
   if(strlen(buffer) != strlen(receivedMessage) || strcmp(buffer,receivedMessage) != 0){
    error("Text is not the same\n");
   }
 
   printf("Response time: %f ms\n",difftime(receiveResponseTime,sendRequestTime)*1000);
}

void error(char *msg)
{
    perror(msg);
    exit(0);
}

 