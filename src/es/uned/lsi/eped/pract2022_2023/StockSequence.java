package es.uned.lsi.eped.pract2022_2023;

import es.uned.lsi.eped.DataStructures.SequenceIF;

public class StockSequence implements StockIF {

	protected SequenceIF<StockPair> stock;
	
	/* Constructor de la clase */
	public StockSequence() {
	}

	@Override
	public int retrieveStock(String p) {
		return 0;
	}

	@Override
	public void updateStock(String p, int u) {

	}

	@Override
	public SequenceIF<StockPair> listStock(String prefix) {
		return null;
	}

}
