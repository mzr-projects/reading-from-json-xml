package com.readers.services.readers;

import com.readers.exceptions.SaleObjectsException;
import com.readers.payload.SaleObjectPayload;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This reader read from JSON and create an object to be analyzed
 */
@Slf4j
@Data
public class JsonFileReader implements FileReader {

	@Override
	public SaleObjectPayload read(String fileName) {
		SaleObjectPayload saleObjectPayload;
		Gson gson = new Gson();
		try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
			saleObjectPayload = gson.fromJson(reader, SaleObjectPayload.class);
		} catch (IOException e) {
			log.error("IOException, Error reading from file.");
			throw new SaleObjectsException("IOException, Error reading from file.");
		} catch (JsonSyntaxException exception) {
			log.error("It's not a valid json file.");
			throw new SaleObjectsException("It's not a valid json file.");
		}
		return saleObjectPayload;
	}
}
