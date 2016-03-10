package stone;

import java.io.IOException;

// 
// パースエラ-
//
public class ParseException extends IOException {
	// コンストラクタ
	public ParseException(Token t) {
		this("",t);
	}
	public ParseException(String msg,Token t) {
		super("syntax error aroud" + location(t) + ". " +msg);
	}
	private static String location(Token t) {
		// ファイルの末行なら
		if(t == Token.EOF) {
			return "the last line";
		}
		else {
			return "\"" + t.getText() + "\" at line " + t.getLineNumber();
		}
	}
	public ParseException(IOException e) {
		super(e);
	}
	public ParseException(String msg) {
		super(msg);
	}
}
