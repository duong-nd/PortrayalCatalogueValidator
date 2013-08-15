
public enum Symbols {
	POINTSYMBOLIZER(1,"PointSymbolizer"),
	LINESYMBOLIZER(2,"LineSymbolizer"),
	POLYGONSYMBOLIZER(3,"PolygonSymbolizer"),
	TEXTSYMBOLIZER(4,"TextSymbolizer"),
	RASTERSYMBOLIZER(5,"RasterSymbolizer"),
	COLORS(6,"Colors");
	
	private int code;
	private String label;
	
	private Symbols(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}
}
