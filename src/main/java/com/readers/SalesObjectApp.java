package com.readers;

import com.readers.services.AdfenixApiService;
import com.readers.services.AdfenixApiServiceImpl;
import com.readers.services.SaleObjectConsumer;
import com.readers.services.readers.JsonFileReader;
import com.readers.services.readers.XmlFileReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SalesObjectApp {
	/**
	 * Here in this method we create an implementation of AdfenixApiService and use the prepareReport method and pass it
	 * file_name and an Implementation of FileReader (it can be Json,Xml,Csv ... reader), Here I have provided two
	 * Readers (JSON,XML) and the priority of ordering, The first two arguments are required but the third is optional
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("================================== JSON without priority ==============================  \n");
		AdfenixApiService adfenixApiServiceImpl1 = new AdfenixApiServiceImpl();
		adfenixApiServiceImpl1.prepareReport("SaleObjects.json", new JsonFileReader(),
				null);

		log.info("================================== JSON with Priority ==============================  \n");
		AdfenixApiService adfenixApiServiceImpl2 = new AdfenixApiServiceImpl();
		adfenixApiServiceImpl2.prepareReport("SaleObjects.json", new JsonFileReader(),
				SaleObjectConsumer.PriorityOrderAttribute.City);

		log.info("================================== XML PricePerSquareMeter Priority ==============================");
		AdfenixApiService adfenixApiServiceImpl3 = new AdfenixApiServiceImpl();
		adfenixApiServiceImpl3.prepareReport("SaleObjects.xml", new XmlFileReader(),
				SaleObjectConsumer.PriorityOrderAttribute.PricePerSquareMeter);

		log.info("================================== XML SquareMeters Priority ==============================");
		AdfenixApiService adfenixApiServiceImpl4 = new AdfenixApiServiceImpl();
		adfenixApiServiceImpl4.prepareReport("SaleObjects.xml", new XmlFileReader(),
				SaleObjectConsumer.PriorityOrderAttribute.SquareMeters);

		log.info("================================== XML no Priority ==============================");
		AdfenixApiService adfenixApiServiceImpl5 = new AdfenixApiServiceImpl();
		adfenixApiServiceImpl5.prepareReport("SaleObjects.xml", new XmlFileReader(),
				null);
	}
}
