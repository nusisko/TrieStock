package es.uned.lsi.eped.pract2022_2023;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.SequenceIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

public class StockSequence implements StockIF {

    protected ListIF<StockPair> stock;

    /* Constructor de la clase */
    public StockSequence() {
        stock = new List<>();
    }

    @Override
    public int retrieveStock(String p) {
        IteratorIF<StockPair> stockIT = stock.iterator();
        while (stockIT.hasNext()) {
            StockPair pair = stockIT.getNext();
            if (pair.getProducto().equals(p)) {
                return pair.getUnidades();
            }
        }
        return -1; // No se encontró el producto
    }

    @Override
    public void updateStock(String p, int u) {

        if (stock.isEmpty()) {
            stock.insert(1, new StockPair(p, u));
        }else{
            IteratorIF<StockPair> stockIT = stock.iterator();
            int i = 1;
            while (stockIT.hasNext()) {
                StockPair pair = stockIT.getNext();
                if (pair.getProducto().equals(p)) {
                    pair.setUnidades(u);
                    return; // Actualización realizada, salimos del bucle.
                } else if (pair.getProducto().compareTo(p) > 0) {
                    stock.insert(i, new StockPair(p, u));
                    return;
                }
                i++;
            }
            stock.insert(i, new StockPair(p, u));
        }


    }

    @Override
    public SequenceIF<StockPair> listStock(String prefix) {
        ListIF<StockPair> result = new List<>(); // Secuencia para almacenar resultados
        IteratorIF<StockPair> stockIT = stock.iterator();
        while (stockIT.hasNext()) {
            StockPair pair = stockIT.getNext();
            if (pair.getProducto().startsWith(prefix)) {
                result.insert(1, pair);
            } else if (pair.getProducto().compareTo(prefix) > 0) {
                return result;
            }
        }
        return result;
    }
}