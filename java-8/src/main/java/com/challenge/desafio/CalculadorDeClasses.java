package com.challenge.desafio;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

public class CalculadorDeClasses implements Calculavel {

	@Override
	public BigDecimal somar(Object object) {

		return calcular(object, Somar.class);
	}

	@Override
	public BigDecimal subtrair(Object object) {
		
		return calcular(object, Subtrair.class);
	}

	@Override
	public BigDecimal totalizar(Object object) {
		
		return somar(object).subtract(subtrair(object));
	}

	public BigDecimal calcular(Object object, Class annotation) {

		BigDecimal soma = BigDecimal.ZERO;

		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);

			if (field.getType().equals(BigDecimal.class)) {
				
				if (field.isAnnotationPresent(annotation)) {

					try {
						soma = soma.add((BigDecimal) field.get(object));

					} catch (IllegalAccessException e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
					
				}
				
			}
		}
		
		return soma;
	}

}
