package org.General;

class TrieNode {
    private TrieNode[] children;
    private boolean isEndOfWord;

    public TrieNode() {
        this.children = new TrieNode[26]; // Assuming only lowercase English letters
        this.isEndOfWord = false;
    }

    public TrieNode getChild(char ch) {
        return children[ch - 'a'];
    }

    public void setChild(char ch, TrieNode node) {
        children[ch - 'a'] = node;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (current.getChild(ch) == null) {
                current.setChild(ch, new TrieNode());
            }
            current = current.getChild(ch);
        }
        current.setEndOfWord(true);
    }

    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord();
    }

    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    private TrieNode searchNode(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.getChild(ch);
            if (current == null) {
                return null; // Character not found in the Trie
            }
        }
        return current;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        // Inserting words
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apricot");
        trie.insert("banana");

        // Searching for words
        System.out.println(trie.search("apple"));    // true
        System.out.println(trie.search("app"));      // true
        System.out.println(trie.search("apricot"));   // true
        System.out.println(trie.search("banana"));    // true
        System.out.println(trie.search("orange"));    // false

        // Checking prefixes
        System.out.println(trie.startsWith("app"));   // true
        System.out.println(trie.startsWith("ora"));   // false
    }
}
