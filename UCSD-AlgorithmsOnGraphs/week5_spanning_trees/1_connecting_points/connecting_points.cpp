#include <algorithm>
#include <iostream>
#include <iomanip>
#include <vector>
#include <cmath>
#include <limits>

using std::vector;

double minimum_distance(vector<int> x, vector<int> y) {
  double result = 0.;
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
    result += std::sqrt(dist[u]);
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
  return result;
} 

int main() {
  size_t n;
  std::cin >> n;
  vector<int> x(n), y(n);
  for (size_t i = 0; i < n; i++) {
    std::cin >> x[i] >> y[i];
  }
  std::cout << std::setprecision(10) << minimum_distance(x, y) << std::endl;
}
