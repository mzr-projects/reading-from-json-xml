package com.readers.payload;

import lombok.Data;

/**
 * The object must be filled after parsing files
 */
@Data
public class SaleObject {

	private String type;
	private int id;
	private String sizeSqm;
	private String startingPrice;
	private PostalAddress postalAddress;

	@Override
	public int hashCode() {
		return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
				.append(type).append(id).toHashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		SaleObject that = (SaleObject) o;

		return new org.apache.commons.lang3.builder.EqualsBuilder().append(id, that.id)
				.append(type, that.type).isEquals();
	}
}
