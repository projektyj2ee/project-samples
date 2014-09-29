package com.vaadin.mczapiewski.addressbook.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

/**
 * @author mczapiewski
 *
 */
@WebServlet(urlPatterns = "/*")
@VaadinServletConfiguration(ui = AddressbookUI.class, productionMode = false)
public class Servlet extends VaadinServlet {

	private static final long serialVersionUID = 1L;

}
