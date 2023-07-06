package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().addContactPage();
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "TCC", "Russia, 19034, Moscow, Petrovka, 10", "7 (495) 325-24-15", "+7 916-000-00-00", "test@test.com", "test3"), true);
        }
        app.getContactHelper().editContactForm();
        app.getContactHelper().fillContactForm(new ContactData("Dmitry", "Dmitrievich", "Dmitriev", "TCC", "Russia, 34580, Saint Petersburg, Stachek, 20", "7 (812) 325-24-15", "+7 916-111-00-11", "test@peter.com", null),false);
        app.getContactHelper().updateContactForm();
        app.getContactHelper().returnToContactPage();
    }
}
