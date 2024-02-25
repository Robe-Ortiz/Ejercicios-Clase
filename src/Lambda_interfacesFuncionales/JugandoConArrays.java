package Lambda_interfacesFuncionales;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class JugandoConArrays {

	static Integer[] transformar(Integer[] arrayDeInteger, Function<Integer,Integer> function){
		Integer[] arrayDeIntegerFinal = new Integer[arrayDeInteger.length];
		for(int i = 0; i < arrayDeInteger.length ;i++) {
			arrayDeIntegerFinal[i] = function.apply(arrayDeInteger[i]);	
		}
		return arrayDeIntegerFinal;
	}
	
	
	static void procesar (Integer[] arrayDeInteger, Consumer<Integer> consumer) {
		for(Integer num : arrayDeInteger) {
			consumer.accept(num);
		}
	}
	
	
	static Integer[] filtrar(Integer[] arrayDeInteger, Predicate<Integer> predicate){
		
		int tamanioArray = 0;
		for(Integer num : arrayDeInteger) {
			if(predicate.test(num)) {
				tamanioArray++;
			}
		}
		Integer[] listaFinal = new Integer[tamanioArray];
		int indice = 0;
		for(int i = 0; i < arrayDeInteger.length ; i++) {
			if(predicate.test(arrayDeInteger[i])) {
				listaFinal[indice] = arrayDeInteger[i]; 
				indice++;
			}
		}
		return listaFinal;
	}
	
	
	static Integer[] generar(int numElementosDelArray,Supplier<Integer> supplier) {
		Integer[] arrayFinal = new Integer[numElementosDelArray];
		for(int i = 0; i < arrayFinal.length; i++) {
			arrayFinal[i] = supplier.get();
		}
		return arrayFinal;
		
	}
	
	
	static Integer[] combinar (Integer[] arrayA, Integer[] arrayB,BiFunction<Integer,Integer,Integer> bifuction) {
		if(arrayA.length != arrayB.length) return null;
		
		Integer[] arrayFinal = new Integer[arrayA.length];
		for(int i = 0; i<arrayA.length ;i++) {
			arrayFinal[i] = bifuction.apply(arrayA[i],arrayB[i]);
		}
		return arrayFinal;
	}
	
	
	static void procesarElementos(Integer[] arrayA,Integer[]arrayB,BiConsumer<Integer,Integer> biconsumer){
		BinaryOperator<Integer> saberIndice = (t, u) -> t < u ? t:u;
		int indice = saberIndice.apply(arrayA.length,arrayB.length);
		
		for(int i = 0; i<indice;i++) {
			biconsumer.accept(arrayA[i], arrayB[i]);
		}
	}
	
	
	static Integer []transformarParesEImpares (Integer[] arrayDeInteger, Function<Integer,Integer> functionPares,Function<Integer,Integer> functionImpares){
		Integer[] arrayFinal = new Integer[arrayDeInteger.length];	
		for(int i = 0;i<arrayDeInteger.length;i++) {
			if(i%2==0){
				arrayFinal[i] = functionPares.apply(arrayDeInteger[i]);
			}else{
				arrayFinal[i] = functionImpares.apply(arrayDeInteger[i]);
			}
		}
		return arrayFinal;
	}
	
	static Integer[] transformarConConjunto(Integer[] arrayDeInteger, Function<Integer,Integer>[] arrayfunction){
		Integer[] arrayFinal = new Integer[arrayDeInteger.length];
		for(int i =0; i< arrayDeInteger.length;i++) {
			arrayFinal[i] = arrayDeInteger[i];
		}		
		for(int i = 0;i<arrayfunction.length;i++) {
			for(int u = 0;u<arrayFinal.length;u++) {
				arrayFinal[u]= arrayfunction[i].apply(arrayFinal[u]);
			}
		}	
		return arrayFinal;
	}
	
	
	
	
	
	public static void main(String[] args) {
		//transformar
		Integer[] primerArray = {2,4,6,8,10};
		Integer[] segundoArray = JugandoConArrays.transformar(primerArray,s->s+1);
		
		for(Integer num : segundoArray) {
			System.out.printf("%d ",num);
		}
		System.out.println();
		
		//procesar
		Integer[] arrayDeNumeros = {0,10,20,30};
		JugandoConArrays.procesar(arrayDeNumeros, s->System.out.printf("Num: %d  ",s));
		System.out.println();
		
		//filtrar
		Integer[] numeros = {0,1,2,3,4,5,6,7,8,9,10};
		Integer[] numeros2 = JugandoConArrays.filtrar(numeros, s->s%2!=0);
		System.out.print("Nuevo array tras pasar los filtros: ");
		for(Integer num : numeros2) {
			System.out.printf("%d ",num);
		}
		System.out.println();
		
		//generar
		Random random = new Random();
		Integer[] numerosGenerados = JugandoConArrays.generar(4, ()->random.nextInt(51));
		for(Integer num : numerosGenerados) {
			System.out.print("N="+num+" ");
		}
		System.out.println();
		
		//combinar
		Integer[] arrayComb1 = {2,4,6};
		Integer[] arrayCombi2 = {1,1,1};
		Integer[]arrayCombiFinal = JugandoConArrays.combinar(arrayComb1, arrayCombi2, (n,s)->n + 1 + s * 2);
		for(Integer num: arrayCombiFinal) {
			System.out.println(num);
		}
		
		//procesarElementos	
		 Integer[] arrayProcesar1 = {1,2,3,};
		 Integer[] arrayProcesar2 = {1,1,};
		 JugandoConArrays.procesarElementos(arrayProcesar1, arrayProcesar2,
				 (s,n)->System.out.printf("Valor primer array: %d. Valor segundo array: %d \n",s,n));
		 System.out.println();
		 
		 //transformarParesEImpares
		 Integer[] transformarParesEImpares = {0,1,2,3,4,5,6,7,8,9,10};
		 Integer[] transformarParesEImpares2 = JugandoConArrays.transformarParesEImpares(transformarParesEImpares,s->s*2, s->s+1);
		 for(Integer num : transformarParesEImpares2) {
			 System.out.printf("%d ",num);
		 }
		 System.out.println();
		 
		 //transformarConConjunto
		 Integer[] numerosOriginales = {1,2,3};
		 
		 
		 Function<Integer,Integer>[] arrayFunction = new Function[]{ //Me obliga a castear para poder crearlo.
				 (Function<Integer,Integer>) s -> s + 1,
				 (Function<Integer,Integer>)s -> s * 2,
				 };
		 
		 Integer[] numerosFinales = JugandoConArrays.transformarConConjunto(numerosOriginales, arrayFunction);
		 for(Integer num : numerosFinales) {
			 System.out.printf("%d ",num);
		 }
		 
		 
		 /* simplificar expresiones lambdas*/
		 /* 01 */ Function <Integer,Integer> function = s -> s + 1;
		 /* 02 */ BiFunction<Integer,Integer,Integer> bifunction = (x,y) -> x + y;
		 /* 03 */ Runnable runable = () -> System.out.println("Hola Mundo");
		 /* 04 */ Predicate<String> predicate = s -> s.isEmpty();
		 /* 05 */ UnaryOperator<Double> unaryOperator = d -> d * 2.0; 
		 /* 06 */ BiConsumer<Integer,Integer> biConsumer = (a,b) ->System.out.println(a - b);
		 /* 07 */ BiPredicate<String,String> biPredicate = (s1,s2) -> s1.equals(s2);
		 /* 08 */ Supplier<Integer> supplier = () -> 42;
		 /* 09 */ Function<List<String>,Integer> function2 = list ->list.size();
		 /* 10 */ Predicate<Map<String,Integer>> predicate2 = map -> map.isEmpty();
		 /* 11 */ Predicate<Character> predicate3 = c -> Character.isDigit(c); 
		 /* 12 */ Function<String,String> function3 = str -> str.toUpperCase();
		 /* 13 */ UnaryOperator<String> unaryOperator2 = str-> new StringBuilder(str).reverse().toString();
		 /* 14 */ Predicate<Boolean> predicate4 = b -> !b; 
		 /* 15 */ BiFunction<String,Integer,String> bifunction2 = (String s, Integer n) -> s.substring(0, n); 
		 /* 16 */ BiFunction<Integer,Double,Double> biFunction3 = (Integer n, Double d) ->Math.pow(n, d);
		 
	}
}

