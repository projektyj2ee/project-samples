package com.vaadin.mczapiewski.addressbook.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vaadin.mczapiewski.addressbook.backend.model.AddressBook;

/**
 * Mapper dla tabeli address_book
 * @author mczapiewski
 *
 */
public class AddressBookMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		AddressBook addressBook = new AddressBook();

		addressBook.setId(rs.getInt("id"));
		addressBook.setFname(rs.getString("fname"));
		addressBook.setLname(rs.getString("lname"));
		addressBook.setCompany(rs.getString("company"));

		return addressBook;
	}

}
