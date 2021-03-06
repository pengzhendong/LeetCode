## 单源最短路径 Dijkstra 算法

图的表示有邻接矩阵和邻接表，稠密图用邻接矩阵比较方便，假设有 V 个顶点，则图的空间复杂度为 O(V^2)。
稀疏图用邻接表更好，假设有 E 条边，则图的空间复杂度为 O(E)。

每个顶点的度都为 1 的情况下有 `E=V-1~V`；在完全图中，`E=V*(V-1)/2~V^2`。

基于邻接矩阵的 Dijkstra 算法的时间复杂度为 `O(V*(V+V))=O(V^2)`。
基于邻接表的 Dijkstra 算法，由于每次都要找一个到单源最近的点，因此时间复杂度也为 `O(V*(V+X))=O(V^2)` （X 为到单源最近的点的度，值小于 V）。

### 优先队列优化

由于每次都要找一个到单源最近的点，在某些情况下 Dijkstra 算法可以使用优先队列进行优化。优先队列的实现有以下几种：

|            | 链表 | 二叉堆 | 二项式堆 | 斐波那契堆 |
| ---------- | ---- | ------ | -------- | ---------- |
| 插入       | 1    | logn   | logn     | 1          |
| 弹出且维护 | n    | logn   | logn     | logn       |

Java 中 PriorityQueue 通过二叉小顶堆实现，因此插入和弹出的时间复杂度都是 O(logn)。通常稀疏图才能用优先队列优化，因此图都是用邻接表表示。

* 邻接表 + 优先队列的时间复杂度为 O((V + E)logV)
  * 最好情况下为 `O((V + E)logV) = O(ElogV) = O(VlogV)`
  * 最坏情况下为 `O((V + E)logV) = O(ElogV) = O(V^2 * logV)`
* 邻接表 + 斐波那契堆的时间复杂度为 O(E + VlogV)
  * 最好情况下为 `O(E + VlogV) = O(V + VlogV) = O(VlogV)`
  * 最坏情况下为 `O(E + VlogV) = O(V^2 + VlogV) = O(V^2)`

综上所述，Java 中使用优先队列实现对 Dijkstra 算法的优化，在稠密图中未必能带来更好的效果，甚至更差。如果使用斐波那契堆，最差也只是和不优化一样。

每次从优先队列中挑选最小的并更新周边顶点，算法导论将其归类为贪心算法，也就是动态规划的特例。