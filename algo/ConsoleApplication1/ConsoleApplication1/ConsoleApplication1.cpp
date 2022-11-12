//#include "pch.h"
#include <iostream>
#include <algorithm>
#include <fstream>
#include <vector>
#include <climits>
#include <queue>
#define ll long long int
using namespace std;
ifstream f("in.txt");
ofstream o("o.txt");
#define nr 1<<30
vector<pair<int, int>>v[10005];
int n, m, p, l, k;
int x, y, t;
const int MaxN = 10010;
const int inf = 1e9 + 10;
struct elems {
	int source, teleports, lenght, cost;
	bool operator<(const elems& aux) const {
		return cost > aux.cost;
	}
};
priority_queue<elems> coada;
int dp[MaxN][11][11];
bool incoada[10005];
vector<int>d(10005, nr);

void update(int source, int teleports, int length, int cost) {
	if (dp[source][teleports][length] > cost) {
		dp[source][teleports][length] = cost;
		coada.push({ source,teleports,length,cost });
	}
}


void dijkstra()
{
	for (int i = 1; i <= n; i++)
	{
		for (size_t j = 0; j <= k; j++)
		{
			for (size_t t = 0; t <= l; t++)
			{
				dp[i][j][t] = 1e9 + 2;
			}
		}
	}
	dp[1][0][0] = 0;
	coada.push({ 1,0,0,0 });
	while (!coada.empty())
	{
		auto nod = coada.top();
		coada.pop();
		int source = nod.source;
		int teleports = nod.teleports;
		int length = nod.lenght;
		int cost = nod.cost;
		
		/*if (dp[source][teleports][length] != cost)
		{
			continue;
		}*/

		if (teleports < k)
		{
			int cost1 = cost;
			if (length > 0)
			{
				cost1 += p;
			}
			update(source, teleports + 1, 0, cost1);
		}

		for (auto e : v[source]) {
			int node = e.first;
			if (length < l) {
				update(node, teleports, length + 1, cost);
			}
			if (length == 0) {
				int cost1 = cost + e.second;
				update(node, teleports, 0, cost1);
			}
		}
	}

}



int main()
{
	f >> n >> m >> p >> l >> k;
	for (size_t i = 1; i <= m; i++)
	{
		f >> x >> y >> t;
		v[x].push_back(make_pair(y, t));
		v[y].push_back(make_pair(x, t));
	}
	dijkstra();
	cout << dp[n][k][0];

}