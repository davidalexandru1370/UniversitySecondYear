#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <stdint.h>

#define max 100
//git
int main() {
	int c, cod;
	int32_t r;
	// Observatie: Deoarece dimensiunea tipului int difera de la platforma la platforma,
	// (spre exemplu, in Borland C in DOS e reprezentat pe 2 octeti, iar in C sub Linux pe
	// 4 octeti) este necesara utilizarea unor tipuri intregi standard. A se vedea
	// stdint.h.
	struct sockaddr_in server;
	char s[max];
	//char o[max];

	c = socket(PF_INET, SOCK_STREAM, 0);
	if (c < 0) {
		fprintf(stderr, "Eroare la creare socket client.\n");
		return 1;
	}

	memset(&server, 0, sizeof(struct sockaddr_in));
	server.sin_family = AF_INET;
	server.sin_port = htons(4321);
	server.sin_addr.s_addr = inet_addr("127.0.0.1");

	cod = connect(c, (struct sockaddr *) &server, sizeof(struct sockaddr_in));
	if (cod < 0) {
		fprintf(stderr, "Eroare la conectarea la server.\n");
		return 1;
	}

	printf("Dati o comanda de trimis la server = ");
	fgets(s, max, stdin);

	// !!! important - trimit lungimea sirului + 1 pentru a trimite pe socket si caracterul NULL (0) care marcheaza sfarsitului sirului.
	// paragraful 1 din protocol
	cod = send(c, s, strlen(s) + 1, 0);
	if (cod != strlen(s) + 1) {
		fprintf(stderr, "Eroare la trimiterea datelor la server.\n");
		return 1;
	}

	// paragraful 5 din protocol

	//r = ntohl(r);
	//if (cod != sizeof(int)) {
	//	fprintf(stderr, "Eroare la primirea datelor de la client.\n");
	//	return 1;
	//}

	//cod = recv(c, &r, sizeof(int32_t), MSG_WAITALL);
	char ch = 1;
	//printf("%d\n",r);
	while(ch != 0){
		cod = recv(c,&ch,sizeof(char),MSG_WAITALL);
		//printf("%c\n",(char)ch);
		
		printf("%c",(char)ch);
	}

	close(c);
}


