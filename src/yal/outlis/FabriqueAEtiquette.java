package yal.outlis;

public class FabriqueAEtiquette {

	private static FabriqueAEtiquette instance = new FabriqueAEtiquette();
	private int indexSi;
	private boolean indexDiv0;
	
	private FabriqueAEtiquette() {
		indexSi = 0;
		indexDiv0 = false;
	};
	
	public static FabriqueAEtiquette getInstance() {
		return instance;
	}

	public int getIndexSi() {
		return indexSi;
	}

	public boolean getIndexDiv0() {
		return indexDiv0;
	}

	public void setIndexDiv0() {
		this.indexDiv0 = true;
	}
	
	public void addIndexSi() {
		indexSi++;
	}
}
