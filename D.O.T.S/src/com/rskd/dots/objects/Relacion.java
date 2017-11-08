package com.rskd.dots.objects;

public class Relacion {
	
	public static final Integer PADRE = 1;
	public static final Integer PAREJA = 2;
	private Individuo individuo1;
	private Individuo Individuo2;
	private Integer tipoRelacion;
	private int relPorc;
	
	public Relacion(Individuo i1,Individuo i2,Integer tipo,int porcentaje){
		this.individuo1=i1;
		this.Individuo2=i2;
		this.tipoRelacion=tipo;
		this.relPorc=porcentaje;
	}

	public Individuo getIndividuo1() {
		return individuo1;
	}

	public Individuo getIndividuo2() {
		return Individuo2;
	}

	public Integer getTipoRelacion() {
		return tipoRelacion;
	}

	public int getRelPorc() {
		return relPorc;
	}

}
