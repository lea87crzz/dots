package com.rskd.dots.objects;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.rskd.dots.actividades.*;
import com.rskd.dots.objects.interfaces.IActividad;
import com.rskd.dots.objects.interfaces.IObjetoVivo;
import com.rskd.dots.objects.interfaces.IPosicionable;
import com.rskd.dots.utils.IndividuoConstants;

public class Individuo implements IObjetoVivo, IPosicionable {

	private int r;
	private int g;
	private int b;
	
	private int edad;
	private Boolean vivo;
	private int salud;
	
	private int posX;
	private int posY;
	
	private IActividad actividadActual;
	private Map<String,Integer> atributos;
	
	public Individuo(Individuo p1,Individuo p2){
		this();
		//la procreacion del individuo toma partes iguales de los padres	
		r=(p1.r+p2.r)/2;
		g=(p1.g+p2.g)/2;
		b=(p1.b+p2.b)/2;
		
		
	}
	
	public Individuo(int red,int green,int blue,Universo u){
		this();
		if(red<0 || red>255) throw new RuntimeException("Valor incorrecto");
		if(green<0 || green>255) throw new RuntimeException("Valor incorrecto");
		if(blue<0 || blue>255) throw new RuntimeException("Valor incorrecto");
		this.r=red;
		this.b=blue;
		this.g=green;
		atributos.put(IndividuoConstants.ATTR_EDAD_INICIO_REPRODUCCION,IndividuoConstants.EDAD_INICIO_REPRODUCCION+u.random(-100, 100));
	}
	
	public Individuo() {
		edad=0;
		vivo=true;
		salud=100;
		actividadActual=new HacerNada();
		atributos=new HashMap<String, Integer>();
		atributos.put(IndividuoConstants.ATTR_EDAD_DECADENCIA,IndividuoConstants.EDAD_DECADENCIA);
		atributos.put(IndividuoConstants.ATTR_EDAD_INICIO_REPRODUCCION,IndividuoConstants.EDAD_INICIO_REPRODUCCION);
	}

	public Color getColor(){
		return new Color(r,g,b);
	}
	
	
	public int getEdad(){
		return edad;
	}
	
	public void vivir(Universo u){
		if(vivo){
			//a partir de cierta edad, bajar salud
			if(edad>getAtributo(IndividuoConstants.ATTR_EDAD_DECADENCIA)){
				salud-=u.random(0,1);
			}
			
			//si salud es menor que 0, muere
			if(salud<=0){
				vivo=false;
				u.evento(this, "murió a los "+edad+" ticks.");
			}
		
			edad++;
			if(edad==getAtributo(IndividuoConstants.ATTR_EDAD_INICIO_REPRODUCCION)){
				u.evento(this, "esta en celo");
				actividadActual=new FindMate();
			}
			if(actividadActual!=null){
				actividadActual.realizarActividad(this,u);
			}
		}
	}

	private int getAtributo(String attr) {
		if(atributos.containsKey(attr))
			return atributos.get(attr);
		return 0;
	}
	
	public boolean estaVivo(){
		return vivo;
	}

	@Override
	public Integer getX() {
		return posX;
	}

	@Override
	public Integer getY() {
		return posY;
	}

	public IActividad getActividad() {
		return actividadActual;
	}

	public void hacerNada() {
		this.actividadActual=new HacerNada();
		
	}

}
