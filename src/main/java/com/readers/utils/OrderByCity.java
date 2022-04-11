package com.readers.utils;

import com.readers.payload.SaleObject;

import java.util.Comparator;

public class OrderByCity implements Comparator<SaleObject> {
	@Override
	public int compare(SaleObject o1, SaleObject o2) {
		return o1.getPostalAddress().getCity().compareTo(o2.getPostalAddress().getCity());
	}
}
