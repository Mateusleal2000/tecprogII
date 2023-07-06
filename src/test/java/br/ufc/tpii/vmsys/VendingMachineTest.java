package br.ufc.tpii.vmsys;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import br.ufc.tpii.vmsys.inventory.Inventory;
import br.ufc.tpii.vmsys.exceptions.InsufficientFunds;
import br.ufc.tpii.vmsys.exceptions.InvalidSelection;
import br.ufc.tpii.vmsys.exceptions.OutOfStock;
import br.ufc.tpii.vmsys.inventory.HashMapInventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;


class VendingMachineTest {

	@Test
	void createVendingMachineTest() {
		Inventory inv = new HashMapInventory();
		VendingMachine vm = new VendingMachine(inv);
		assertEquals(vm.withdrawRemainingCoins(), 0.0, 0.00001);
	}
	@Test
	void addCoinsTest() {
		Inventory inv = new HashMapInventory();
		VendingMachine vm = new VendingMachine(inv);
		vm.addCoins(19.8);
		assertEquals(vm.howManyCoinsLeft(), 19.8, 0.0001);
		vm.addCoins(2.0);
		assertEquals(vm.howManyCoinsLeft(), 21.8, 0.0001);
		
		try {
			vm.addCoins(-15.2);
		} catch(IllegalArgumentException e) {
			assertEquals(1,1);
		}
		
		vm.addCoins(0.0);
		assertEquals(vm.howManyCoinsLeft(), 21.8, 0.0001);
	}
	
	@Test
	void coinsLeftTest(){
		Inventory inv = new HashMapInventory();
		VendingMachine vm = new VendingMachine(inv);
		assertEquals(vm.howManyCoinsLeft(), 0.0, 0.00001);
		
		vm.addCoins(15.5);
		assertEquals(vm.howManyCoinsLeft(), 15.5, 0.00001);
	}
	
	
	@Test
	void withdrawCoinsLeftTest(){
		Inventory inv = new HashMapInventory();
		VendingMachine vm = new VendingMachine(inv);
		vm.addCoins(15.5);
		assertEquals(vm.withdrawRemainingCoins(), 15.5, 0.00001);
	}
	
	@Test
	void vendTest() {
		Item it1 = new Item("Halva", 15.5, 10);
		Item it2 = new Item("Crackers", 5.5, 20);
		Item it3 = new Item("Soda", 4.25, 15);
		Item it4 = new Item("Cookies", 10.20, 1);
		Inventory inv = new HashMapInventory();
		try {
			inv.addItem(it1);
			inv.addItem(it2);
			inv.addItem(it3);
			inv.addItem(it4);
		} catch (ItemAlreadyAdded e) {
			e.printStackTrace();
		}
		VendingMachine vm = new VendingMachine(inv);
		try {
			vm.vend("Tea");
		} catch (InvalidSelection | OutOfStock | InsufficientFunds e) {
			assertEquals(e.getMessage(), "Invalid item selection: Tea");
		}
		
		vm.addCoins(3.50);
		
		try {
			vm.vend("Crackers");
		} catch (InvalidSelection | OutOfStock | InsufficientFunds e) {
			assertEquals(e.getMessage(), "Insufficient coins to buy Crackers: "+vm.howManyCoinsLeft());
		}
		
		vm.addCoins(56.20);
		double total_coins = vm.howManyCoinsLeft();
		
		try {
			vm.vend("Cookies");
		} catch (InvalidSelection | OutOfStock | InsufficientFunds e) {
			fail();
		}
		
		assertEquals(vm.howManyCoinsLeft(), total_coins - 10.20);
		
		try {
			vm.vend("Cookies");
		} catch (InvalidSelection | OutOfStock | InsufficientFunds e) {
			assertEquals(e.getMessage(), "Item out of stock: Cookies");
		}
		
		vm.withdrawRemainingCoins();
		
		vm.addCoins(5.5);
		
		try {
			vm.vend("Crackers");
		} catch (InvalidSelection | OutOfStock | InsufficientFunds e) {
			fail();
		}
		
		assertEquals(vm.howManyCoinsLeft(), 0.0);
	}
	
	@Test
	void otherVendTest() {
		
		Item it = new Item("Soda", 1.5, 1);
		Inventory inv = new HashMapInventory();
		try {
			inv.addItem(it);
		} catch (ItemAlreadyAdded e) {
			e.printStackTrace();
		}
		
		assertThrows(OutOfStock.class, () -> {
            VendingMachine vm = new VendingMachine(inv);
            vm.addCoins(100.0);
            vm.vend("Soda");
            vm.vend("Soda");
        });
		
	}
}
