#include "Console.h"
#include "Graph.h"
#include <iostream>

int main()
{
	Graph graph;
	Console* console=new Console(graph);
	console->run_console();
	return 0;
}

