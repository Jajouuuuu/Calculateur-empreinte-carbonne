package consoCarbone;

/**
 * Interface pour comparer des objets de type ConsoCarbone.
 * 
 * @param <ConsoCarbone> Le type d'objet à comparer.
 */
public interface Comparable<ConsoCarbone> {

	/**
	 * Compare l'objet courant à un autre objet de type ConsoCarbone.
	 * 
	 * @param conso L'objet ConsoCarbone à comparer à l'objet courant.
	 * @return Un entier négatif si l'objet courant est considéré comme étant
	 *         "avant" l'objet conso, un entier positif si l'objet courant est
	 *         considéré comme étant "après" l'objet conso, ou 0 si les deux objets
	 *         sont considérés comme étant égaux.
	 */
	public int compareTo(ConsoCarbone conso);

}