package es.uned.lsi.eped.pract2022_2023;

//import ...

import es.uned.lsi.eped.DataStructures.*;


public class StockTree implements StockIF {

    public GTreeIF<Node> stock; /* El stock es un árbol general de nodos */

    /* Constructor de la clase */
    public StockTree() {
        this.stock = new GTree<Node>();
    }

    public static List<StockPair> preorderIterative(ListIF<GTreeIF<Node>> rootChildren, String prefix) {

        IteratorIF<GTreeIF<Node>> rootChildrenIT = rootChildren.iterator();
        List<StockPair> sequence = new List<>();

        while (rootChildrenIT.hasNext()) {

            GTreeIF<Node> root = rootChildrenIT.getNext();

            StringBuilder preString = new StringBuilder();
            Stack<String> charBreadPath = new Stack<>();

            Stack<GTreeIF<Node>> stackPREORDER = new Stack<>();

            if (root == null) {
                return sequence;
            }

            stackPREORDER.push(root);

            boolean firstNodeValue = true;
            boolean firstSotckPair = true;

            while (!stackPREORDER.isEmpty()) {

                //Continue Preorder
                GTreeIF<Node> node = stackPREORDER.getTop();
                stackPREORDER.pop();
                Node nodeRoot = node.getRoot();

                //Create StockPair for first word

                if (nodeRoot instanceof NodeInner nodeInner) {
                    //System.out.println(nodeInner.getLetter());
                    preString.append(nodeInner.getLetter());
                } else if (nodeRoot instanceof NodeInfo nodeInfo) {

                    if (firstSotckPair) {
                        charBreadPath.push(prefix);
                        firstSotckPair = false;
                    }

                    String producto = charBreadPath.getTop();
                    charBreadPath.pop();

                    //System.out.println("Inserted " + (producto + preString));

                    sequence.insert(1, new StockPair((producto + preString), nodeInfo.getUnidades()));
                    preString.setLength(0);
                }

                //Add children to stack PREORDER
                IteratorIF<GTreeIF<Node>> childrenIT = node.getChildren().iterator();
                if (childrenIT.hasNext()) {
                    stackPREORDER.push(childrenIT.getNext());
                }
                while (childrenIT.hasNext()) {

                    stackPREORDER.push(childrenIT.getNext());
                    charBreadPath.push((prefix + preString));
                }
            }
        }
            return sequence;
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
                rootTree.addChild(1, child);
                // Target tree pasa a ser el nuevo árbol
                rootTree = child;
            } else {
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
                    rootTree.addChild(1, child);

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
            rootTree.addChild(1, TreeInfo);
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
                    nodeInfo.setUnidades(u);
                }
            }
        }
    }

    @Override
    public int retrieveStock(String p) {
        int n = p.length();
        //Determina el target tree como el stock
        GTreeIF<Node> rootTree = stock;

        // Itera sobre el string p
        for (int i = 0; i < n; i++) {

            char c = p.charAt(i);
            boolean found = false;

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
            if (!found) {
                return -1;
            }
        }
        IteratorIF<GTreeIF<Node>> it = rootTree.getChildren().iterator();
        while (it.hasNext()) {
            GTreeIF<Node> child = it.getNext();
            if (child.getRoot() instanceof NodeInfo nodeInfo) {
                //Modificamos el valor del nodo INFO
                return nodeInfo.getUnidades();
            }
        }
        return -1;
    }

    @Override
    public SequenceIF<StockPair> listStock(String prefix) {
        int n = prefix.length();
        //Determina el target tree como el stock
        GTreeIF<Node> rootTree = stock;

        // Itera sobre el string p
        for (int i = 0; i < n; i++) {

            char c = prefix.charAt(i);
            boolean found = false;

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
            if (!found) {
                return new List<>();
            }
        }
        if (rootTree.getNumChildren() != 0) {
            return preorderIterative(rootTree.getChildren(), prefix);
        } else {
            return new List<>();
        }

    }
}