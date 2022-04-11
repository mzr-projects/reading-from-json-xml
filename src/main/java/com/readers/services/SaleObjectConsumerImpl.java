package com.readers.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SaleObjectConsumerImpl implements SaleObjectConsumer {

	private final PriorityOrderAttribute priorityOrderAttribute;

	@Override
	public PriorityOrderAttribute getPriorityOrderAttribute() {
		return this.priorityOrderAttribute;
	}

	@Override
	public void startSaleObjectTransaction() {
		log.info("=========== Transaction Started.");
	}

	@Override
	public void reportSaleObject(int squareMeters, String pricePerSquareMeter, String city, String street, Integer floor) throws TechnicalException {
		log.info("=========== Report");
		log.info("Sqr Meters : {}", squareMeters);
		log.info("Price per Sqr Meters : {}", pricePerSquareMeter);
		log.info("City : {}", city);
		log.info("Street : {}", street);
		log.info("Floor : {}", floor);
	}

	@Override
	public void commitSaleObjectTransaction() {
		log.info("=========== Transaction Committed.");
		log.info("\n");
	}
}
