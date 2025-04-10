## [2461. Amount of Time for Binary Tree to Be Infected](https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected)

$\color{yellow}Medium$ **[hash-table]** **[tree]** **[depth-first-search]** **[breadth-first-search]** **[binary-tree]**

<p>You are given the <code>root</code> of a binary tree with <strong>unique</strong> values, and an integer <code>start</code>. At minute <code>0</code>, an <strong>infection</strong> starts from the node with value <code>start</code>.</p>

<p>Each minute, a node becomes infected if:</p>

<ul>
	<li>The node is currently uninfected.</li>
	<li>The node is adjacent to an infected node.</li>
</ul>

<p>Return <em>the number of minutes needed for the entire tree to be infected.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/06/25/image-20220625231744-1.png" style="width: 400px; height: 306px;" />
<pre>
<strong>Input:</strong> root = [1,5,3,null,4,10,6,9,2], start = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/06/25/image-20220625231812-2.png" style="width: 75px; height: 66px;" />
<pre>
<strong>Input:</strong> root = [1], start = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> At minute 0, the only node in the tree is infected so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>Each node has a <strong>unique</strong> value.</li>
	<li>A node with a value of <code>start</code> exists in the tree.</li>
</ul>


-------

### Algorithm
1. Declare a variable `maxDistance` to store maximum distance from the start node.
2. Define a function `traverse` that performs a depth-first search of the tree that returns depth and calculates and saves `maxDistance`.
  - For each call to traverse, we have a new root and declare a variable depth = 0.
  - If root == null set depth = 0 and return.
  - Recursively call traverse with root.right and save in the variable rightDepth.
  - Recursively call traverse with root.left and save in the variable leftDepth.
  - If root = start the root is the start node:
    - Set maxDistance = max(leftDepth, rightDepth) to calcualte the start node's max depth.
    - Set depth = -1 to signify this is the start node.
  - If the leftDepth and rightDepth are both greater than or equal to 0, the start node is not in this subtree:
    - Set depth = max(leftDepth, rightDepth) + 1 to calculate the current root's max depth.
  - Else, the current root's subtree contains the start node:
    - Define a variable distance as the sum of abs(leftDepth) and abs(rightDepth), which is the distance of the furthest node in the other subtree.
    - Set maxDistance = max(maxDistance, distance) to update maxDistance if distance is larger.
    - Set depth = min(leftDepth, rightDepth) - 1 to calculate a negative number that signifies the subtree contains the start node and represents the distance of the start node from the root.
  - return depth.
3. Call traverse(root, start).
4. Return maxDistance.