import select
import socket

clients = []
port = 8888
ip = "127.0.0.1"
start_message = "connected to server"
master = []


def send_message_to_clients(message, blacklist):
    for client_descriptor in clients:
        if client_descriptor not in blacklist:
            client_descriptor.send(bytes(message, "ascii"))


def get_ip_address(socket_descriptor):
    return socket_descriptor.getpeername()


if __name__ == "__main__":
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    clients.append(server_socket)

    server_socket.bind((ip, port))

    server_socket.listen(5)
    while True:
        content, _, _ = select.select(clients, [], [])

        for connection in content:
            if connection == server_socket:
                client_socket, address = server_socket.accept()
                clients.append(client_socket)
                client_socket.send(bytes(start_message, "ascii"))
            else:
                received_message = connection.recv(255).decode("ascii")
                message = "message from " + str(get_ip_address(connection)[
                    0] + str(get_ip_address(connection)[1])) + " " + received_message
                send_message_to_clients(message, [server_socket, connection])
