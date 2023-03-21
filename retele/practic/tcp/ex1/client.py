import socket
import select
import threading


port = 8888
ip = "127.0.0.1"
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect((ip, port))
master = [client_socket]


def receive_messages():
    while True:
        readable, _, _ = select.select(master, [], [])
        if client_socket in readable:
            data = client_socket.recv(255).decode("ascii")
            if (len(data.strip()) > 0):
                print(data)


def send_message():
    while True:
        data = input("message=")
        if (len(data) > 0):
            client_socket.send(bytes(data, "ascii"))


if __name__ == "__main__":
    receive_thread = threading.Thread(target=receive_messages, args=())
    send_thread = threading.Thread(target=send_message, args=())
    receive_thread.start()
    send_thread.start()

    receive_thread.join()
    send_thread.join()
