package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.contact().all().size() == 0) {
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testContactCreation() throws Exception {

    app.contact().gotoHomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withCompany("TCC")
            .withAddress("Russia, 19034, Moscow, Petrovka, 10").withHomePhone("7 (495) 325-24-15").withMobilePhone("+7 916-000-00-00")
            .withEmail("test@test.com").withGroup("test3");
    app.goTo().addContactPage();
    app.contact().create(contact);
    app.contact().gotoHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
