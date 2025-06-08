
import java.nio.charset.Charset;

class WordDictionary {

    class TrieNode {
        private TrieNode[] children;
        private boolean _isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        }

        public void addWord(String word) {
            TrieNode node = this;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node._isEnd = true;
        }

        public boolean find(String word) {
            TrieNode node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c == '.') {
                    for (int j = 0; j < 26; j++) {
                        char[] subWord = word.substring(i).toCharArray();
                        subWord[0] = (char)((int)'a'+j);
                        if (node.find(new String(subWord)))
                            return true;
                    }
                    return false;
                } else {
                    if (node.children[c - 'a'] == null) {
                        return false;
                    }
                    node = node.children[c - 'a'];
                }
            }
            return node._isEnd;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        root.addWord(word);
    }

    public boolean search(String word) {
        return root.find(word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */