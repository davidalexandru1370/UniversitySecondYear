import socket
import struct
import select
import random
import math


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


def compute_distance(x1, y1, x2, y2):
    return math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))


master = []
clients = dict()
ip = "127.0.0.1"
port = 8888
random.seed()
x = random.randint(0, 100)
y = random.randint(0, 100)
print("server x = " + str(x))
print("server y = " + str(y))
magic_number = 3
epsilon = 0.001

data = dict()
if __name__ == "__main__":
    server_socket = None
    try:
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0)
        server_socket.bind((ip, port))
        server_socket.listen(magic_number)
        print("Server started")
    except socket.error as err:
        print(err)
        exit(-1)

    master.append(server_socket)

    while len(data.keys()) < magic_number:
        readable, _, _ = select.select(master, [], [])

        for conn in readable:
            if conn == server_socket:
                client_socket, address = server_socket.accept()
                #client_port = tcp_receive_int(client_socket)
                master.append(client_socket)
                #clients.append((client_socket, address))
                clients[client_socket] = address

            else:
                client_x = tcp_receive_int(conn)
                client_y = tcp_receive_int(conn)
                print("Received from" +
                      str(clients[client_socket]) + " x = " + str(client_x) + " y = " + str(client_y))
                data[conn] = compute_distance(client_x, client_y, x, y)

    minim = 1000

    for elem in data.keys():
        if data[elem] < minim:
            minim = data[elem]

    for elem in data.keys():
        if abs(data[elem] - minim) <= epsilon:
            tcp_send_string(elem, "YOU WON!")
        else:
            tcp_send_string(elem, "YOU LOST!")
