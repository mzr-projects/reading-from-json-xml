package com.readers.services;

import com.readers.exceptions.SaleObjectsException;
import com.readers.payload.SaleObject;
import com.readers.payload.SaleObjectPayload;
import com.readers.services.readers.FileReader;
import com.readers.utils.OrderByCity;
import com.readers.utils.OrderByPricePerSqrMeters;
import com.readers.utils.OrderBySqrMeters;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the AdfenixApiService you can create any class as you wish and implement your own service
 */
@Slf4j
public class AdfenixApiServiceImpl implements AdfenixApiService {

	/**
	 * @param fileName   Name of file that we're going to read from
	 * @param fileReader Here we pass the type of reader I have provided two file readers (JSON,XML)
	 */
	public void prepareReport(String fileName,
	                          FileReader fileReader,
	                          SaleObjectConsumer.PriorityOrderAttribute priorityOrderAttribute) {

		if (fileName == null || fileReader == null) {
			log.error("FileName or FileReader can not be empty.");
			throw new SaleObjectsException("FileName or FileReader can not be empty.");
		}

		SaleObjectConsumer saleObjectConsumer = new SaleObjectConsumerImpl(priorityOrderAttribute);
		SaleObjectPayload saleObjectPayload = fileReader.read(fileName);

		List<SaleObject> saleObjectList = new ArrayList<>(saleObjectPayload.getSaleObjects());
		if (saleObjectConsumer.getPriorityOrderAttribute() != null) {
			orderReport(saleObjectConsumer.getPriorityOrderAttribute(), saleObjectList);
		}

		DecimalFormat pricePerSqrMeterFormat = new DecimalFormat("0.000");

		saleObjectList.forEach((saleObject -> {

			int sqrMeter = Integer.parseInt(saleObject.getSizeSqm());

			StringBuilder priceBuilder = new StringBuilder();
			if (sqrMeter > 0) {
				double pricePerSqrMeter =
						Double.parseDouble(saleObject.getStartingPrice().replace(".", "")) / sqrMeter;
				String price = pricePerSqrMeterFormat.format(pricePerSqrMeter);
				priceBuilder.append(price);
				priceBuilder.append("USD");
			} else {
				throw new SaleObjectsException("Invalid square meter value.");
			}

			String city = null;
			String street = null;
			Integer floor = null;
			if (saleObject.getPostalAddress() != null) {
				city = saleObject.getPostalAddress().getCity();
				street = saleObject.getPostalAddress().getStreet();

				if (saleObject.getPostalAddress().getFloor() != null) {
					floor = saleObject.getPostalAddress().getFloor();
				}
			}

			saleObjectConsumer.startSaleObjectTransaction();
			saleObjectConsumer.reportSaleObject(sqrMeter, priceBuilder.toString(), city, street, floor);
			saleObjectConsumer.commitSaleObjectTransaction();
		}));
	}

	/**
	 * @param priorityOrderAttribute Here we pass sort parameter
	 * @param saleObjectList         The data must be sorted
	 */
	private void orderReport(SaleObjectConsumer.PriorityOrderAttribute priorityOrderAttribute,
	                         List<SaleObject> saleObjectList) {
		switch (priorityOrderAttribute) {
			case City -> orderByCity(saleObjectList);
			case SquareMeters -> orderBySqrMeters(saleObjectList);
			case PricePerSquareMeter -> orderByPricePerSqm(saleObjectList);
		}
	}

	/**
	 * Sort by city
	 *
	 * @param saleObjectList Data
	 */
	private void orderByCity(List<SaleObject> saleObjectList) {
		saleObjectList.sort(new OrderByCity());
	}

	/**
	 * Sort by Square Meters
	 *
	 * @param saleObjectList Data
	 */
	private void orderBySqrMeters(List<SaleObject> saleObjectList) {
		saleObjectList.sort(new OrderBySqrMeters());
	}

	/**
	 * Sort by PricePerSquareMeters
	 *
	 * @param saleObjectList Data
	 */
	private void orderByPricePerSqm(List<SaleObject> saleObjectList) {
		saleObjectList.sort(new OrderByPricePerSqrMeters());
	}
}
