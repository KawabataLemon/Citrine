package stone.ast;

import java.util.List;

// 
// 関数定義ステートメント
//
public class DefStmnt extends ASTList {
	public DefStmnt(List<ASTree> c) { super(c); }
	public String name() { return ((ASTLeaf)child(0)).token().getText(); }
	public BlockStmnt body() { return (BlockStmnt)child(2); }	// 処理部分を返す
	public ParameterList parameters() { return (ParameterList)child(1); }
	public String toString() { 
		return "(def " + name() + " " + parameters() + " " + body() + ")";
	}
}
