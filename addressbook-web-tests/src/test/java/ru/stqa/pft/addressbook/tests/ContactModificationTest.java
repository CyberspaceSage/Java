package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withCompany("TCC")
                    .withAddress("Russia, 19034, Moscow, Petrovka, 10").withHomePhone("7 (495) 325-24-15").withMobilePhone("+7 916-000-00-00")
                    .withEmail("test@test.com").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact  = new ContactData().withId(modifiedContact.getId()).withFirstname("Dmitry").withLastname("Dmitriev");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(),before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}

