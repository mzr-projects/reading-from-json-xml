package com.readers;

import com.readers.services.AdfenixApiService;
import com.readers.services.AdfenixApiServiceImpl;
import com.readers.services.SaleObjectConsumer;
import com.readers.services.readers.JsonFileReader;
import org.junit.jupiter.api.Test;

class SalesObjectAppTest {

	@Test
	void shouldAnswerWithTrue() {
		AdfenixApiService adfenixApiServiceImpl1 = new AdfenixApiServiceImpl();
		adfenixApiServiceImpl1.prepareReport("SaleObjects.json", new JsonFileReader(),
				SaleObjectConsumer.PriorityOrderAttribute.City);
	}
}
