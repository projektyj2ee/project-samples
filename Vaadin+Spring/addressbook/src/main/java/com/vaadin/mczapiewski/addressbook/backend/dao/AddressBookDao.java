package com.vaadin.mczapiewski.addressbook.backend.dao;

import java.util.List;
import java.util.Set;

import com.vaadin.mczapiewski.addressbook.backend.model.AddressBook;


/**
 * @author mczapiewski
 *
 */
public interface AddressBookDao {

	/**
	 * Pobiera liste danych z ksiazki adressowej
	 * @return
	 */
	public Set<AddressBook> getAddressBooks();
	
	/**
	 * @param addressBookId
	 * @return
	 */
	public AddressBook findById(int addressBookId);
	
	/**
	 * Update
	 * @param addressBook
	 * @return
	 */
	public boolean update(AddressBook addressBook);
	
	/**
	 * Create
	 * @param addressBook
	 * @return
	 */
	public boolean create(AddressBook addressBook);
	
	/**
	 * @param addressBooks
	 * @return
	 */
	public int[] create(List<AddressBook> addressBooks);
	
	/**
	 * Pobiera count z tabeli 'address_book'
	 * @return
	 */
	public long getAddressBookRecordCount() throws org.springframework.jdbc.BadSqlGrammarException; 
	
	/**
	 * Tworzy tabele address_book w przypadku jej braku w bazie
	 */
	public void createAddressBookTable();
}
