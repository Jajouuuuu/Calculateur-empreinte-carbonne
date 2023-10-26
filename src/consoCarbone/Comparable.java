package consoCarbone;

/**
 * Interface pour comparer des objets de type ConsoCarbone.
 * 
 * @param <ConsoCarbone> Le type d'objet � comparer.
 */
public interface Comparable<ConsoCarbone> {

	/**
	 * Compare l'objet courant � un autre objet de type ConsoCarbone.
	 * 
	 * @param conso L'objet ConsoCarbone � comparer � l'objet courant.
	 * @return Un entier n�gatif si l'objet courant est consid�r� comme �tant
	 *         "avant" l'objet conso, un entier positif si l'objet courant est
	 *         consid�r� comme �tant "apr�s" l'objet conso, ou 0 si les deux objets
	 *         sont consid�r�s comme �tant �gaux.
	 */
	public int compareTo(ConsoCarbone conso);

}