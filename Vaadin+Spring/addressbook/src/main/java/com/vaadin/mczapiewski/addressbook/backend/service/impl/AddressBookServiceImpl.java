package com.vaadin.mczapiewski.addressbook.backend.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.mczapiewski.addressbook.backend.dao.AddressBookDao;
import com.vaadin.mczapiewski.addressbook.backend.model.AddressBook;
import com.vaadin.mczapiewski.addressbook.backend.service.AddressBookService;
import com.vaadin.mczapiewski.addressbook.enums.AddressBookRows;

@Service
public class AddressBookServiceImpl implements AddressBookService {

	@Autowired
	private AddressBookDao addressBookDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.tutorial.addressbook.backend.AddressBookService#getAddressBook
	 * ()
	 */
	@Override
	@Deprecated
	public Set<String> getAddressBook() {
		// Set<AddressBook> adressBooks = addressBookDao.getAddressBooks();

		Set<String> list = new HashSet<String>();
		// list.add(adressBooks.get(0).getFname());
		list.add("String 1");
		list.add("String 2");
		list.add("String 3");
		list.add("String 4");
		return list;
	}

	/*
	 * Generate some in-memory example data to play with. In a real application
	 * we could be using SQLContainer, JPAContainer or some other to persist the
	 * data.
	 */
	@Override
	public IndexedContainer getAddressBookDataSource(String[] fieldNames) {
		IndexedContainer ic = new IndexedContainer();
		Set<AddressBook> addressBooks = addressBookDao.getAddressBooks();

		for (String p : fieldNames) {
			ic.addContainerProperty(p, String.class, "");
		}

//		long index = 0L;
		for (AddressBook addressBook : addressBooks) {
			//System.out.println("i: " + index);
			Object id = ic.addItem();
			ic.getContainerProperty(id, AddressBookRows.LP.getValue()).setValue((new Integer(addressBook.getId())).toString());
			ic.getContainerProperty(id, AddressBookRows.FNAME.getValue()).setValue(addressBook.getFname());
			ic.getContainerProperty(id, AddressBookRows.LNAME.getValue()).setValue(addressBook.getLname());
			ic.getContainerProperty(id, AddressBookRows.COMPANY.getValue()).setValue(addressBook.getCompany());
//			index++;
		}

		return ic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.mczapiewski.addressbook.backend.AddressBookService#update(
	 * com.vaadin.mczapiewski.addressbook.backend.model.AddressBook)
	 */
	@Override
	public boolean update(AddressBook addressBook) {
		return addressBookDao.update(addressBook);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.mczapiewski.addressbook.backend.AddressBookService#
	 * createRandomData(java.lang.Integer)
	 */
	@Override
	public int[] createRandomData(long dataCount) {
		// Tworze 10000 wpisow do bazy z losowymi danymi
		String[] fnames = { "Peter", "Alice", "Joshua", "Mike", "Olivia", "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene", "Lisa", "Marge" };
		String[] lnames = { "Smith", "Gordon", "Simpson", "Brown", "Clavel", "Simons", "Verne", "Scott", "Allison", "Gates", "Rowling", "Barks", "Ross", "Schneider", "Tate" };
		String[] lcompanes = { "IBM", "Google", "Oracle", "LG", "HANNS-G", "nVidia", "Sun", "Hyundai", "Samsung", "Fujitsu", "Motorola", "Microsoft", "Mandriva", "Sony", "Optimus" };

		List<AddressBook> adressBooks = new ArrayList<AddressBook>();
		for (long i = 0; i < dataCount; i++) {
			AddressBook addressBookNew = new AddressBook(fnames[(int) (fnames.length * Math.random())], lnames[(int) (lnames.length * Math.random())],
					lcompanes[(int) (lcompanes.length * Math.random())]);
			adressBooks.add(addressBookNew);
		}

		return addressBookDao.create(adressBooks);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.mczapiewski.addressbook.backend.AddressBookService#
	 * getAddressBookRecordCount()
	 */
	@Override
	public long getAddressBookRecordCount() {
		try {
			return addressBookDao.getAddressBookRecordCount();
		} catch (org.springframework.jdbc.BadSqlGrammarException e) { // przechwytuje
																		// wyjatek,
																		// ktory
																		// generowany
																		// jest
																		// podczas
																		// wykonania
																		// selecta
																		// do
																		// nieistniejacej
																		// tabeli
			System.err.println("Tabla 'address_book' nie istnieje. Tworzę nową tabele.");
		}
		addressBookDao.createAddressBookTable();
		return 0L;
	}

}
