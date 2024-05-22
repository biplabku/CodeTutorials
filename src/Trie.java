import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {

    public TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words){
        if(words.length == 0) {
            return new ArrayList<>();
        }
        for(int i = 0; i < words.length; i++) {
            String str = words[i];
            addChildren(str);
        }
        return new ArrayList<>();
    }

    public void addChildren(String str){
        HashMap<Character, TrieNode> children = root.children;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            TrieNode node;
            if(!children.containsKey(ch)) {
                node = new TrieNode(ch);
                children.put(ch, node);
            }else{
                node = children.get(ch);
            }
            if(i == str.length() - 1) {
                node.isEnd = true;
            }
        }
    }

    public static void main(String[] args) {
        Trie ts = new Trie();
        String[] words = new String[]{"cat", "dog", "catdog"};
        System.out.println(ts.findAllConcatenatedWordsInADict(words));
    }
}
