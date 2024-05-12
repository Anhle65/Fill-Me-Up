package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;
    private Tower towerSameTypeCart;
    private Tower towerDiffTypeCart;
    @BeforeEach
    public void setUpTest(){
        cart = new Cart("water", 20f, 40);
        towerSameTypeCart = new Tower("water", 20, 20, 3f);
        towerDiffTypeCart = new Tower("fire", 20, 20, 3f);
    }
    @Test
    public void testIncrementAmountResourceIntoCartWithInputIsSameType(){
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(20, cart.getCurrentAmountOfCart());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(40, cart.getCurrentAmountOfCart());
    }

    @Test
    public void testIncrementAmountResourceIntoCartWithInputIsNotSameType(){
        cart.incrementAmountResourceIntoCart(towerDiffTypeCart);
        assertEquals(0, cart.getCurrentAmountOfCart());
    }

    @Test
    public void testIncrementAmountResourceIntoCartWhenCartFull(){
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(20, cart.getCurrentAmountOfCart());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(40, cart.getCurrentAmountOfCart());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(40, cart.getCurrentAmountOfCart());
    }

    @Test
    public void testIsCartFilledUp(){
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertFalse(cart.isCartFilledUp());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertTrue(cart.isCartFilledUp());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertTrue(cart.isCartFilledUp());
    }
}
