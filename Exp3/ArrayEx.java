import java.util.Arrays;
public class ArrayEx{
	public static void main(String[] args) {
		int[] iArr;
		iArr = new int[5];
		iArr[0] = 20;
		iArr[1] = 40;
		iArr[2] = 100;
		iArr[3] = 32;
		iArr[4] = 22;

		for(int i = 0; i < 5; i++) {
			System.out.println(iArr[i]);
		}

		double[] dArr = new double[] {123.22, 231.44, 334.55, 500.789};
		for (double d : dArr) {
			System.out.println(d*d);
		}

		char[] letters = {'s', 'o', 'u', 'n', 'd'};

		int[] iArrl = new int[5];
		System.arraycopy(iArr, 0, iArrl, 0, 5);

		for (int i : iArrl) {
			System.out.println(i);
		}

		boolean e = Arrays.equals(iArr, iArrl); 
		System.out.println(e);

		Arrays.sort(iArr);
		for (int i : iArr) {
			System.out.println(i);
		}

		boolean e1 = Arrays.equals(iArr, iArrl);
		System.out.println(e1);


		String[] words = new String[] {"I", "like", "to", "eat", "apples", "and", "bananas"};
		int counter = 0;
		for (String word: words) {
			String lower = word.toLowerCase();
			if (lower.startsWith("a") ||lower.startsWith("e") ||lower.startsWith("i") ||lower.startsWith("o") ||lower.startsWith("u")) {
				counter += 1;
			}
		}
		System.out.println(counter);
	}
} 
