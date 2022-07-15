import java.util.HashMap;
import java.util.Map;

public class Partitionator {
	
	public static int findMinPartition(int[] array, int index, int sub01, int sub02, Map<String, Integer> solutions) {
		
		if (index < 0) {
			return Math.abs(sub01 - sub02);
		}

		String key = index + "|" + sub01 + "|" + sub02;
		
		if (!solutions.containsKey(key)) {
			
			int includeSub01 = findMinPartition(array, index - 1, sub01 + array[index], sub02, solutions);

			int includeSub02 = findMinPartition(array, index - 1, sub01, sub02 + array[index], solutions);

			solutions.put(key, Integer.min(includeSub01, includeSub02));
		}

		return solutions.get(key);
	}

	public static void main(String[] args) {
		
		int[] S = { 10, 5, 14};

		Map<String, Integer> solutions = new HashMap<>();

		System.out.println("A diferença de partição mínima é: " + findMinPartition(S, S.length - 1, 0, 0, solutions));
	}
}
