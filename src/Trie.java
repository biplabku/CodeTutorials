import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Trie {

    public TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words){
        if(words.length == 0) {
            return new ArrayList<>();
        }
        List<String> input = new ArrayList<>();
        for(int i = 0; i < words.length; i++) {
            String str = words[i];
            addChildren(str);
            input.add(str);
        }
        List<String> list = new ArrayList<>();
        // now read through all the words and chech if there is a continuation of the words
        for(String word: words) {
            List<String> l = doSearchNode(root, word, input);
            boolean notSeen = false;
            if(l.size() > 1) {
                for(String str : l) {
                    if(!input.contains(str)) {
                        notSeen = true;
                    }
                }
                if(!notSeen) {
                    list.add(word);
                }
            }
        }

        return list;
    }

    public List<String> doSearchNode(TrieNode root, String word, List<String> input){
        HashMap<Character, TrieNode> children = root.children;
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            sb.append(ch);
            TrieNode node = children.get(ch);
            children = node.children;
            if(node.isEnd && input.contains(sb.toString())) {
                list.add(sb.toString());
                sb.setLength(0);
            }
        }
        return list;
    }

    public void addChildren(String str){
        HashMap<Character, TrieNode> children = root.getChildren();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            TrieNode node;
            if(!children.containsKey(ch)) {
                node = new TrieNode(ch);
                children.put(ch, node);
            }else{
                node = children.get(ch);
            }
            children = node.children;
            if(i == str.length() - 1) {
                node.isEnd = true;
            }
        }
    }

    public static void main(String[] args) {
        Trie ts = new Trie();
        String[] words = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        System.out.println(ts.findAllConcatenatedWordsInADict(words));
    }
}
