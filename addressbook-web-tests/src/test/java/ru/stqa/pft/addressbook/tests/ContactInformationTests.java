package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class ContactInformationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withCompany("TCC")
                    .withAddress("  Russia, 19034, Moscow, Petrovka, 10   ").withHomePhone("7 (495) 325-24-15").withMobilePhone("+7 916-000-00-00")
                    .withWorkPhone("7 (495) 000-00-01").withFaxPhone("7 (495) 000-00-01")
                    .withEmail(" test@test.com ").withEmail2(" test2@test.com ").withEmail3(" test3@test.com ").withGroup("[none]"));
        }
    }
    @Test
    public void testContactInformation() {
        app.contact().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(cleanedAddress(contact.getAddress()), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress())));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter((s) -> !s.equals(""))
                .map(ContactInformationTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }
    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getSecondEmail(), contact.getThirdEmail())
                .filter((s) -> !s.equals(""))
                .map(ContactInformationTests::cleanedEmails)
                .collect(Collectors.joining("\n"));
    }
    public static String cleanedPhones(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
    public static String cleanedAddress(String address) {
        return address.trim().replaceAll("\n", "");
    }
    public static String cleanedEmails(String email) {
        return email.trim();
    }
}