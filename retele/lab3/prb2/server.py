import socket
import struct
import threading

e = threading.Event()

e.clear()


rs = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
rs.bind(("127.0.0.1",8888))
rs.listen(5)
print("Server started")

def worker(con):
	print("client connection = " ,con.getpeername(),con)
	file_path = con.recv(1024).decode("ascii")
	file = None
	try:
		file = open(file_path,"r")
	except IOError:
		print("Unable to open file" + file_path)
		cs.send("!i",-1)
	file_str = ""
	file_len = 0
	for line in file.readlines():
		file_len += len(line)
		file_str += line
	con.send(struct.pack("!i",file_len))
	con.send(file_str.encode())

while True:
	clientSocket, clientAddress = rs.accept()
	task = threading.Thread(target=worker, args=(clientSocket,))
	task.start()
