#include <iostream>
#include <fstream>
#include <stack>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;
ifstream f("ctc.in");
ofstream o("ctc.out");
#define NMAX 100005
#define unvisited -1

vector<int>graph[NMAX];
int visited[NMAX] = { unvisited }, lowlink[NMAX], inStack[NMAX];
vector<vector<int>>result;
stack<int>connectedComponent;
int index;

void tarjan(int nod) {
	visited[nod] = lowlink[nod] = index;
	index++;

	inStack[nod] = 1;
	connectedComponent.push(nod);
	for (vector<int>::iterator it = graph[nod].begin(); it != graph[nod].end(); it++) {
		if (visited[*it] == unvisited) {
			tarjan(*it);
			lowlink[nod] = min(lowlink[nod], lowlink[*it]);
		}
		else if (inStack[*it] != 0) {
			lowlink[nod] = min(lowlink[nod], lowlink[*it]);
		}
	}
	if (visited[nod] == lowlink[nod]) {
		vector<int> solution;
		int element;
		do {
			element = connectedComponent.top();
			connectedComponent.pop();
			solution.push_back(element);
			inStack[element] = 0;
		} while (element != nod);
		result.push_back(solution);
	}
}

void printResult() {
	o << result.size() << "\n";
	for (int line = 0; line < result.size(); line++) {
		for (int column = 0; column < result[line].size(); column++) {
			o << result[line][column] + 1 << " ";
		}
		o << "\n";
	}
}

int main()
{
	int vertices, edges;
	f >> vertices >> edges;
	for (int it = 0; it < edges; it++) {
		int start, end;
		f >> start >> end;
		graph[start - 1].push_back(end - 1);
	}
	fill(visited, visited + NMAX, -1);
	for (int nod = 0; nod < vertices; nod++) {
		if (visited[nod] == unvisited) {
			tarjan(nod);
		}
	}

	printResult();

	return 0;
}

