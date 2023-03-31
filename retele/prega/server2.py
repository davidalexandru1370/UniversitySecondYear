import sys
import socket
import select

port = 8888
ip_address = "127.0.0.1"

master = []
hello_message = "User join"

def sendToAll(message):
    for fd in master:
        fd.send(bytes(message,"ascii"))

def getIpAddress(socket_descriptor):
    peer = socket_descriptor.getpeername()
    return peer

if __name__ == "__main__":
    server_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM,0)
    server_socket.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)

    master.append(server_socket)

    server_socket.bind((ip_address,port))

    server_socket.listen(5)

    while True:
        ready_to_read, _,_ = select.select(master,[],[])
        
