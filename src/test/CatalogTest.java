package test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.category.CarOptionCategory;

public class CatalogTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		Catalog catalog = new Catalog();
		ArrayList<CarOptionCategory> categories = catalog.getAllCategories();
		for (CarOptionCategory category: categories) {
			System.out.println(category);
		}
	}

}
