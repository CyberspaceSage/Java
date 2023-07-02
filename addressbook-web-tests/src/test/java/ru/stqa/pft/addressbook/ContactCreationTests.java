package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation()  {
    gotoGroupPage();
    addContactPage();
    fillContactForm(new ContactData("Ivan", "Ivanovich", "Ivanov", "TCC", "Russia, 19034, Moscow, Petrovka, 10", "7 (495) 325-24-15", "+7 916-000-00-00", "test@test.com"));
    commitToContactForm();
    returnToContactPage();
  }
}
