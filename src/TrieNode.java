import java.util.HashMap;

public class TrieNode {
    char ch;
    boolean isEnd;
    HashMap<Character, TrieNode> children = new HashMap<>();

    public TrieNode(){

    }

    public HashMap<Character, TrieNode> getChildren(){
        return children;
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
