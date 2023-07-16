package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test (enabled = false)
  public void testContactDeletion() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.goTo().addContactPage();
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "TCC", "Russia, 19034, Moscow, Petrovka, 10", "7 (495) 325-24-15", "+7 916-000-00-00", "test@test.com","[none]"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmDeleteContact();
    app.getContactHelper().gotoHomePage();
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
    }
}


