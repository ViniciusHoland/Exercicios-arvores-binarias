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
            novoNode.pai = null;
            return this.raiz = novoNode;
        } else if (esqOuDir) {
            if(no.dir == null){
                novoNode.pai = no;
                return no.dir = novoNode;
            }else{
                novoNode.pai = no;
                return no.esq = novoNode;
            }

        } else {
            if(no.esq == null) {
                novoNode.pai = no;
                return no.esq = novoNode;
            }else{
                novoNode.pai = no;
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


        if(nodeRemovido == null){
            System.out.println("Não foi localizado nenhum node");
            return null;
        }

        Node nodePai = nodeRemovido.pai;
        if(nodePai == null){
            System.out.println("A arvore so tem um Node");
            return nodeRemovido;
        }

        if(nodeRemovido.dir == null && nodeRemovido.esq == null){
            if(nodePai == null){
                this.raiz = null;
            }
            else if(nodePai.esq == nodeRemovido){
                nodePai.esq = null;
            }else {
                nodePai.dir = null;
            }
        }

        else if(nodeRemovido.esq == null || nodeRemovido.dir == null){
            // esse teste  vai retornar o filho do Nó a ser removido que logo vai se ligar ao Pai do node removido
            Node nodeFilho = (nodeRemovido.esq != null) ? nodeRemovido.esq : nodeRemovido.dir;

            if(nodePai == null){
                this.raiz = nodeFilho;
            }
            else if(nodePai.dir == nodeRemovido){
                nodePai.dir = nodeFilho;
            } else {
                nodePai.esq = nodeFilho;
            }
            nodeFilho.pai = nodePai;
        }

        else{
            Node sucessor = nodeRemovido.dir;
            while (sucessor.esq != null) {
                sucessor = sucessor.esq;
            }

            int valorSucessor = sucessor.valor; // Armazena o valor do sucessor
            removeNode(sucessor.valor); // Remove o sucessor (caso simples de remoção)
            nodeRemovido.valor = valorSucessor; // Substitui o valor do nó removido pelo do sucessor

        }

        return nodeRemovido;

    }


    public Node deleteSubTree(int valor){
        Node findNode = findNode(valor);
        if(findNode == null){
            return null;
        }
        return this.deleteSub(findNode);
    }

    private Node deleteSub(Node node){

        if(node.dir != null && node.esq != null){
            node.esq = null;
            node.dir = null;
        } else  if(node.dir != null || node.esq != null){
            node.esq = null;
            node.dir = null;
        }
        return node;
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

    public void concatenaArvore(Tree tree){
        this.contatenaArvore(tree,this.localizaNoIncompletoRecursivo(this.getRaiz()));
    }

    private void contatenaArvore(Tree tree, Node nodeParaConcatenar){
        if(nodeParaConcatenar.dir == null){
            nodeParaConcatenar.dir = tree.getRaiz();
        }else {
            nodeParaConcatenar.esq = tree.getRaiz();
        }
    }


    private boolean getRandom() {
        Random random = new Random();
        return random.nextBoolean();
    }


    public void printTree() {
        printTree(this.getRaiz(), "", false);
    }

    //** Metodo feito pelo CHATGPT para que eu pudesse ir testando de forma ilustrativa como ficava a arvore **
    private void printTree(Node node, String prefix, boolean isRight) {
        if (node != null) {
            // Mostra o prefixo e seta se é filho direito ou esquerdo
            System.out.println(prefix + (isRight ? "├── " : "└── ") + node.valor);

            // Constrói novo prefixo para os filhos do nó atual
            String childPrefix = prefix + (isRight ? "│   " : "    ");

            // Imprime primeiro o lado direito e depois o esquerdo para a visualização ficar invertida (direita no topo)
            printTree(node.dir, childPrefix, true);
            printTree(node.esq, childPrefix, false);
        }
    }



}
