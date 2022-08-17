package com.alassaneniang.isbntools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StockManagementTests {

    ExternalISBNDataService webService;
    ExternalISBNDataService databaseService;
    String isbn;
    StockManager stockManager;

    @BeforeEach
    void setUp() {
        webService = mock(ExternalISBNDataService.class);
        databaseService = mock(ExternalISBNDataService.class);
        isbn = "0140177396";
        stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);
    }

    @Test
    @DisplayName("Test Can Get A Correct Locator Code")
    void testCanGetACorrectLocatorCode() {
        when(webService.lookup(isbn))
                .thenReturn(new Book(isbn, "Of Mice And Men", "J. Steinbeck"));
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    @DisplayName("Database Is Used If Data Is Present")
    void databaseIsUsedIfDataIsPresent() {
        when(databaseService.lookup(isbn))
                .thenReturn(new Book(isbn, "abc", "abc"));
        when(webService.lookup(isbn))
                .thenReturn(null);
        stockManager.getLocatorCode(isbn);
        verify(databaseService).lookup(isbn);
        verify(webService, never()).lookup(anyString());
    }

    @Test
    @DisplayName("Webservice Is Used If Data Is Not Present In Database")
    void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        when(webService.lookup(isbn))
                .thenReturn(new Book(isbn, "abc", "abc"));
        stockManager.getLocatorCode(isbn);
        verify(databaseService).lookup(anyString());
        verify(webService).lookup(isbn);
    }

}
