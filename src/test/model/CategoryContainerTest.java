package model;

import model.exceptions.NoTitleException;
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
        try {
            ctyc.addCategory(new Category("name"));
            assertEquals(1, ctyc.getLength());
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }
    }

    @Test
    void testAddNotesFiveHundred() {
        try {
            for (int i = 0; i < 500; i++) {
                String j = Integer.toString(i);
                ctyc.addCategory(new Category(j));
                assertTrue(ctyc.containsCategory(j));
            }

            assertEquals(500, ctyc.getLength());
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }
    }

    @Test
    void testDeleteCategoryByNameNonExistentCategory() {
        try {
            ctyc.addCategory(new Category("name"));
            assertEquals(1, ctyc.getLength());

            ctyc.deleteCategory("a");
            assertEquals(1, ctyc.getLength());
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }
    }

    @Test
    void testDeleteCategoryByNameOnce() {
        try {
            ctyc.addCategory(new Category("name"));
            assertEquals(1, ctyc.getLength());

            ctyc.deleteCategory("name");
            assertEquals(0, ctyc.getLength());
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }
    }

    @Test
    void testDeleteCategoryByNameFiveHundred() {
        try {
            for (int i = 0; i < 500; i++) {
                String j = Integer.toString(i);
                ctyc.addCategory(new Category(j));
            }

            assertEquals(500, ctyc.getLength());
            assertEquals(500, ctyc.getCategories().size());
            assertTrue(ctyc.containsCategory("484"));
            assertTrue(ctyc.containsCategory("251"));
            assertTrue(ctyc.containsCategory("0"));

            ctyc.deleteCategory("484");
            ctyc.deleteCategory("251");
            ctyc.deleteCategory("0");
            // these should result in doing nothing
            ctyc.deleteCategory("693");
            ctyc.deleteCategory("-576");

            assertEquals(497, ctyc.getLength());
            assertFalse(ctyc.containsCategory("484"));
            assertFalse(ctyc.containsCategory("251"));
            assertFalse(ctyc.containsCategory("0"));
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }
    }

    @Test
    void testGetCategoryByName() {
        try {
            for (int i = 0; i < 500; i++) {
                String j = Integer.toString(i);
                ctyc.addCategory(new Category(j));
            }
            Category c = ctyc.getCategoryByName("484");
            assertEquals("484", c.getName());
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }
    }
}

