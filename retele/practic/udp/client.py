import random
import socket
import struct
ip = "127.0.0.1"
port = 8888

if __name__ == "__main__":
    client_socket = None

    try:
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, 0)
    except socket.error as socket_error:
        print(socket_error)
        exit(-1)

    finished = False
    left = 1
    right = 2**17-1

    random.seed()

    answer = None
    my_num = 0
    steps = 0
    while not finished:
        my_num = (left+right)//2
        try:
            client_socket.sendto(struct.pack(
                "!I", my_num), (ip, port))
            answer, address = client_socket.recvfrom(1)
        except socket.error as msg:
            print(msg)
            client_socket.close()
            exit(-1)
        steps += 1
        print('Send', my_num, ' Answer ', answer.decode("ascii"))
        if answer == b'H':
            left = my_num + 1

        if answer == b'S':
            right = my_num - 1

        if answer == b'W' or answer == b'L':
            finished = True

    client_socket.close()

    if answer == b'W':
        print("Winner with number = ", my_num, " with steps ", steps)
    else:
        print("Lost")
