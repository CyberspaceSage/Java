package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testContactCreation() throws Exception {
    app.contact().gotoHomePage();
    Contacts before = app.contact().all();
    app.goTo().addContactPage();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withCompany("TCC")
            .withAddress("Russia, 19034, Moscow, Petrovka, 10").withHomePhone("7 (495) 325-24-15").withMobilePhone("+7 916-000-00-00")
            .withEmail("test@test.com").withGroup("[none]").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
  @Test(enabled = false)
  public void testBadContactCreation() throws Exception {
    app.contact().gotoHomePage();
    Contacts before = app.contact().all();
    app.goTo().addContactPage();
    ContactData contact = new ContactData().withFirstname("Ivan'").withMiddlename("Ivanovich").withLastname("Ivanov").withCompany("TCC")
            .withAddress("Russia, 19034, Moscow, Petrovka, 10").withHomePhone("7 (495) 325-24-15").withMobilePhone("+7 916-000-00-00")
            .withEmail("test@test.com").withGroup("[none]");
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
