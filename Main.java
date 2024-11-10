package Arvores;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Tree tree = new Tree();

        tree.addNode(10 );
        tree.addNode(5 );
        tree.addNode(6);
        tree.addNode(20);
        tree.addNode(50);
        tree.addNode(15);
        tree.addNode(70);
        tree.addNode(88);

        tree.printTree();
        tree.deleteSubTree(6);
        tree.printTree();
        //System.out.println(tree.findNode(20).valor);


        //tree.printTree();
        //System.out.println(tree.getAltura());

        List<Integer> lista = new ArrayList<>();
        lista.add(4);
        lista.add(2);
        lista.add(3);
        lista.add(1);
        lista.add(9);

        Tree tree1 = new Tree();
        tree1.addList(lista);

        //tree1.printTree();
        //System.out.println(tree1.getNumNo());

        List<Integer> listReturn = tree.returnList();

        //System.out.println(listReturn);

    }

}
