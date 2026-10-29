import java.util.*;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3){
          
          // parse into 3 separate lists 
          List<Integer> t1 = parse(traversal1);
          List<Integer> t2 = parse(traversal2);
          List<Integer> t3 = parse(traversal3);

          List<Integer> s1 = new ArrayList<>(t1);
          List<Integer> s2 = new ArrayList<>(t2);
          List<Integer> s3 = new ArrayList<>(t3);
          Collections.sort(s1);
          Collections.sort(s2);
          Collections.sort(s3);
          if (!s1.equals(s2) || !s2.equals(s3)){
               return "Invalid traversals";
          }

          // find ascending - inorder 
          List<Integer>[] t = new List[] {t1, t2, t3}; 

          int in = -1; 
          for (int i = 0; i < 3; i++){
               if (isAscending(t[i])){
                    in = i; 
                    break; 
               }
          }

          if (in == -1){
               return "Invalid traversals"; 
          }

          int a = (in + 1) % 3;
          int b = (in + 2) % 3;
          int pre; 
          int post;
          if (matches(t[a], t[b])) {
               pre = a;
               post = b;
          } else if (matches(t[b], t[a])) {
               pre = b; 
               post = a;
          } else {
               return "Invalid traversals";
          }

          String[] labels = new String[3];
          labels[in] = "Inorder";
          labels[pre] = "Preorder";
          labels[post] = "Postorder";
          return "Traversal 1 - " + labels[0] + ", Traversal 2 - " + labels[1] + ", Traversal 3 - " + labels[2];
     }

     static class Node {
          int key;
          Node left, right;
          Node(int key) { this.key = key; }
     }

     static Node insert(Node root, int key) {
          if (root == null) return new Node(key);
          if (key < root.key) root.left = insert(root.left, key);
          else root.right = insert(root.right, key);
          return root;
     }

     static void preorder(Node root, List<Integer> out) {
          if (root == null) return;
          out.add(root.key);
          preorder(root.left, out);
          preorder(root.right, out);
     }

     static void postorder(Node root, List<Integer> out) {
          if (root == null) return;
          postorder(root.left, out);
          postorder(root.right, out);
          out.add(root.key);
     }

     static List<Integer> parse(String s) {
          List<Integer> list = new ArrayList<>();
          for (String part : s.split("-")) list.add(Integer.parseInt(part.trim()));
          return list;
     }

     static boolean isAscending(List<Integer> list) {
          for (int i = 1; i < list.size(); i++){
               if (list.get(i) <= list.get(i - 1)){
                    return false; 
               }
          }
          return true;
     }

     static boolean matches(List<Integer> pre, List<Integer> post) {
          Node root = null;
          for (int k : pre) root = insert(root, k);
          List<Integer> p1 = new ArrayList<>();
          List<Integer> p2 = new ArrayList<>();
          preorder(root, p1);
          postorder(root, p2);
          return p1.equals(pre) && p2.equals(post);
     }
}