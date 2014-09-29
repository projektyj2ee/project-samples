package com.vaadin.mczapiewski.addressbook.backend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.vaadin.mczapiewski.addressbook.backend.dao.AddressBookDao;
import com.vaadin.mczapiewski.addressbook.backend.model.AddressBook;
import com.vaadin.mczapiewski.addressbook.mapper.AddressBookMapper;

/**
 * DAO dla tabeli address_book
 * 
 * @author mczapiewski
 * 
 */
@Component("addressBookDao")
public class AddressBookDaoImpl implements AddressBookDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.tutorial.addressbook.backend.dao.AddressBookDao#getAddressBooks
	 * ()
	 */
	public Set<AddressBook> getAddressBooks() {
		return new HashSet<AddressBook>(jdbc.query("SELECT * FROM address_book ORDER BY id LIMIT 400000 ", new RowMapper<AddressBook>() {

			public AddressBook mapRow(ResultSet rs, int rowNum) throws SQLException {
				AddressBook addressBook = new AddressBook();

				addressBook.setId(rs.getInt("id"));
				addressBook.setFname(rs.getString("fname"));
				addressBook.setLname(rs.getString("lname"));
				addressBook.setCompany(rs.getString("company"));

				return addressBook;
			}
		}));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.mczapiewski.addressbook.backend.dao.AddressBookDao#findById
	 * (int)
	 */
	@Override
	public AddressBook findById(int addressBookId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(addressBookId));

		// return (AddressBook)
		// jdbc.queryForObject("SELECT * FROM address_book WHERE id=:id",
		// namedParameters, new RowMapper() {
		// public Object mapRow(ResultSet resultSet, int rowNum) throws
		// SQLException {
		// return new AddressBook(resultSet.getInt("id"),
		// resultSet.getString("fname"), resultSet.getString("lname"),
		// resultSet.getString("company"));
		// }
		// });
		String sql = "SELECT * FROM address_book WHERE id = :id";

		AddressBook addressBook = (AddressBook) jdbc.queryForObject(sql, namedParameters, new AddressBookMapper());

		return addressBook;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.mczapiewski.addressbook.backend.dao.AddressBookDao#update(
	 * com.vaadin.mczapiewski.addressbook.backend.model.AddressBook)
	 */
	public boolean update(AddressBook addressBook) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(addressBook);
		return jdbc.update("update address_book set fname = :fname, lname = :lname, company = :company where id=:id", params) == 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.mczapiewski.addressbook.backend.dao.AddressBookDao#create(
	 * com.vaadin.mczapiewski.addressbook.backend.model.AddressBook)
	 */
	public boolean create(AddressBook addressBook) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(addressBook);

		return jdbc.update("insert into address_book (fname, lname, company) values (:fname, :lname, :company)", params) == 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.mczapiewski.addressbook.backend.dao.AddressBookDao#create(
	 * java.util.List)
	 */
	public int[] create(List<AddressBook> addressBooks) {

		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(addressBooks.toArray());

		return jdbc.batchUpdate("insert into address_book (fname, lname, company) values (:fname, :lname, :company)", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.mczapiewski.addressbook.backend.dao.AddressBookDao#
	 * getAddressBookRecordCount()
	 */
	@Override
	public long getAddressBookRecordCount() {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(1));
		String sql = "SELECT count(*) FROM address_book";
		long count = jdbc.queryForLong(sql, namedParameters);

		return count;
	}

	@Override
	public void createAddressBookTable() {
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("CREATE TABLE address_book");
		sbQuery.append("(");
		sbQuery.append("  id serial NOT NULL,");
		sbQuery.append("  fname character varying(100),");
		sbQuery.append("  lname character varying(100),");
		sbQuery.append("  company text,");
		sbQuery.append("  CONSTRAINT address_book_pkey PRIMARY KEY (id )");
		sbQuery.append(")");

		jdbc.execute(sbQuery.toString(), new PreparedStatementCallback() {
			@Override
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				return ps.execute();
			}
		});
	}
}
