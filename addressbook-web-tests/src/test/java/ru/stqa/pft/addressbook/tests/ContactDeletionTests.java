package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0) {
      app.goTo().addContactPage();
      app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withCompany("TCC")
              .withAddress("Russia, 19034, Moscow, Petrovka, 10").withHomePhone("7 (495) 325-24-15").withMobilePhone("+7 916-000-00-00")
              .withEmail("test@test.com").withGroup("test3"));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.contact().gotoHomePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(),before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
    }
}


