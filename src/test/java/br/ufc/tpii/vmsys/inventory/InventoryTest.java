package br.ufc.tpii.vmsys.inventory;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;

class InventoryTest {

	@Test
	void createInventoryTest() {
		HashMapInventory inv = new HashMapInventory();
		assertEquals(inv.numberOfItems(),0);
	}
	
	@Test
	void addToInvTest() {
		HashMapInventory inv = new HashMapInventory();
		Item it = new Item("Crackers", 5.50, 20);
		try {
			inv.addItem(it);
		}
		catch(ItemAlreadyAdded e){
			fail();
		}	
	}
	
	@Test
	void addRepeatedItemToInvTest() {
		HashMapInventory inv = new HashMapInventory();
		Item it = new Item("Crackers", 5.50, 20);
		try {
			inv.addItem(it);
		} catch (ItemAlreadyAdded e) {
			// TODO Auto-generated catch block
			fail();
		}
		
		try {
			inv.addItem(it);
		} catch (ItemAlreadyAdded e) {
			assertEquals("Item already added: " + it.getName(),e.getMessage());
		}
	}
	
	@Test
	void removeItemFromInvTest() {
		HashMapInventory inv = new HashMapInventory();
		Item it = new Item("Crackers", 5.50, 20);
		Item it2 = new Item("Halva", 15.50, 9);
		try {
			inv.addItem(it);
			inv.addItem(it2);
		} catch (ItemAlreadyAdded e) {
			e.printStackTrace();
		}
		
		try {
			inv.removeItem("Crackers");
		} catch (ItemNotFound e) {
			fail();
		}
		
		assertEquals(inv.numberOfItems(), 1);
	}
	
	@Test
	void removeNonExistentItemFromInvTest() {
		HashMapInventory inv = new HashMapInventory();
		Item it = new Item("Crackers", 5.50, 20);
		try {
			inv.addItem(it);
		} catch (ItemAlreadyAdded e) {
			fail();
		}
		
		try {
			inv.removeItem("Halva");
		} catch (ItemNotFound e) {
			assertEquals("Item not found: Halva", e.getMessage());
		}
	}
	
	@Test
	void getItemTest() {
		HashMapInventory inv = new HashMapInventory();
		Item it = new Item("Crackers", 5.50, 20);
		Item it2 = new Item("Halva", 15.50, 9);
		
		try {
			inv.addItem(it);
			inv.addItem(it2);
		} catch (ItemAlreadyAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Item returnedCracker = inv.getItem("Crackers");
			assertEquals(returnedCracker.getName(), "Crackers");
			
			Item returnedHalva = inv.getItem("Halva");
			assertEquals(returnedHalva.getName(), "Halva");
		} catch (ItemNotFound e) {
			fail();
		}
	}
	
	@Test
	void getUnexistentItemTest() {
		HashMapInventory inv = new HashMapInventory();
		Item it = new Item("Crackers", 5.50, 20);
		Item it2 = new Item("Halva", 15.50, 9);

		try {
			inv.addItem(it);
			inv.addItem(it2);
		} catch (ItemAlreadyAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Item returnedFakeItem = inv.getItem("Fake");
			System.out.println("Should not have returned this "+ returnedFakeItem.getName()+" item");
		} catch (ItemNotFound e) {
			assertEquals("Item not found: Fake", e.getMessage());
		}
	}

}
