package Arvores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree {

    Node raiz;

    public Tree() {

    }

    public Node addNode(int valor){
        return this.addNode(this.getRandom(), valor, this.localizaIncompleto());
    }

    private Node addNode(boolean esqOuDir, int valor, Node no) {

        Node novoNode = new Node(valor);

        if (no == null) {
            return this.raiz = novoNode;
        } else if (esqOuDir) {
            if(no.dir == null){
                return no.dir = novoNode;
            }else{
                return no.esq = novoNode;
            }

        } else {
            if(no.esq == null) {
                return no.esq = novoNode;
            }else{
                return no.dir = novoNode;
            }
        }
    }

    public Node getRaiz() {
        return this.raiz;
    }

    public Node localizaIncompleto() {
        return localizaNoIncompletoRecursivo( this.getRaiz());
    }

    private Node localizaNoIncompletoRecursivo(Node node) {

        if (node == null) {
            return null;
        }

        if(this.getRandom()){
            if(node.dir == null){
                return node;
            } else if(node.esq == null){
                return node;
            } else {
                return localizaNoIncompletoRecursivo(node.dir);
            }
        }else {
            if(node.esq == null){
                return node;
            } else if(node.dir == null){
                return node;
            } else {
                return localizaNoIncompletoRecursivo(node.esq);
            }
        }
    }

    public int getAllFolha(){
        return this.getFolha(this.getRaiz());
    }


    private int getFolha(Node raiz){
        if(raiz == null){
            return 0;
        }
        if(raiz.esq == null && raiz.dir == null){
            return 1;
        }

        return getFolha(raiz.dir) + getFolha(raiz.esq);

    }

    public Node removeNode(int valor){

        Node nodeRemovido = this.findNode(valor);

        return nodeRemovido;

    }

    public Node findNode(int valor){
        return this.findNode(this.getRaiz(), valor);
    }

    private Node findNode(Node node, int valor){
        if(node == null){
            return null;
        }
        if(node.valor == valor){
            return node;
        }

        // vai tentar localizar na esquerda
        Node findNodeEsq = findNode(node.esq,valor);
        if(findNodeEsq != null){
            return findNodeEsq;
        }

        // depois de percorrer toda arvore esquerda, vai tentar na direita
        return findNode(node.dir, valor);
    }

    public List<Integer> returnList() {
        List<Integer> newList = new ArrayList<>();
        return this.percorreRecursivo(this.getRaiz(), newList);
    }

    private List<Integer> percorreRecursivo(Node node, List<Integer> list) {
        if (node != null) {
            percorreRecursivo(node.esq, list);
            list.add(node.valor);
            percorreRecursivo(node.dir, list);
        }
        return list;
    }

    public int getNumNo() {
        return this.numNo(this.getRaiz());
    }

    private int numNo(Node raiz) {
        if (raiz != null) {
            int alturaEsquerda = alturaTree(raiz.esq);
            int alturaDireita = alturaTree(raiz.dir);
            return alturaEsquerda + alturaDireita + 1;
        } else {
            return 0;
        }
    }

    public int getAltura() {
        return this.alturaTree(this.getRaiz());
    }

    private int alturaTree(Node raiz) {
        if (raiz != null) {
            int alturaEsquerda = alturaTree(raiz.esq);
            int alturaDireita = alturaTree(raiz.dir);
            return Math.max(alturaEsquerda, alturaDireita) + 1;
            // compara a Direita e Esquerda e retorna o maior, depois soma o nó atual
        } else {
            return 0;
        }
    }

    public void addList(List<Integer> list) {
        for (Integer valorList : list) {
            this.addNode(this.getRandom(), valorList, this.localizaIncompleto());
        }
    }

    private boolean getRandom() {
        Random random = new Random();
        return random.nextBoolean();
    }


    public void printTree() {
        printTree(this.getRaiz(), 0);
    }

    //** Metodo feito pelo CHATGPT para que eu pudesse ir testando de forma ilustrativa como ficava a arvore **
    private void printTree(Node node, int depth) {
        if (node == null) {
            return;
        }

        // Primeiro, imprime o lado direito (aumentando a profundidade)
        printTree(node.dir, depth + 1);

        // Imprime o nó atual com indentação baseada na profundidade
        System.out.println(" ".repeat(depth * 4) + "└── " + node.valor);

        // Depois, imprime o lado esquerdo
        printTree(node.esq, depth + 1);
    }


}
