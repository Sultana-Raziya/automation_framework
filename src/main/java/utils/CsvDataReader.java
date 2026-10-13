package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataReader {

	private CsvDataReader() {
	}

	public static Object[][] readLoginData(String filePath) {

		List<Object[]> data = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

			String line;

			// Skip header
			reader.readLine();

			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty()) {
					continue;
				}

				String[] values = line.split(",", -1);

				if (values.length != 3) {
					throw new IllegalArgumentException("Invalid CSV row: " + line);
				}

				data.add(new Object[] { values[0].trim(), values[1].trim(), values[2].trim() });
			}

		} catch (IOException e) {

			throw new RuntimeException("Unable to read login test data: " + filePath, e);
		}

		return data.toArray(new Object[0][]);
	}
}