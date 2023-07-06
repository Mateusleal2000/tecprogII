package br.ufc.tpii.vmsys.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VmSysExceptionsTest {

	@Test
	void InsufficientFundsTest() {
		assertThrows(InsufficientFunds.class, ()->{throw new InsufficientFunds();});
	}
	
	@Test
	void InvalidSelectionTest() {
		assertThrows(InvalidSelection.class, ()->{throw new InvalidSelection();});
	}
	
	@Test
	void OutOfStockTest() {
		assertThrows(OutOfStock.class, ()->{throw new OutOfStock();});
	}

}
