#pragma once
#include <vector>
#include <map>
#include <exception>
#include <fstream>
#include <string>
#include <iostream>
class Graph
{
public:
	Graph();
	Graph(const Graph& graph);
	void add_edge(int node_from, int node_to, int cost);
	void add_node(int node);
	void delete_edge(int node_from, int node_to);
	bool is_edge(int node_from, int node_to);
	int get_out_degree_of_node(int node);
	int get_in_degree_of_node(int node);
	std::vector<int>nodes;
	int get_number_of_nodes();
	void remove_node(int node);
	int get_the_cost_between_two_nodes(int node_from, int node_to);
	void set_the_cost_between_two_nodes(int node_from, int node_to, int new_value);
	std::pair<std::vector<int>::iterator, std::vector<int>::iterator> get_iterator_for_nodes();
	std::pair<std::vector<int>::iterator, std::vector<int>::iterator> get_iterator_for_outbounds_of_a_node(int node);
	std::pair<std::vector<int>::iterator, std::vector<int>::iterator> get_iterator_for_inbounds_of_a_node(int node);
	void read_graph_from_file(char file_name[]);
	int get_the_number_of_edges();
	int get_the_number_of_deleted_nodes();
	bool check_if_node_exists(int node);
	void build_random_graph(int vertices, int edges);

private:
	std::map<int, std::vector<int>> edges_in;
	std::map<int, std::vector<int>> edges_out;
	std::map<std::pair<int, int>, int > costs;
	int number_of_nodes;
	int number_of_edges;
	int deleted_nodes;

};

