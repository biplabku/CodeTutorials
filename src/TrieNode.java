import java.util.HashMap;

public class TrieNode {
    char ch;
    boolean isEnd;
    HashMap<Character, TrieNode> children;

    public TrieNode(){

    }

    public TrieNode(char ch){
        this.ch = ch;
    }

    public TrieNode(char ch, boolean isEnd, HashMap<Character, TrieNode> children){
        this.ch = ch;
        this.isEnd = isEnd;
        this.children = children;
    }
}
