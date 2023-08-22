package es.uned.lsi.eped.pract2022_2023;

//import ...

import es.uned.lsi.eped.DataStructures.*;


public class StockTree implements StockIF {

    protected static GTreeIF<Node> stock; /* El stock es un árbol general de nodos */

    /* Constructor de la clase */
    public StockTree() {
        this.stock = new GTree<Node>();
    }

    @Override
    public void updateStock(String p, int u) {
        int n = p.length();
        //Determina el target tree como el stock
        GTreeIF<Node> rootTree = stock;

        // Itera sobre el string p
        for (int i = 0; i < n; i++) {

            char c = p.charAt(i);
            boolean found = false;

            //Si el target tree no tiene hijos, crea un nuevo nodo y un nuevo árbol
            if (rootTree.getNumChildren() == 0) {
                NodeInner node = new NodeInner(c);
                GTreeIF<Node> child = new GTree<>();
                child.setRoot(node);
                rootTree.addChild(rootTree.getNumChildren() + 1, child);
                // Target tree pasa a ser el nuevo árbol
                rootTree = child;
            }else{
                //Si tiene hijos, itera sobre ellos
                IteratorIF<GTreeIF<Node>> it = rootTree.getChildren().iterator();
                while (it.hasNext() && !found) {
                    GTreeIF<Node> child = it.getNext();
                    //Si el hijo es un nodo interno y su letra coincide con el char c
                    //  found = true
                    //  target tree pasa a ser el hijo
                    if (child.getRoot() instanceof NodeInner inner && inner.getLetter() == c) {
                        found = true;
                        rootTree = child;
                    }
                }
                //Si no se ha encontrado el char c en los hijos
                //  se crea un nuevo nodo y un nuevo árbol
                //  target tree pasa a ser el nuevo árbol
                if (!found) {
                    NodeInner node = new NodeInner(c);
                    GTreeIF<Node> child = new GTree<>();
                    child.setRoot(node);
                    rootTree.addChild(rootTree.getNumChildren() + 1, child);
                    rootTree = child;
                }
            }
        }
        //Si el target tree no tiene hijos, se crea un nuevo nodo INFO y se añade al árbol
        //Si tiene hijos, se modifica el valor del nodo INFO
        if (rootTree.getNumChildren() == 0) {
            NodeInfo node = new NodeInfo(u);
            GTreeIF<Node> TreeInfo = new GTree<>();
            TreeInfo.setRoot(node);
            rootTree.addChild(rootTree.getNumChildren() + 1, TreeInfo);
        } else {
            //Buscamos el Nodo INFO
            IteratorIF<GTreeIF<Node>> it = rootTree.getChildren().iterator();
            boolean found = false;
            while (it.hasNext() && !found) {
                GTreeIF<Node> child = it.getNext();
                if (child.getRoot() instanceof NodeInfo nodeInfo) {
                    found = true;
                    //Modificamos el valor del nodo INFO
                    int unidades = nodeInfo.getUnidades();
                    nodeInfo.setUnidades(unidades + u);
                }
            }
        }
    }


    @Override
    public int retrieveStock(String p) {
        return 0;
    }

    @Override
    public SequenceIF<StockPair> listStock(String prefix) {
        return null;
    }




}