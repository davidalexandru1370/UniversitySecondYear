#define _WINSOCK_DEPRECATED_NO_WARNINGS 1

#include <stdio.h>

#ifndef _WIN32
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <arpa/inet.h>

#include <unistd.h>
#include <errno.h>

#define closeSocket close

typedef int SOCKET;
#else
#include <WinSock2.h>
#include <cstdint>
#endif

int main(){
	SOCKET s;
	struct sockaddr_in server,client;
	int c,l,err;

#ifdef _WIN32
	WSADATA wsaData;
	if(WSAStartup(MAKEWORD(2,2), &wsaData) < 0){
		printf("Error initializing windows sockets library!\n");
		return -1;
	}

#endif

	s = socket(AF_INET,SOCK_STREAM,0);
	if(s<0){
		printf("Eroare la crearea socketului server\n");
		return -1;
	}
	
	memset(&server,0,sizeof(server));
	server.sin_port = htons(8888);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;

	if(bind(s,(struct sockaddr*) &server,sizeof(server)) < 0){
		printf("Binding error\n");
		return -1;
	}

	listen(s,5);

	l = sizeof(client);
	memset(&client,0,sizeof(client));

	while(1){
		uint16_t len;
		printf("Listening for incomming connections\n");
		c = accept(s,(struct sockaddr*) &client,&l);
		err = errno;
		#ifdef _WIN32
			err = WSAGetLastError();
		#endif
		if(c < 0){
			printf("accept error: %d", err);
			continue;
		}
		printf("Incomming connected client from %s:%d\n",inet_ntoa(client.sin_addr),ntohs(client.sin_port));
		int res = recv(c,(char*)&len, sizeof(len),0);

		if(res != sizeof(len))
		       	printf("Error receiving operand!\n");
		len = ntohs(len);

		uint16_t whiteSpaces = 0;
		while(len-- > 0){
			uint16_t str;
			recv(c,(char*)&str,sizeof(str),0);
			str = ntohs(str);
			//printf("%c",(char)str);
			if(str == ' '){
				whiteSpaces++;
			}
		}
		
		//res = send(c,(char*)&suma,sizeof(suma),0);
		whiteSpaces = htons(whiteSpaces);
		res = send(c,(char*)&whiteSpaces,sizeof(whiteSpaces),0);

		closeSocket(c);

	}

#ifdef _WIN32
	WSACleanup();
#endif

	return 0;
}
