import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

public class ReferenceCounter {
	
private int count=0;
	
	public int getcount(){
		return count;
	}
	
	public void updateCounter(CompilationUnit cu, String type) {
		// Count References
		cu.accept(new ASTVisitor() {public boolean 	visit(FieldDeclaration node) {
						String qualifiedName = node.getType().toString();
						
						if (type.equals(qualifiedName)) {
							count++;
						}
						return true;
					}
				});
	}

}
