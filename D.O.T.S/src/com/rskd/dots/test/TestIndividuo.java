package com.rskd.dots.test;

import com.rskd.dots.objects.Individuo;
import com.rskd.dots.objects.Universo;

public class TestIndividuo {

	public static void main(String[] args) {
		Universo u=new Universo();
		
		Individuo iazul=u.crearIndividuo(0,0,255);
		Individuo irojo=u.crearIndividuo(255,0,0);
		
		/*Individuo hijo=u.reproducir(iazul,irojo);
		Individuo hijo2=u.reproducir(iazul, hijo);
		Individuo hijo3=u.reproducir(iazul, hijo2);
		
		
		
		System.out.println(hijo.getColor());
		System.out.println(hijo2.getColor());
		System.out.println(hijo3.getColor());*/
		
		while(iazul.estaVivo()){
			u.vivir();
		}

	}

}
