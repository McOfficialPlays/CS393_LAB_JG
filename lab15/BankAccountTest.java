package week15;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Starts each test with a fresh account of 100.0
        account = new BankAccount(100.0);
    }

    /** 1. Add an @AfterEach annotation and method to delete the current bank account to make it available for garbage collection */
    @AfterEach
    void tearDown() {
        account = null;
    }

    @Test
    void testDeposit() {
    /** 2. Adeposit $50 and check that the balance is 150 */
	account.deposit(50);
	assertEquals(150.0, account.getBalance());
    }

    @Test
    void testWithdraw() {
    /** 3. withdraw $40 and check that the balance is $60; remember that each test is done on a fresh instance of bank account */
	account.withdraw(40);
	assertEquals(60, account.getBalance());
    }

    @Test
    void testInvalidDeposit() {
    /** 4. Deposit a negative amount and check if an exception is thrown */
	assertThrows(IllegalArgumentException.class, () -> { 
	    account.deposit(-50);
	});
    }

    @Test
    void testOverdraft() {
    /** 5. Verify that Withdrawing more than the current balance
    throws an exception */
	assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(200);
        });
    }

    @Test
    void testNewNeg() {
    /** 6. Add a test to check that an Exception is thrown when
    trying to create a new bankaccout with a negaive initial balance */
	assertThrows(IllegalArgumentException.class, () -> {
	    new BankAccount(-100);
	});
    }
    @Test
    void testTransfer() { //creates new bank account and transfers 30 into new account from old
	 BankAccount secondAccount = new BankAccount(50.0);
	 account.transfer(secondAccount, 30.0);
	 assertEquals(70.0, account.getBalance());
	 assertEquals(80.0, secondAccount.getBalance());
	
    }
    @Test
    void testTransferOverdraft() {
        BankAccount secondAccount = new BankAccount(50.0);

        assertThrows(IllegalArgumentException.class, () -> {
            account.transfer(secondAccount, 200.0);
        });
    }
    @Test
    void testAllValuesAtLeastTwenty() {
        int[] values = {20, 25, 30, 40, 100};

        for (int value : values) {
            assertTrue(value >= 20); //seeing that they are all greater than 20
        }
    }
    @Test
    void testStringsContainSameCharacters() {
        String strOne = "hello";
        String strTwo = "hello";

        assertEquals(strOne, strTwo);
    }
    //if one test fails it still runs all the others because junit runs all tests independently
    @Test
    void testStringsContainSameCharactersAnyOrder() {
        String strOne = "listen";
        String strTwo = "silent";

        char[] first = strOne.toCharArray();
        char[] second = strTwo.toCharArray();

        java.util.Arrays.sort(first);
        java.util.Arrays.sort(second);

        assertArrayEquals(first, second);
    }
    
}
