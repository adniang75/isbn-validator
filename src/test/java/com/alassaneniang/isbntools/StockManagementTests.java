package com.alassaneniang.isbntools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        String isbn = "0140177396";
        when(databaseService.lookup(isbn))
                .thenReturn(new Book(isbn, "abc", "abc"));
        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);
        stockManager.getLocatorCode(isbn);
        verify(databaseService).lookup(isbn);
        verify(webService, never()).lookup(anyString());
    }

    @Test
    @DisplayName("Webservice Is Used If Data Is Not Present In Database")
    void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        String isbn = "0140177396";
        when(webService.lookup(isbn))
                .thenReturn(new Book(isbn, "abc", "abc"));
        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);
        stockManager.getLocatorCode(isbn);
        verify(databaseService).lookup(anyString());
        verify(webService).lookup(isbn);
    }

}
