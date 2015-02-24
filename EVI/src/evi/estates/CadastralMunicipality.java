package evi.estates;

public enum CadastralMunicipality {
	BOREK("Borek"),
	BUKOWA("Bukowa"),
	KISTOWO("Kistowo"),
	KŁODNO("Kłodno"),
	MŚCISZEWICE("Mściszewice"),
	PODJAZY("Podjazdy"),
	SUCHA("Sucha"),
	SULĘCZYNO("Sulęczyno"),
	WĘSIORY("Węsiory"),
	ZDUNOWICE("Zdunice"),
	ŻAKOWO("Żakowo");
	
	String name;
	
	private CadastralMunicipality(String name) {
		this.name = name;
	}
}
