package com.vaadin.mczapiewski.addressbook.backend.model;

/**
 * Model dla tabeli address_book
 * 
 * @author mczapiewski
 * 
 */
public class AddressBook {

	private Integer id;
	private String fname;
	private String lname;
	private String company;

	/**
	 * Constructor
	 */
	public AddressBook() {
	}

	/**
	 * Constructor
	 * 
	 * @param fname
	 * @param lname
	 * @param company
	 */
	public AddressBook(String fname, String lname, String company) {
		this.fname = fname;
		this.lname = lname;
		this.company = company;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param fname
	 * @param lname
	 * @param company
	 */
	public AddressBook(Integer id, String fname, String lname, String company) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.company = company;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "AddressBook [id=" + id + ", fname=" + fname + ", lname=" + lname + ", company=" + company + "]";
	}

}
