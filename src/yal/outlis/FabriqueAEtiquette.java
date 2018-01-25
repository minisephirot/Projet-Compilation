package yal.outlis;

public class FabriqueAEtiquette {

	private static FabriqueAEtiquette instance = new FabriqueAEtiquette();
	private int indexSi;
	private int indexDiv0;
	
	private FabriqueAEtiquette() {
		indexSi = 0;
		indexDiv0 = 0;
	};
	
	public static FabriqueAEtiquette getInstance() {
		return instance;
	}

	public int getIndexSi() {
		return indexSi;
	}

	public void setIndexSi(int indexSi) {
		this.indexSi = indexSi;
	}

	public int getIndexDiv0() {
		return indexDiv0;
	}

	public void setIndexDiv0(int indexDiv0) {
		this.indexDiv0 = indexDiv0;
	}
	
	public void addIndexSi() {
		indexSi++;
	}
	
	public void addIndexDiv0() {
		indexDiv0++;
	}
	
	
}
