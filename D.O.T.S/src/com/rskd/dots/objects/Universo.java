package com.rskd.dots.objects;

import java.util.ArrayList;
import java.util.Random;

import com.rskd.dots.objects.interfaces.IActividad;

public class Universo {
	
	public ArrayList<Individuo> individuos;
	public ArrayList<Individuo> individuosNuevos;
	public ArrayList<Relacion> relaciones;
	Random r;
	public long tick;
	
	public Universo(){
		individuos=new ArrayList<Individuo>();
		relaciones=new ArrayList<Relacion>();
		r=new Random();
		tick=0;
	}
	
	public Universo(int seed){
		this();
		r=new Random(seed);
	}
	
	public Individuo crearIndividuo(int r, int g, int b){
		Individuo i=new Individuo(r, g, b,this);
		individuos.add(i);
		return i;
	}
	
	
	public Individuo reproducir(Individuo i1,Individuo i2){
		Individuo h=new Individuo(i1,i2);
		if(individuosNuevos!=null){
			individuosNuevos.add(h);
		} else{
			individuos.add(h);
		}
		crearRelacion(h,i1,Relacion.PADRE,100);
		crearRelacion(h,i2,Relacion.PADRE,100);		
		evento(h,"nace");
		return h;
	}
	
	
	public void crearRelacion(Individuo i1,Individuo i2,Integer tipo,int porcentaje){
		Relacion r=new Relacion(i1, i2, tipo, porcentaje);
		relaciones.add(r);
	}
	
	
	public void vivir(){
		individuosNuevos=new ArrayList<Individuo>();
		for(Individuo i:individuos){
			i.vivir(this);
		}
		for(Individuo i:individuosNuevos){
			individuos.add(i);
		}
		individuosNuevos=null;
		tick++;
	}

	public int random(int desde, int hasta) {		
		return desde+r.nextInt((hasta-desde)+1);
	}

	public void evento(Individuo individuo, String evento) {
		//eventos, en principio imprime
		System.out.println("Tick "+tick+":"+individuo+" "+evento);		
	}
	
	public int distancia(Individuo i1,Individuo i2){
		int x12=i1.getX()-i2.getX();
		int y12=i1.getY()-i2.getY();
		return x12*x12+y12*y12;
	}
	
	public ArrayList<Individuo> getIndividuosADistanciaWhitActividad(Individuo i1,int distancia, IActividad actividad){
		ArrayList<Individuo> cercanos=getIndividuosADistancia(i1,distancia);
		ArrayList<Individuo> res=new ArrayList<Individuo>();
		for(Individuo i:cercanos){
			if(i.getActividad().getClass().equals(actividad.getClass())){
				res.add(i);
			}
		}
		return res;
	}
	
	public ArrayList<Individuo> getIndividuosADistancia(Individuo i1,int distancia){
		ArrayList<Individuo> res=new ArrayList<Individuo>();
		for(Individuo i:individuos){
			if(i!=i1){
				if(distancia(i1,i)<distancia){
					res.add(i);
				}
			}
		}		
		return res;
	}

}
