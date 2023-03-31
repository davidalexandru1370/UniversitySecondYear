import struct
import socket
import random
import select
import sys


def tcp_send_int(address, value):
    address.send(struct.pack("!i", value))


def tcp_send_string(address, value):
    address.send(value.encode("ascii"))


def tcp_receive_string(sock, size=1024):
    return sock.recv(size).decode("ascii")


def tcp_receive_int(sock):
    return struct.unpack("!i", sock.recv(4))[0]


def pair_to_string(ip_port):
    return str(ip_port[0]) + ":" + str(ip_port[1])


def string_to_pair(ip_port):
    values = ip_port.split(':')
    return (values[0], int(values[1]))


def udp_send_string(sock, string, address):
    sock.sendto(string.encode("ascii"), address)


def udp_receive_string(sock):
    string, addr = sock.recvfrom(1024)
    string = string.decode("ascii")
    return (string, addr)


client_ip = "127.0.0.1"
server_ip = "127.0.0.1"
server_port = 8888
client_port = random.randint(50000, 60000)
# print(client_port)
clients = []

server_connection = socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0)
server_connection.connect((server_ip, server_port))

client_connection = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client_connection.bind((client_ip, client_port))

tcp_send_int(server_connection, client_connection.getsockname()[1])
# print(client_connection.getsockname())
already_connected_clients = tcp_receive_int(server_connection)
for _ in range(already_connected_clients):
    size = tcp_receive_int(server_connection)
    address_port = string_to_pair(tcp_receive_string(server_connection, size))
    clients.append(address_port)

master = [sys.stdin, server_connection, client_connection]

while True:
    readable, _, _ = select.select(master, [], [])
    for fd in readable:
        if fd == server_connection:
            case = tcp_receive_int(server_connection)
            size = tcp_receive_int(server_connection)
            address_port = string_to_pair(
                tcp_receive_string(server_connection, size))
            if case == 0:
                clients.append(address_port)
                print("Client {0} has connected".format(address_port))
            elif case == 1:
                clients.remove(address_port)
                print("Client {0} has disconnected".format(address_port))
            print(clients)
        elif fd == sys.stdin:
            message = input()
            if message == "QUIT":
                tcp_send_string(server_connection, message)
                exit(0)
            for c in clients:
                udp_send_string(client_connection, message, c)
        elif fd == client_connection:
            msg, address = udp_receive_string(client_connection)
            print("{0} -> {1}".format(pair_to_string(address), msg))
