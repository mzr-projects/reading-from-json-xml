package com.readers.services;

import com.readers.services.readers.FileReader;

public interface AdfenixApiService {
	/**
	 * The first two arguments are required but the third is optional
	 *
	 * @param fileName               Name of file that we're going to read from
	 * @param fileReader             Here we pass the type of reader. I have provided two file readers (JSON,XML)
	 * @param priorityOrderAttribute The priority of ordering data
	 */
	void prepareReport(String fileName,
	                   FileReader fileReader,
	                   SaleObjectConsumer.PriorityOrderAttribute priorityOrderAttribute);
}
