package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test (enabled = false)
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.goTo().addContactPage();
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "TCC", "Russia, 19034, Moscow, Petrovka, 10", "7 (495) 325-24-15", "+7 916-000-00-00", "test@test.com", "[none]"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContactForm(before.size() - 1);
        ContactData contact  = new ContactData(before.get(before.size() - 1).getId(),"Dmitry", "Dmitrievich", "Dmitriev", "TCC", "Russia, 34580, Saint Petersburg, Stachek, 20", "7 (812) 325-24-15", "+7 916-111-00-11", "test@peter.com", null);
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().updateContactForm();
        app.getContactHelper().returnToContactPage();
        List <ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare (g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

