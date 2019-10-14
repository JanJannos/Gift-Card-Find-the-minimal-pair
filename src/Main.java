import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

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

		int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {

				// process the line
				String trimmed = line.replaceAll("\\s+", "");
				String[] parts = trimmed.split(",");
				if (parts != null && parts.length == 2) {

					String product_name = parts[0];
					int product_price = Integer.parseInt(parts[1]);

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

			System.out.println("Min : " + min + " , Second : " + secondMin);
		} catch (FileNotFoundException e) {

			System.out.println("No Input !\n\nPlease include products.txt File!");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
