package com.readers.utils;

import com.readers.payload.SaleObject;

import java.util.Comparator;

public class OrderByPricePerSqrMeters implements Comparator<SaleObject> {
	@Override
	public int compare(SaleObject o1, SaleObject o2) {
		double pricePerSqrSo1 = Double.parseDouble(o1.getStartingPrice().replace(".", "")) / Integer.parseInt(o1.getSizeSqm());
		double pricePerSqrSo2 = Double.parseDouble(o2.getStartingPrice().replace(".", "")) / Integer.parseInt(o2.getSizeSqm());
		return Double.compare(pricePerSqrSo1, pricePerSqrSo2);
	}
}
