package yal.arbre.expression;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {
    
	protected String returnType;
	
    protected Expression(int n) {
        super(n) ;
    }

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

}
