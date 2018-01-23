package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class Binaire extends Expression {
    
    protected Expression gauche ;
    protected Expression droite ;

    protected Binaire(Expression gauche, Expression droite) {
        super(gauche.getNoLigne());
        this.gauche = gauche;
        this.droite = droite;
    }
    
    public abstract String operateur() ;

    @Override
    public String toString() {
        return "(" + gauche + operateur() + droite + ")" ;
    }
    
    @Override
    public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# opération ");
		sb.append(operateur());
		
		sb.append(", calcul de gauche\n");
		sb.append(gauche.toMIPS());
		
		sb.append("# empiler gauche\n");
		sb.append("sw $v0, ($sp)\n");
		sb.append("addi $sp, $sp, -4\n");
		
		sb.append("# calcul de droite\n");
		sb.append(droite.toMIPS());
		
		sb.append("# dépile droite dans $t8\n");
		sb.append("addi $sp, $sp, 4\n");
		sb.append("lw $t8, ($sp)\n");
		return sb.toString();
    }

}
