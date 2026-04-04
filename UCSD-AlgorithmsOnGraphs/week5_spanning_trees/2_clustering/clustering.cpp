#include <algorithm>
#include <iostream>
#include <iomanip>
#include <cassert>
#include <vector>
#include <cmath>
#include <limits>

using std::vector;
using std::pair;



double clustering(vector<int> x, vector<int> y, int k) {
  //write your code here
  int n = x.size();
  vector<double> dist(n, std::numeric_limits<double>::max());
  vector<bool> visited(n, false);
  dist[0] = 0.0;
  for (int i = 0; i < n; i++) {
    int u = -1;
    for (int j = 0; j < n; j++) {
      if (!visited[j] && (u == -1 || dist[j] < dist[u])) {
        u = j;
      }
    }
    visited[u] = true;
    if (i == n-k+1) {
      return std::sqrt(dist[u]);
    }
    for (int v = 0; v < n; v++) {
      if (!visited[v]) {
        double dx = x[u] - x[v];
        double dy = y[u] - y[v];
        double w = dx * dx + dy * dy;
        if (w < dist[v]) {          
          dist[v] = w;
        }
      }
    }
  } 
  return -1.;
} 

int main() {
  size_t n;
  int k;
  std::cin >> n;
  vector<int> x(n), y(n);
  for (size_t i = 0; i < n; i++) {
    std::cin >> x[i] >> y[i];
  }
  std::cin >> k;
  std::cout << std::setprecision(10) << clustering(x, y, k) << std::endl;
}
