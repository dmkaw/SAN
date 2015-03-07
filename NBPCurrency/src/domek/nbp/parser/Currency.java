package domek.nbp.parser;

import java.math.BigDecimal;

public class Currency {
	private final String code;
	private final BigDecimal rate;
	
	public Currency(String code, BigDecimal rate) {
		super();
		this.code = code;
		this.rate = rate;
	}
	
	public String getCode() {
		return code;
	}
	
	public BigDecimal getRate() {
		return rate;
	}
	
	@Override
	public String toString(){
		return code + " : " + rate;
	}
}
