package com.readers.payload;

import lombok.Data;

@Data
public class PostalAddress {
	private String city;
	private String street;
	private Integer floor;
}
