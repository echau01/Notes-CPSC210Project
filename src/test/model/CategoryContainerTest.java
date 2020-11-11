package model;
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryContainerTest {
    private CategoryContainer ctyc;

    @BeforeEach
    void testInit() {
        ctyc = new CategoryContainer();
    }

    @Test
    void testAddCategoryOnce() {
        ctyc.addCategory(new Category(""));
        assertEquals(1, ctyc.getLength());
    }

    @Test
    void testAddNotesFiveHundred() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            ctyc.addCategory(new Category(j));
            assertTrue(ctyc.containsCategory(j));
        }

        assertEquals(500, ctyc.getLength());
    }

    @Test
    void testDeleteCategoryByNameNonExistentCategory() {
        ctyc.addCategory(new Category(""));
        assertEquals(1, ctyc.getLength());

        ctyc.removeCategoryByName("a");
        assertEquals(1, ctyc.getLength());
    }

    @Test
    void testDeleteCategoryByNameOnce() {
        ctyc.addCategory(new Category(""));
        assertEquals(1, ctyc.getLength());

        ctyc.removeCategoryByName("");
        assertEquals(0, ctyc.getLength());
    }

    @Test
    void testDeleteCategoryByNameFiveHundred() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            ctyc.addCategory(new Category(j));
        }

        assertEquals(500, ctyc.getLength());
        assertTrue(ctyc.containsCategory("484"));
        assertTrue(ctyc.containsCategory("251"));
        assertTrue(ctyc.containsCategory("0"));

        ctyc.removeCategoryByName("484");
        ctyc.removeCategoryByName("251");
        ctyc.removeCategoryByName("0");
        // these should result in doing nothing
        ctyc.removeCategoryByName("693");
        ctyc.removeCategoryByName("-576");

        assertEquals(497, ctyc.getLength());
        assertFalse(ctyc.containsCategory("484"));
        assertFalse(ctyc.containsCategory("251"));
        assertFalse(ctyc.containsCategory("0"));
    }

    @Test
    void testGetCategoryByName() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            ctyc.addCategory(new Category(j));
        }

        Category c = ctyc.getCategoryByName("484");
        Category c2 = ctyc.getCategoryByName("-576");

        assertEquals("484", c.getName());
        assertEquals("", c2.getName());
    }

    @Test
    void testGetCategoryNameByIndex() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            ctyc.addCategory(new Category(j));
        }

        assertEquals("484", ctyc.getCategoryNameByIndex(484));
        assertEquals("251", ctyc.getCategoryNameByIndex(251));
    }

}

 */
