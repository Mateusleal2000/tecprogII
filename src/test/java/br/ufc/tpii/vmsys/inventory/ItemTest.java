package br.ufc.tpii.vmsys.inventory;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTest {

	@Test
	void createItemTest() {
		Item it = new Item("Halva",15.5, 9);
		assertEquals(it.getName(), "Halva");
		assertEquals(it.getPrice(), 15.5, 0.001);
		assertEquals(it.getCount(), 9);
		
		Item it2 = new Item("Cookies", 0.0, 0);
		assertEquals(it2.getName(), "Cookies");
		assertEquals(it2.getPrice(), 0.0, 0.001);
		assertEquals(it2.getCount(), 0);
	}
	
	@Test
	void createBadItemTest() {
		try {
			Item it = new Item("Cookies", -1.5, 15);
		} catch(IllegalArgumentException e) {
			assertEquals(1,1);
		}
		
		try {
			Item it2 = new Item("Cookies", 1.5, -15);
		} catch(IllegalArgumentException e) {
			assertEquals(1,1);
		}
		
	}
	
	@Test
	void decItemTest() {
		Item it = new Item("Halva",15.5, 5);
		Item it2 = new Item("Cookies", 10.0, 0);
		
		it.decCount();
		
		assertEquals(it.getCount(), 4);

		it2.decCount();
		
		assertEquals(it2.getCount(), 0);
	}
	
	@Test
	void incItemTest() {
		Item it = new Item("Halva",15.5, 5);
		it.incCount();
		assertEquals(it.getCount(), 6);
		for(int i = 0; i < 4; i++) {
			it.incCount();
		}
		assertEquals(it.getCount(), 10);
	}
	
	@Test
	void setPriceTest() {
		Item it = new Item("Halva",15.5, 5);
		try {
			it.setPrice(7.50);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(it.getPrice(), 7.50);
		
		try {
			it.setPrice(-15.5);
		}catch(IllegalArgumentException e) {
			assertEquals(1,1);
		}
		Item it2 = new Item("Crackers",14.4, 5);
		try {
			it2.setPrice(0.0);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(it2.getPrice(), 0.0,0.00001);
	}
	
	@Test
	void getPriceTest() {
		Item it = new Item("Halva",15.5, 5);
		it.setPrice(7.50);
		assertEquals(it.getPrice(), 7.50);
	}
	
	@Test
	void getNameTest() {
		Item it = new Item("Halva",15.5, 5);
		assertEquals(it.getName(), "Halva");
	}

}
