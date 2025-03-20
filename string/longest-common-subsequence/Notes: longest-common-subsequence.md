## [1250. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence)

$\color{yellow}Medium$ **[string]** **[dynamic-programming]**

<p>Given two strings <code>text1</code> and <code>text2</code>, return <em>the length of their longest <strong>common subsequence</strong>. </em>If there is no <strong>common subsequence</strong>, return <code>0</code>.</p>

<p>A <strong>subsequence</strong> of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.</p>

<ul>
	<li>For example, <code>&quot;ace&quot;</code> is a subsequence of <code>&quot;abcde&quot;</code>.</li>
</ul>

<p>A <strong>common subsequence</strong> of two strings is a subsequence that is common to both strings.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> text1 = &quot;abcde&quot;, text2 = &quot;ace&quot; 
<strong>Output:</strong> 3  
<strong>Explanation:</strong> The longest common subsequence is &quot;ace&quot; and its length is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> text1 = &quot;abc&quot;, text2 = &quot;abc&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest common subsequence is &quot;abc&quot; and its length is 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> text1 = &quot;abc&quot;, text2 = &quot;def&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no such common subsequence, so the result is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text1.length, text2.length &lt;= 1000</code></li>
	<li><code>text1</code> and <code>text2</code> consist of only lowercase English characters.</li>
</ul>


-------

## LCS 最长公共子序列

```
for i = 1...m:
	  for j = 1...n:
				if a[i] == b[j]:
						lcs[i][j] = lcs[i-1][j-1] + 1
				else:
					 lcs[i][j] = max(lcs[i-1][j], lcs[i][j-1])
return lcs[m][n]
```

- 空间优化：
	- 计算lcs[i][j]其实只需要保存lcs[i-1][j]，所以空间复杂度可以优化为O(N);
  - 如果我们需要返回这个最长公共子序列，则至少需要O(NM)的空间复杂度来保存每一步的选择从而得到子序列

可以通过在过程中记录每一步的前进方向来得到子序列
或者可以通过比较`lcs[i][j]`和`lcs[i-1][j]`, `lcs[i][j-1]`, `lcs[i-1][j-1]`来判断而不需要额外的存储空间来记录


- 相似问题: 最短编辑距离
