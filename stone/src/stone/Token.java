package stone;

// トークンの解析クラス
public abstract class Token {
	
	// クラス定数
	public static final Token EOF = new Token(-1){};	// ファイルの終了値を示す
	public static final String EOL = "\\n";			// 行の終了
	
	private int lineNumber;								// 行数情報
	
	// コンストラクタ
	protected Token(int line) {
		lineNumber = line;
	}
	
	public int getLineNumber() { return lineNumber; };
	public boolean isIdentifier() { return false; };
	public boolean isNumber() { return false; };
	public boolean isString() { return false; };
	public int getNumber() { throw new StoneException("not number token"); };
	public String getText() { return ""; };
}
