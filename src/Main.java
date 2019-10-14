import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {

	// Products Hashmap
	static HashMap<Integer, String> products = new HashMap<Integer, String>();

	
	
	/**
	 * Presents to the screen two minimal values that are less than the Balance
	 * @param min
	 * @param secondMin
	 * @return
	 */
	public static String createResult(int min, int secondMin) {
		String nameMin = products.get(min);
		String nameSecondMin = products.get(secondMin);
		String result = String.format("%1$s %2$s , %3$s %4$s", nameMin, min, nameSecondMin, secondMin);

		return result;
	}

	
	
	/**
	 * Finds two minimal values based to a file of products up to the given balance
	 * @param file
	 * @param balance
	 * @return
	 */
	public static int[] findTwoMinimalProducts(String file, int balance) {
		int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {

				// process the line
				String trimmed = line.replaceAll("\\s+", "");
				String[] parts = trimmed.split(",");
				if (parts != null && parts.length == 2) {

					String product_name = parts[0];
					int product_price = Integer.parseInt(parts[1]);
					products.put(product_price, product_name);

					// If found new minimum
					if (product_price < min && product_price < balance) {

						// Minimum now becomes second minimum
						secondMin = min;

						// Update minimum
						min = product_price;
					}

					// If current element is > min and < secondMin
					else if ((product_price < secondMin) && product_price != min && (product_price + min) < balance) {
						// Update secondMin
						secondMin = product_price;
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("No Input !\n\nPlease include products.txt File!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return results

		if (min != Integer.MAX_VALUE && secondMin != Integer.MAX_VALUE) {
			int[] vals = new int[] { min, secondMin };
			return vals;
		}

		return null;

	}

	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// default values
		String file = "products.txt";
		int balance = 2500;

		// Assume that if "args" is not null , then :
		// args[0] = filename
		// args[1] = balance

		if (args != null && args.length == 2) {
			file = args[0];
			balance = Integer.parseInt(args[1]);
		}

		int[] minimalValues = findTwoMinimalProducts(file, balance);

		if (minimalValues != null) {
			// build result based on Hashmap
			String result = createResult(minimalValues[0], minimalValues[1]);
			System.out.println(result);
		}

	}

}
