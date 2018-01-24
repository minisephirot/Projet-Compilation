package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class MoinsUnaire extends Unaire {
    
    public MoinsUnaire(Expression expr) {
        super(expr);
        this.returntype = "int";
    }

    @Override
    public String operateur() {
        return "- " ;
    }

	@Override
	public void verifier() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toMIPS() {
		// -x = on inverse tous les bits et on ajoute 1
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# moins unaire\n");
		sb.append("xori $v0, $v0, 0xFFFFFFFF\n");
		sb.append("addi $v0, $v0, 1\n");
		return sb.toString();		
	}
}
