package stone;
import static stone.Parser.rule;
import stone.ast.Fun;

//
// クロージャ処理に対応したパーサー
//
public class ClosureParser extends FuncParser {
	public ClosureParser() {
		primary.insertChoice(rule(Fun.class).sep("fun").ast(paramList).ast(block));
	}
}
