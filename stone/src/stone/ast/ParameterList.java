package stone.ast;
import java.util.List;

// 
// パラメータリスト
//
public class ParameterList extends ASTList {
	public ParameterList(List<ASTree> c) { super(c); }
	public String name(int i) { return ((ASTLeaf)child(i)).token().getText(); }
	public int size() { return numChildren(); }
}
