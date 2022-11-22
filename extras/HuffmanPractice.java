import java.util.*;
import java.io.*;

class HuffmanNode implements Comparable<HuffmanNode> {

    int count;
    char data;
    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(int count, char data){
        this.count = count;
        this.data = data;
    }

    public int compareTo(HuffmanNode n){
        return this.count - n.count;
    }
}

public class HuffmanPractice {

    static void printCode(HuffmanNode root, StringBuilder sb){
        if(root.left == null && root.right == null && Character.isLetter(root.data)){
            System.out.println(root.data + " -> " + sb);
            return ;
        }
        // left
        sb.append(0);
        printCode(root.left, sb);
        sb.deleteCharAt(sb.length()-1);
        // right
        sb.append(1);
        printCode(root.right, sb);
        sb.deleteCharAt(sb.length()-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().trim().toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for(char item : str){
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (var item : map.entrySet()) {
            pq.add(new HuffmanNode(item.getValue(), item.getKey()));
        }

        // create tree;
        while(pq.size() > 1){
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode root = new HuffmanNode(left.count+right.count, '$');
            root.left = left;
            root.right = right;
            pq.add(root);
        }

        HuffmanNode root = pq.poll();
        System.out.println(map);
        printCode(root, new StringBuilder());
    }
}
