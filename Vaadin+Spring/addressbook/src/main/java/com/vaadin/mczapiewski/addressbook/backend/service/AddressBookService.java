package com.vaadin.mczapiewski.addressbook.backend.service;

import java.util.Set;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.mczapiewski.addressbook.backend.model.AddressBook;

/**
 * @author mczapiewski
 *
 */
public interface AddressBookService {

	/**
	 * Metoda testowa
	 * @return
	 */
	@Deprecated
	public Set<String> getAddressBook();
	
	/**
	 * DataSource dla glownej tabeli
	 * @param fieldNames
	 * @return
	 */
	public IndexedContainer getAddressBookDataSource(String[] fieldNames);
	
	/**
	 * @param addressBook
	 * @return
	 */
	public boolean update(AddressBook addressBook);
	
	/**
	 * Tworzy randomowe dane w pustej bazie
	 * @param addressBook
	 * @return
	 */
	public int[] createRandomData(long dataCount);
	
	/**
	 * Pobiera count z tabeli 'address_book'
	 * @return
	 */
	public long getAddressBookRecordCount();
	
}
