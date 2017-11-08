package com.rskd.dots.objects;

import java.util.ArrayList;
import java.util.Random;

public class Universo {
	
	public ArrayList<Individuo> individuos;
	public ArrayList<Relacion> relaciones;
	Random r;
	
	public Universo(){
		individuos=new ArrayList<Individuo>();
		relaciones=new ArrayList<Relacion>();
		r=new Random();
	}
	
	public Universo(int seed){
		this();
		r=new Random(seed);
	}
	
	public Individuo crearIndividuo(int r, int g, int b){
		Individuo i=new Individuo(r, g, b);
		individuos.add(i);
		return i;
	}
	
	
	public Individuo reproducir(Individuo i1,Individuo i2){
		Individuo h=new Individuo(i1,i2);
		individuos.add(h);
		crearRelacion(h,i1,Relacion.PADRE,100);
		crearRelacion(h,i2,Relacion.PADRE,100);		
		return h;
	}
	
	
	public void crearRelacion(Individuo i1,Individuo i2,Integer tipo,int porcentaje){
		Relacion r=new Relacion(i1, i2, tipo, porcentaje);
		relaciones.add(r);
	}
	
	
	public void vivir(){
		for(Individuo i:individuos){
			i.vivir(this);
		}
	}

	public int random(int desde, int hasta) {		
		return desde+r.nextInt((hasta-desde)+1);
	}

	public void evento(Individuo individuo, String evento) {
		//eventos, en principio imprime
		System.out.println(individuo+" "+evento);
		
	}

}
