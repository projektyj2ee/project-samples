package com.vaadin.mczapiewski.addressbook.enums;

/**
 * Enum określający kolumny w tabeli ksiązki adresowej
 * 
 * @author mczapiewski
 * 
 */
public enum AddressBookRows {

//	@formatter:off
	LP("L.P."),
	FNAME("First Name"),
	LNAME("Last Name"),
	COMPANY("Company");
//	@formatter:on

	private String value;

	/**
	 * @param columnDescription
	 */
	private AddressBookRows(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
