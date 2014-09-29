Addressbook Tutorial
====================

Przykładowa aplikacja webowa wykorzystująca Vaadin'a i Spring Framework (z AspecJ). 
Połączenie do bazy postgresowej definiowane jest w pliku jdbc.properties.
Przy pierwszym uruchomieniu aplikacja tworzy odpowiednie tabele w bazie i wypełnia je przykładowymi danymi.

Running the example
-------------------
mvn jetty:run


Importing in Eclipse
--------------------
Make sure you have "Eclipse IDE for Java EE Developers" and Maven integration "m2e-wtp" installed. You will get Eclipse from http://eclipse.org/downloads/ and plugins through Help -> Eclipse Marketplace... menu

To checkout and run the project from Eclipse, do:
- File -> Import...
- Check out Maven Projects from CMS
- Choose Git from SCM menu and set URL to git://github.com/vaadin/addressbook.git
  - If you do not see "Git" in the SCM menu, click "Find more SCM connectors in the m2e Marketplace" and install "m2e-egit"
- Now you should have an "addressbook" project in your workspace
- To run it, right click and choose Run As -> Run on Server
- Start experimenting

Note that if you are missing EGit plugin, "Maven SCM Handler for EGit" or a local server to run the address book on, you will be asked to install these while doing the above.
