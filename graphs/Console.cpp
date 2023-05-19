#include "Console.h"
#include <fstream>
#include <iostream>
#include <chrono>
#include <string>
Console::Console(Graph _graph)
{
	graph = _graph;
}

void Console::print_menu()
{
	std::cout << "Press 1 to get the number of vertices\n";
	std::cout << "Press 2 to check if there is a vertex between two nodes\n";
	std::cout << "Press 3 to print the out degree of a node\n";
	std::cout << "Press 4 to print the in degree of a node\n";
	std::cout << "Press 5 to print all outbounds edges of a node\n";
	std::cout << "Press 6 to print all the inbounds edges of a node\n";
	std::cout << "Press 7 to read the graph from a file.\n";
	std::cout << "Press 8 to print the graph into a file.\n";
	std::cout << "Press 9 to print all the nodes and edges\n";
	std::cout << "Press 10 to remove a node\n";
	std::cout << "Press 11 to remove an edge\n";
	std::cout << "Press 12 to change cost between two nodes\n";
	std::cout << "Press 13 to add a new edge between two nodes\n";
	std::cout << "Press 14 to add a new node\n";
	std::cout << "Press 15 to get print the number of edges\n";
	std::cout << "Press 16 to see cost between two nodes\n";
	std::cout << "Press 17 to generate a random graph\n";
	std::cout << "Press 18 to exit\n";
}

void Console::run_console()
{
	int running = 1;
	//generate_random_graph("random_graph1.txt");
	//generate_random_graph("random_graph2.txt");
	while (running)
	{
		print_menu();
		int choice;
		std::cout << "your choice=";
		fseek(stdin, 0, SEEK_END);
		scanf("%d", &choice);
		if (choice == 1)
		{
			print_all_the_nodes();
		}
		else if (choice == 2) {
			print_if_is_edge_between_two_nodes();
		}
		else if (choice == 3)
		{
			print_the_out_degree_of_a_node();
		}
		else if (choice == 4)
		{
			print_the_in_degree_of_a_node();
		}
		else if (choice == 5)
		{
			print_outbounds_nodes_of_a_node();
		}
		else if (choice == 6)
		{
			print_inbounds_nodes_of_a_node();
		}
		else if (choice == 7)
		{
			read_graph_from_file();
		}
		else if (choice == 8)
		{
			print_graph_to_file("output.txt");
		}
		else if (choice == 9) {
			print_all_graph();
		}
		else if (choice == 10)
		{
			remove_a_node();
		}
		else if (choice == 11)
		{
			remove_an_edge();
		}
		else if (choice == 12)
		{
			change_cost_between_two_nodes();
		}
		else if (choice == 13) {
			add_a_new_edge_between_two_nodes();
		}
		else if (choice == 14) {
			add_a_new_node();
		}
		else if (choice == 15) {
			print_number_of_edges();
		}
		else if (choice == 16) {
			print_const_between_two_nodes();
		}
		else if (choice == 17) {
			generate_random_graph("output.txt");
		}
		else if (choice == 18) {
			running = false;
		}
		else {
			printf("Invalid menu!\n");
		}

	}
}

void Console::print_all_the_nodes()
{
	std::cout << graph.get_number_of_nodes() << "\n";
}

void Console::add_a_new_edge_between_two_nodes()
{
	int cost;
	int source, target;
	std::cout << "source=";
	std::cin >> source;
	std::cout << "target=";
	std::cin >> target;
	std::cout << "cost=";
	std::cin >> cost;
	try
	{
		graph.add_edge(source, target, cost);
	}
	catch (const std::exception& except)
	{
		//std::cout << "There already exists an edge between those two nodes\n";
		std::cout << except.what();
	}
}

void Console::print_if_is_edge_between_two_nodes()
{
	int node1, node2;
	std::cout << "source node=";
	std::cin >> node1;
	std::cout << "target node=";
	std::cin >> node2;
	if (graph.check_if_node_exists(node1) == false)
	{
		std::cout << "The node " << node1 << " does not exists!\n";
		return;
	}
	if (graph.check_if_node_exists(node2) == false)
	{
		std::cout << "The node " << node2 << " does not exists!\n";
		return;
	}

	if (graph.is_edge(node1, node2) == false)
	{
		std::cout << "No edge!\n";
	}
	else {
		std::cout << "There is an edge!\n";
	}
}

void Console::print_the_out_degree_of_a_node()
{
	int node;
	std::cout << "node=";
	std::cin >> node;
	try
	{
		std::cout << graph.get_out_degree_of_node(node) << "\n";
	}
	catch (const std::exception&)
	{
		std::cout << "The node does not exist!\n";
	}
}

void Console::print_the_in_degree_of_a_node()
{
	int node;
	std::cout << "node=";
	std::cin >> node;
	try
	{
		std::cout << graph.get_in_degree_of_node(node) << "\n";
	}
	catch (const std::exception&)
	{
		std::cout << "The node does not exist!\n";
	}
}

void Console::print_outbounds_nodes_of_a_node()
{
	int node;
	std::cout << "node=";
	std::cin >> node;
	try
	{
		std::pair<std::vector<int>::iterator, std::vector<int>::iterator> ans = graph.get_iterator_for_outbounds_of_a_node(node);
		for (std::vector<int>::iterator it = ans.first; it < ans.second; it++)
		{
			std::cout << *it << " ";
		}
		std::cout << "\n";
	}
	catch (const std::exception&)
	{
		std::cout << "The node is not in the list\n";
	}
}

void Console::print_inbounds_nodes_of_a_node()
{
	int node;
	std::cout << "node=";
	std::cin >> node;
	try
	{
		std::pair<std::vector<int>::iterator, std::vector<int>::iterator> ans = graph.get_iterator_for_inbounds_of_a_node(node);
		for (std::vector<int>::iterator it = ans.first; it < ans.second; it++)
		{
			std::cout << *it << " ";
		}
		std::cout << "\n";
	}
	catch (const std::exception&)
	{
		std::cout << "The node is not in the list\n";
	}
}

void Console::change_cost_between_two_nodes()
{
	int node1, node2, value;
	std::cout << "node1=";
	std::cin >> node1;
	std::cout << "node2=";
	std::cin >> node2;
	std::cout << "value=";
	std::cin >> value;
	try
	{
		graph.set_the_cost_between_two_nodes(node1, node2, value);
	}
	catch (const std::exception& except)
	{
		std::cout << except.what();
	}
}

void Console::print_const_between_two_nodes()
{
	int node1, node2;
	std::cout << "node1=";
	std::cin >> node1;
	std::cout << "node2=";
	std::cin >> node2;
	try
	{
		std::cout << "Cost between node " << node1 << " and node " << node2 << " = " << graph.get_the_cost_between_two_nodes(node1, node2) << "\n";
	}
	catch (const std::exception& except)
	{
		std::cout << except.what();
	}
}

void Console::read_graph_from_file()
{
	char file_name[] = "graph1k.txt";
	auto start = std::chrono::high_resolution_clock::now();
	graph.read_graph_from_file(file_name);
	auto stop = std::chrono::high_resolution_clock::now();
	printf("Total time = %d\n", std::chrono::duration_cast<std::chrono::milliseconds>(stop - start).count());
}

void Console::print_graph_to_file(std::string file_name = "output.txt")
{
	std::ofstream file(file_name);
	std::pair<std::vector<int>::iterator, std::vector<int>::iterator> nodes = graph.get_iterator_for_nodes();
	if (nodes.first == nodes.second)
	{
		file << "The graph cannot be build!";
	}
	else {
		file << graph.get_number_of_nodes() << " " << graph.get_the_number_of_edges() << "\n";
		for (std::vector<int>::iterator index = nodes.first; index < nodes.second; index++)
		{
			try
			{
				std::pair<std::vector<int>::iterator, std::vector<int>::iterator> edges = graph.get_iterator_for_outbounds_of_a_node(*index);
				
					for (std::vector<int>::iterator edge = edges.first; edge < edges.second; edge++)
					{
						if (*edge == -1)
						{
							file << *index << " " << *edge << " " << 0 << "\n";
							break;
						}
						file << *index << " " << *edge << " " << graph.get_the_cost_between_two_nodes(*index, *edge) << "\n";
					}

			}
			catch (const std::exception&)
			{
				file << *index << " -1 0" << "\n";
			}

		}
	}
}

void Console::remove_a_node()
{
	int node;
	std::cout << "node=";
	std::cin >> node;
	try
	{
		graph.remove_node(node);
	}
	catch (const std::exception&)
	{
		std::cout << "The node does not exists!\n";
	}
}

void Console::remove_an_edge()
{
	int source, target;
	std::cout << "source=";
	std::cin >> source;
	std::cout << "target=";
	std::cin >> target;
	try
	{
		graph.delete_edge(source, target);
	}
	catch (const std::exception& except)
	{
		std::cout << except.what();
	}
}

void Console::add_a_new_node()
{
	int new_node;
	std::cout << "new node=";
	std::cin >> new_node;
	try
	{
		graph.add_node(new_node);
	}
	catch (const std::exception&)
	{
		std::cout << "Cannot be added\n";
	}
}

void Console::print_number_of_edges()
{
	std::cout << "The number of edges = " << graph.get_the_number_of_edges() << "\n";
}

void Console::print_all_graph()
{
	std::pair<std::vector<int>::iterator, std::vector<int>::iterator> nodes = graph.get_iterator_for_nodes();
	for (std::vector<int>::iterator index = nodes.first; index != nodes.second; index++)
	{
		std::pair<std::vector<int>::iterator, std::vector<int>::iterator> edges = graph.get_iterator_for_outbounds_of_a_node(*index);
		for (std::vector<int>::iterator edge = edges.first; edge != edges.second; edge++)
		{
			std::cout << *index << " " << *edge << " " << graph.get_the_cost_between_two_nodes(*index, *edge) << "\n";
		}
	}
}

void Console::generate_random_graph(std::string file_name)
{
	int vertices, edges;
	char text_file[100];
	std::cout << "vertices=";
	std::cin >> vertices;
	std::cout << "edges=";
	std::cin >> edges;
	try
	{
		graph.build_random_graph(vertices, edges);
		print_graph_to_file(file_name);
	}
	catch (const std::exception& except)
	{
		std::ofstream file(file_name);
		file << except.what();
	}
}
