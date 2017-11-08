package com.rskd.dots.actividades;

import java.util.ArrayList;

import com.rskd.dots.objects.Individuo;
import com.rskd.dots.objects.Relacion;
import com.rskd.dots.objects.Universo;
import com.rskd.dots.objects.interfaces.IActividad;
import com.rskd.dots.utils.IndividuoConstants;

public class FindMate implements IActividad {

	@Override
	public void realizarActividad(Individuo individuo,Universo u) {
		ArrayList<Individuo> posibles=u.getIndividuosADistanciaWhitActividad(individuo, IndividuoConstants.DISTANCIA_CERCA, this);//whitout relacion
		if(!posibles.isEmpty()){
			Individuo mate=posibles.get(0);
			u.crearRelacion(individuo, mate, Relacion.PAREJA, 100);
			u.evento(individuo, "se emparejó con "+mate);
			
			u.reproducir(individuo, mate);
			individuo.hacerNada();
			mate.hacerNada();
		}

	}

}
