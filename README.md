# contacts-rest-api
A simple rest api to manage contacts.


The goal of this exercise is to pratice TDD and build a 'clean' rest api.

The contact rest api should have these end points:

- register as a user
- enable/disable sharing your contact list
- add a contact to your contact list
- remove a contact form your contact list
- display a contact information
- display a contact's contacts when the contact accepts to share his contacts whith his contacts (you should not appear in your contact contacts list)

a contact is defined with his name, phone number and an optional nickname

a user is defined with a phone number and a name

a phone number is a string of 10 digits


To do this I will use: java + sparkjava + rest-assured + google-guice.

You can fell free to use any other language and frameworks.



# First step --> setup project and dependencies
