package com.readers.payload;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SaleObjectPayload {
	private int numberOfSaleObjects;
	private Set<SaleObject> saleObjects = new HashSet<>();
}
