class Solution {
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        List<String> res = new ArrayList<>();
        backtrack(res, s, 0);
        return this.ans;
    }

    private void backtrack(List<String> res, String s, int l) {
        if (l == s.length()) {
            List<String> newAns = new ArrayList<>();
            newAns.addAll(res);
            this.ans.add(newAns);
            return;
        }
        for (int r = l; r < s.length(); r++) {
            if (isValid(s, l, r)) {
                res.add(s.substring(l, r + 1));
                backtrack(res, s, r+1);
                res.removeLast();
            }
        }
    }

    private boolean isValid(String s, int l, int r) {
        while (r >= l && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }
        return r <= l;
    }
}