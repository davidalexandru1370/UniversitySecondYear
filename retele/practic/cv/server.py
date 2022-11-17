# Write a pair of TCP/IP applications (SERVER, CLIENT) where each CLIENT connects to the SERVER on port 7000 over TCP.
# The server keeps a list of all connected CLIENTS (ip and port) and sends that list to each CLIENT upon connection.
#  The server also sends incremental changes to the list to each of the connected CLIENTS whenever a new CLIENT arrives
#   or when a CLIENT closes its TCP connection to the server.

# Each CLIENT reads messages from the standard input and sends that message over UDP to all the other CLIENTS registered to the server
# (the list is kept by each CLIENT and updated by the SERVER).
# Users can type in messages at the standard input and each message will be sent by the CLIENT to all other registered CLIENTS through UDP.
# Whenever the user enters a message with the content “QUIT” - that CLIENT disconnects its TCP connection from the SERVER and closes its
# UDP socket (the “QUIT” message is not sent to all other clients).
# Upon receiving a list incremental update, each CLIENT displays a message stating the client action
#  (Ex: : Client 192.168.0.3:5000 has disconnected” or “Client 192.168.0.3:5000 has connected”)
import socket
import struct
import select


def tcp_send_int(address, value):
    address.send(struct.pack("!i", value))


def tcp_send_string(address, value):
    address.send(value.encode("ascii"))


def tcp_receive_string(sock, size=1024):
    return sock.recv(size).decode("ascii")


def tcp_receive_int(sock):
    return struct.unpack("!i", sock.recv(4))[0]


ip = "127.0.0.1"
port = 8888
master = []
clients = []

if __name__ == "__main__":
    server_socket = None
    try:
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0)
        server_socket.bind((ip, port))
        server_socket.listen(10)
        print("Server started")
    except socket.error as err:
        print(err)
        exit(-1)

    master.append(server_socket)

    while True:
        readable, _, _ = select.select(master, [], [])

        for connection in readable:
            if connection == server_socket:
                client_socket, address = server_socket.accept()
                client_port = tcp_receive_int(client_socket)
                client_address = str(address[0]) + ":" + str(client_port)
                # send to already connected clients that a new client has joined
                for client in clients:
                    #client.send(struct.pack("!i", 0))
                    tcp_send_int(client[0], 0)
                    tcp_send_int(client[0], len(client_address))
                    tcp_send_string(client[0], client_address)

                # send to new connected client the list with already connected clients
                tcp_send_int(client_socket, len(clients))
                for client in clients:
                    tcp_send_int(client_socket, len(client[1]))
                    tcp_send_string(client_socket, client[1])

                master.append(client_socket)
                clients.append((client_socket, client_address))
            else:
                msg = tcp_receive_string(connection)
                cl = None
                for c in clients:
                    if c[0] == connection:
                        cl = c
                if msg == "QUIT":
                    for c2 in clients:
                        if c2 != cl:
                            tcp_send_int(c2[0], 1)
                            tcp_send_int(c2[0], len(cl[1]))
                            tcp_send_string(c2[0], cl[1])
                    clients.remove(cl)
                connection.close()
                master.remove(connection)
