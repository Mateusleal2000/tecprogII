package br.ufc.tpii.vmsys.inventory.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemExceptionsTest {

	@Test
	void ItemAlreadyAddedTest() {
		assertThrows(ItemAlreadyAdded.class, ()->{throw new ItemAlreadyAdded();});
	}
	@Test
	void ItemNotFoundTest() {
		assertThrows(ItemNotFound.class, ()->{throw new ItemNotFound();});
	}
}
