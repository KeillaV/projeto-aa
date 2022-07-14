import java.util.HashMap;
import java.util.Map;

public class Partitionator {
	// Particiona o conjunto `S` em dois subconjuntos, `S1` e `S2`, de modo que o
	// diferença entre a soma dos elementos em `S1` e a soma
	// dos elementos em `S2` é minimizado
	public static int findMinAbsDiff(int[] S, int n, int S1, int S2, Map<String, Integer> lookup) {
		// Caso base: se a lista ficar vazia, retorna o absoluto
		// diferença entre os dois conjuntos
		if (n < 0) {
			return Math.abs(S1 - S2);
		}

		// Construir uma chave de mapa exclusiva a partir de elementos dinâmicos da
		// entrada.
		// Observe que podemos identificar exclusivamente o subproblema apenas com `n` e
		// `S1`,
		// como `S2` nada mais é que `S-S1`, onde `S` é a soma de todos os elementos
		String key = n + "|" + S1;

		// Se o subproblema for visto pela primeira vez, resolva-o e
		// armazena seu resultado em um mapa
		if (!lookup.containsKey(key)) {
			// Caso 1. Incluir o item atual no subconjunto `S1` e repetir
			// para os itens restantes `n-1`
			int inc = findMinAbsDiff(S, n - 1, S1 + S[n], S2, lookup);

			// Caso 2. Excluir o item atual do subconjunto `S1` e retornar para
			// os itens restantes `n-1`
			int exc = findMinAbsDiff(S, n - 1, S1, S2 + S[n], lookup);

			lookup.put(key, Integer.min(inc, exc));
		}

		return lookup.get(key);
	}

	public static void main(String[] args) {
		// Entrada: um conjunto de itens
		int[] S = { 10, 20, 15, 5, 25 };

		// cria um mapa para armazenar soluções para subproblemas
		Map<String, Integer> lookup = new HashMap<>();

		System.out.println("The minimum difference is " + findMinAbsDiff(S, S.length - 1, 0, 0, lookup));
	}
}
