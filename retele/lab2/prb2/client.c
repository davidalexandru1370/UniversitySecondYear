#include <sys/types.h>
#include <stdlib.h>
#include <sys/socket.h>

#include <stdio.h>

#include <netinet/in.h>

#include <netinet/ip.h>

#include <string.h>

#include <unistd.h>

#include <arpa/inet.h>

int main(){
	int c;
	struct sockaddr_in server;
	char* text = NULL;
	uint16_t whiteSpaces;
	size_t cv;
	uint16_t len;
	
	c = socket(AF_INET,SOCK_STREAM,0);

	if(c < 0){
		printf("Eroare la crearea socketului client\n");
	}

	memset(&server,0,sizeof(server));
	server.sin_port = htons(8888);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = inet_addr("127.0.0.1");

	if(connect(c,(struct sockaddr*)&server,sizeof(server)) < 0){
		printf("Eroare la conectarea la server\n");
		return -1;
	}
	printf("text=");
	int read = getline(&text,&cv,stdin);
	if(read < 0){
		printf("Invalid text!\n");
		return -1;
	}
	len = htons(strlen(text)-1);
	//printf("%s",text);
	send(c,&len,sizeof(len),0);
	
	for(int i=0;i<strlen(text)-1;i++){
		uint16_t ch = htons((int)text[i]);
		//char c = (char)ch;
		//printf("%d",ch);
		send(c,&ch,sizeof(ch),0);
	}
	
	recv(c,&whiteSpaces,sizeof(whiteSpaces),0);
	whiteSpaces = ntohs(whiteSpaces);
	printf("Number of whitespaces is %hu\n",whiteSpaces);
	free(text);
	close(c);

return 0;
}
