package com.readers.utils;

import com.readers.payload.SaleObject;

import java.util.Comparator;

public class OrderBySqrMeters implements Comparator<SaleObject> {
	@Override
	public int compare(SaleObject o1, SaleObject o2) {
		return Integer.parseInt(o1.getSizeSqm()) - Integer.parseInt(o2.getSizeSqm());
	}
}
