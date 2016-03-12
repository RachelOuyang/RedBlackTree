/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktreetesterproject;

import java.util.Scanner;
import java.io.*;

/**
 * a spell checker that is based on a red black tree.
 * @author ruijieouyang
 * @version 1.0
 * @since 10/05/2015
 */
public class RedBlackTreeTesterProject {

    /**
     * pre: user would follow the format of command requirements.
     * post: allow user to spell check text files.
     * @param args[0] - The file name of the word list (to be used as a
     * dictionary). The main method interacts with a user at the command line. A
     * menu of commands is provided as well as some computations regarding the
     * size of the word list used as a dictionary. The dictionary is case
     * sensitive. That is, "Pittsburgh" does not equal "pittsburgh". The
     * commands entered by the user are NOT case sensitive. That is, "p" == "P".
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        //ask users to input text file name
        System.out.println("Please input file name");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        File file = new File(fileName);

        //scan file and input words in every line into red black tree
        RedBlackTree rbt = new RedBlackTree();
        Scanner fileRead = new Scanner(file);
        while (fileRead.hasNext()) {
            String word = fileRead.next();
            rbt.insert(word);
            //System.out.println(word);
        }

        //output information as follows:
        System.out.println("Red Black Tree is loaded with " + rbt.getSize() + " words.");
        System.out.println("The height of the tree is " + rbt.height());
        System.out.println("2 * Lg( n+1) = " + 2 * (Math.log(rbt.getSize() + 1) / Math.log(2)));
        System.out.println();
        System.out.println("Legal commands are: ");
        System.out.println("<p>  display the entire word tree with a level order traversal.");
        System.out.println("<s>  print the words of the tree in sorted order (use an inorder traversal).");
        System.out.println("<r>  print the words of the tree in reverse sorted order.");
        System.out.println("<!>  to quit.");
        System.out.println("<c>  <word> to spell check this word");
        System.out.println("<a>  <word> add word to tree.");
        System.out.println("<f>  <fileName> to check a text file for spelling errors.");
        System.out.print(">");

        //get the results of above commands
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            String word = "";
            if (line.length() > 2) {
                word = line.substring(2);//in case of out of index 
            }
            //command is not case sensitive. 'a' command is the same as the 'A' command
            char command = line.toLowerCase().charAt(0);

            if (command == 'c') {
                if (rbt.contains(word)) {
                    System.out.println("Found " + word + " after " + rbt.getRecentCompares() + " comparisons");
                } else {
                    System.out.println(word + " Not in dictionary. Perhaps you mean\n" + rbt.closeBy(word));
                }
            } else if (command == 'a') {
                if (rbt.contains(word)) {
                    System.out.println("The word '" + word + "' is already in the dictionary");
                } else {
                    rbt.insert(word);
                    System.out.println(word + " was added to dictionary.");
                }

            } else if (command == 'p') {
                rbt.levelOrderTraversal();
            } else if (command == 's') {
                rbt.inOrderTraversal();
            } else if (command == 'r') {
                rbt.reverseOrderTraversal();
            } else if (command == '!') {
                System.out.println("Bye");
                break;
            } else if (command == 'f') {
                File comparedFile = new File(word);
                Scanner cmpRead = new Scanner(comparedFile);
                boolean flag = true;
                while (cmpRead.hasNext()) {
                    String newWord = cmpRead.next();
                    if (newWord.contains(".")) {
                        newWord = newWord.substring(0, newWord.indexOf("."));
                    }
                    if (!rbt.contains(newWord)) {
                        flag = false;
                        System.out.println("'" + newWord + "'" + " was not found in dictionary.");
                    }
                }
                if (flag) {
                    System.out.println("No spelling errors found.");
                }
            }
            System.out.print(">");
        }

    }

}
