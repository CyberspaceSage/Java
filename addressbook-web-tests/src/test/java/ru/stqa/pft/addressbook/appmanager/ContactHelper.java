package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {
    public boolean acceptNextAlert = true;
    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void commitToContactForm() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
    }
    public void returnToContactPage() {
      click(By.linkText("home page"));
    }
    public void gotoHomePage() {
        click(By.linkText("home"));
    }
    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"),contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }
    public void confirmDeleteContact() {
      assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }
    public void deleteSelectedContact() {
      click(By.xpath("//input[@value='Delete']"));
    }
    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
    public String closeAlertAndGetItsText() {
      try {
        Alert alert = wd.switchTo().alert();
        String alertText = alert.getText();
        if (acceptNextAlert) {
          alert.accept();
        } else {
          alert.dismiss();
        }
        return alertText;
      } finally {
        acceptNextAlert = true;
      }
    }

    public void editContactForm(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void updateContactForm() {
        click(By.name("update"));
    }

    public void create(ContactData contactData) {
        fillContactForm(contactData,true);
        commitToContactForm();
        returnToContactPage();
    }
    public void modify(ContactData contact) {
       editContactForm(contact.getId());
       fillContactForm(contact,false);
       updateContactForm();
       returnToContactPage();
    }
    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        confirmDeleteContact();
    }
    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.xpath("//*/text()[normalize-space(.)='test3']/parent::*"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List <WebElement> elements = wd.findElements((By.name("entry")));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath("./td[3]")).getText();
            String lastname = element.findElement(By.xpath("./td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}
