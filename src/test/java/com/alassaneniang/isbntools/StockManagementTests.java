package com.alassaneniang.isbntools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StockManagementTests {

    @Test
    @DisplayName("Test Can Get A Correct Locator Code")
    void testCanGetACorrectLocatorCode() {
        // test stub
        ExternalISBNDataService webService = (isbn) -> new Book(isbn, "Of Mice And Men", "J. Steinbeck");
        ExternalISBNDataService databaseService = (isbn) -> null;
        StockManager stockManager = new StockManager();
        // inject stub
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);
        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    @DisplayName("Database Is Used If Data Is Present")
    void databaseIsUsedIfDataIsPresent() {
        fail();
    }

    @Test
    @DisplayName("Webservice Is Used If Data Is Not Present In Database")
    void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        fail();
    }

}
