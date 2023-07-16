package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test (enabled = false)
  public void testContactCreation() throws Exception {
    app.goTo().groupPage();
    if (! app.getContactHelper().isThereAGroup()) {
      app.group().create(new GroupData().withName("test3"));
    }
    app.getContactHelper().gotoHomePage();
    List <ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Ivan", "Ivanovich", "Ivanov", "TCC", "Russia, 19034, Moscow, Petrovka, 10", "7 (495) 325-24-15", "+7 916-000-00-00", "test@test.com", "test3");
    app.goTo().addContactPage();
    app.getContactHelper().createContact(contact);
    app.getContactHelper().gotoHomePage();
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare (g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
