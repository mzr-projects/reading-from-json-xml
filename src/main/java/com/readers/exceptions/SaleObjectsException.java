package com.readers.exceptions;

import com.readers.services.SaleObjectConsumer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaleObjectsException extends SaleObjectConsumer.TechnicalException {

	private String message;

	@Override
	public String getMessage() {
		return message;
	}
}
