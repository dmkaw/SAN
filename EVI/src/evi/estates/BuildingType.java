package evi.estates;

public enum BuildingType {
	RESIDENTAL("Residental"),
	LEISURE("Leisure"),
	RETAIL("Retail"),
	OFFICE("Office"),
	INDUSTRIAL("Industrial"),
	HEALTHCARE("Healthcare");
	
	String type;
	
	BuildingType(String type){
		this.type = type;
	}
}
