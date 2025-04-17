## [2090. Number of Ways to Arrive at Destination](https://leetcode.com/problems/number-of-ways-to-arrive-at-destination)

$\color{yellow}Medium$ **[dynamic-programming]** **[graph]** **[topological-sort]** **[shortest-path]**

<p>You are in a city that consists of <code>n</code> intersections numbered from <code>0</code> to <code>n - 1</code> with <strong>bi-directional</strong> roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.</p>

<p>You are given an integer <code>n</code> and a 2D integer array <code>roads</code> where <code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code> means that there is a road between intersections <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> that takes <code>time<sub>i</sub></code> minutes to travel. You want to know in how many ways you can travel from intersection <code>0</code> to intersection <code>n - 1</code> in the <strong>shortest amount of time</strong>.</p>

<p>Return <em>the <strong>number of ways</strong> you can arrive at your destination in the <strong>shortest amount of time</strong></em>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2025/02/14/1976_corrected.png" style="width: 255px; height: 400px;" />
<pre>
<strong>Input:</strong> n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, roads = [[1,0,10]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>n - 1 &lt;= roads.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li>There is at most one road connecting any two intersections.</li>
	<li>You can reach any intersection from any other intersection.</li>
</ul>


-------

## 最短路径

对每个结点维持属性`v.d`用来记录从源结点s到v的最短路径上界，初始化为正无穷; 用`v.p`记录在路径中的前驱结点，初始化为`nil`

松弛操作 - 对一条权重为w(u, v)的边(u,v)的松弛relax(u, v, w)过程为：
```
if v.d > u.d + w(u,v):
	v.d = u.d + w(u, v)
	v.p = u
```

### Bellman-Ford
一般情况下的单源最短路径，通过对所有的边进行|V|-1次松弛操作，得到源点s到图中所有其他节点的最短路径

```
boolean shortestPaths(E, V, w, s):
	for each v in V-s:
		v.d = INF
		v.p = nil

	s.d = 0

	for i = 1...|V|-1:
		for each e in E:
			relax(u, v, w)
	for each e in E:
		if v.d > u.d + w(u, v):
			return false // 图中有权重为负的环，该问题无解
	return true // 图中不存在权重为负值的环
```

### DAG
有向无环图中的单源最短路径，首先对所有结点进行拓扑排序，接着按照拓扑序遍历结点并对每个结点的所有边进行一次松弛

```
void dag(E, V, w, s):
	for each v in V-s:
		v.d = INF
		v.p = nil
	s.d = 0

	sortedV = topologySort(E, V, s)

	for each u in sortedV:
		for each v connected to u:
			relax(u, v, w)
```

### Dijkstra
所有边权重非负的有向图的单源最短路径。维持一个结点集合S，重复从V-S中寻找v.d最小的node加入S，并对所有从v出发的边进行松弛
算法的复杂度取决于如何不停地在更新后寻找v.d最小的结点，因此可以运用优先队列

```
void dijkstra(E, V, w, s):
	for each v in V-s:
		v.d = INF
		v.p = nil
	S = []
	Q = V // priority queue
	while not Q.empty:
		u = Q.pop();
		append(S, u)
		for each v connected to u:
			relax(u, v, w)
```