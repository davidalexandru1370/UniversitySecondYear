import socket
import struct


def tcp_send_int(address, value):
    address.send(struct.pack("!i", value))


def tcp_receive_int(sock):
    return struct.unpack("!i", sock.recv(4))[0]


def tcp_receive_string(sock, size=1024):
    return sock.recv(size).decode("ascii")


ip = "127.0.0.1"
port = 8888

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0)
client.connect((ip, port))

x = int(input("x="))
y = int(input("y="))

tcp_send_int(client, x)
tcp_send_int(client, y)

message = tcp_receive_string(client)
print(message)
