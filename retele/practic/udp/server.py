import socket
import select
import random
import struct
master = []
ip = "127.0.0.1"
port = 8888
start = 1
end = 2**17 - 1
random_number = random.randint(start, end)


if __name__ == "__main__":
    # server_socket.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)
    server_socket = None
    try:
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, 0)
        server_socket.bind((ip, port))
    except socket.error as socket_error:
        print(socket_error)
        exit(-1)

    print("Server random number = " + str(random_number))

    while True:
        client_number, address = server_socket.recvfrom(4)
        master.append(address)
        number = struct.unpack("!I", client_number)[0]
        print(number)
        if number > random_number:
            server_socket.sendto(b'S', address)
        if number < random_number:
            server_socket.sendto(b'H', address)
        if number == random_number:
            winner = address
            break

    for conn in master:
        print(conn)
        if conn == winner:
            server_socket.sendto(b'W', conn)
        else:
            server_socket.sendto(b'L', conn)
