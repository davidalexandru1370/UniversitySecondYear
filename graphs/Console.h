#include <iostream>
#include <fstream>
#include <vector>
#include "Graph.h"
#pragma once
class Console
{
public:
	Console(Graph _graph);
	void print_menu();
	void run_console();

private:
	void print_all_the_nodes();
	void add_a_new_edge_between_two_nodes();
	void print_if_is_edge_between_two_nodes();
	void print_the_out_degree_of_a_node();
	void print_the_in_degree_of_a_node();
	void print_outbounds_nodes_of_a_node();
	void print_inbounds_nodes_of_a_node();
	void change_cost_between_two_nodes();
	void print_const_between_two_nodes();
	void read_graph_from_file();
	void print_graph_to_file(std::string file_name);
	void remove_a_node();
	void remove_an_edge();
	void add_a_new_node();
	void print_number_of_edges();
	Graph graph;
	void print_all_graph();
	void generate_random_graph(std::string file_name);
};

