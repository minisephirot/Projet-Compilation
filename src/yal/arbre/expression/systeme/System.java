package yal.arbre.expression.systeme;

import yal.arbre.ArbreAbstrait;

public abstract class System extends ArbreAbstrait {
	
	protected String s;
	
	protected System(int no, String s) {
		super(no);
		this.s = s;
	}
	
	

}
