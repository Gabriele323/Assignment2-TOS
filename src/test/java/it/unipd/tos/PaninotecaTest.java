////////////////////////////////////////////////////////////////////
// [Gabriele] [Garbin] [1162293]
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;
import org.junit.Test;
import org.junit.Rule;

import java.util.List;
import java.util.ArrayList;

import it.unipd.tos.business.Bill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItem.itemType;

public class PaninotecaTest{

    @Test
    public void MenuItemGetItemTypeTest() {
        MenuItem toTest=new MenuItem(itemType.Fritti, 4.0D, "Arancini");
        assertEquals("Fritti", toTest.getType().toString());
    }

    @Test
    public void MenuItemGetPriceTest() {
        MenuItem toTest=new MenuItem(itemType.Panini, 5.0D, "Panino Primavera");
        assertEquals(5.0D, toTest.getPrice(), 0.0);
    }

    @Test
    public void MenuItemGetNameTest() {
        MenuItem toTest=new MenuItem(itemType.Bevande, 1.5D, "Cola");
        assertEquals("Cola", toTest.getName());
    }

    @Test
    public void BillEmptyListTest() {
        List<MenuItem> menu=null;
        Bill contoMenu=new Bill();
        try{
            contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            assertEquals("Empty Menu", eccezione.getError());
        }
    }

    @Test
    public void BillMoreThan30ItemsTest() {
        List<MenuItem> menu=new ArrayList<>();
        for(int i=0; i<31; i++) {
            menu.add(new MenuItem(itemType.Panini, 3.0D, "Panino Vegetariano"));
        }
        Bill contoMenu=new Bill();
        try{
            contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            assertEquals("Too many items", eccezione.getError());
        }
    }

    @Test
    public void BillLessThan10EuroTaxTest(){
        List<MenuItem> menu=new ArrayList<>();
        for(int i=0; i<5; i++) {
            menu.add(new MenuItem(itemType.Bevande, 1.0D, "Aranciata"));
        }
        Bill contoMenu=new Bill();
        double totale=0.0D;
        try{
            totale=contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            System.err.print(eccezione.getError());
        }
        assertEquals(5.5D, totale, 0.0);
    }

    @Test
    public void BillSandwichDiscountTest(){
        List<MenuItem> menu=new ArrayList<>();
        for(int i=0; i<9; i++) {
            menu.add(new MenuItem(itemType.Panini, 2.0D, "Panino Vegetariano"));
        }
        menu.add(new MenuItem(itemType.Panini, 1.0D, "Panino con mortadella"));
        Bill contoMenu=new Bill();
        double totale=0.0D;
        try{
            totale=contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            System.err.print(eccezione.getError());
        }
        assertEquals(18.5D, totale, 0.0);
    }
	
    @Test
    public void BillLessThan50EuroTest(){
        List<MenuItem> menu=new ArrayList<>();
        for(int i=0; i<10; i++) {
            menu.add(new MenuItem(itemType.Fritti, 2.0D, "Arancini"));
        }
        Bill contoMenu=new Bill();
        double totale=0.0D;
        try{
            totale=contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            System.err.print(eccezione.getError());
        }
        assertEquals(20.0D, totale, 0.0);
    }

    @Test
    public void BillMoreThan50EuroOnlyBevandeTest(){
        List<MenuItem> menu=new ArrayList<>();
        for(int i=0; i<10; i++) {
            menu.add(new MenuItem(itemType.Bevande, 10.0D, "Succo di mela"));
        }
        Bill contoMenu=new Bill();
        double totale=0.0D;
        try{
            totale=contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            System.err.print(eccezione.getError());
        }
        assertEquals(100.0D, totale, 0.0);
    }

    @Test
    public void BillMoreThan50EuroButLessWithoutBevandeTest(){
        List<MenuItem> menu=new ArrayList<>();
        for(int i=0; i<5; i++) {
            menu.add(new MenuItem(itemType.Bevande, 9.0D, "Succo di mela"));
        }
        menu.add(new MenuItem(itemType.Panini, 6.0D, "Panino Primavera"));
        Bill contoMenu=new Bill();
        double totale=0.0D;
        try{
            totale=contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            System.err.print(eccezione.getError());
        }
        assertEquals(51.0D, totale, 0.0);
    }

    @Test
    public void BillMoreThan50EuroDiscountTest(){
        List<MenuItem> menu=new ArrayList<>();
        for(int i=0; i<10; i++) {
            menu.add(new MenuItem(itemType.Fritti, 6.0D, "Olive ascolane"));
        }
        Bill contoMenu=new Bill();
        double totale=0.0D;
        try{
            totale=contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            System.err.print(eccezione.getError());
        }
        assertEquals(54.0D, totale, 0.0);
    }

    @Test
    public void BillMoreThan50EuroDiscountAndSandwichDiscountTest(){
        List<MenuItem> menu=new ArrayList<>();
        for(int i=0; i<10; i++) {
            menu.add(new MenuItem(itemType.Panini, 6.0D, "Panino Vegetariano"));
        }
        menu.add(new MenuItem(itemType.Panini, 4.0D, "Panino con mortadella"));
        Bill contoMenu=new Bill();
        double totale=0.0D;
        try{
            totale=contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            System.err.print(eccezione.getError());
        }
        assertEquals(55.8D, totale, 0.0);
    }
    
    @Test
    public void BillLessThan10EuroAndSandwichDiscountTest(){
        List<MenuItem> menu=new ArrayList<>();
        for(int i=0; i<5; i++){
            menu.add(new MenuItem(itemType.Panini, 1.0D, "Panino con sopressa"));
        }
        menu.add(new MenuItem(itemType.Panini, 2.0D, "Panino vegetariano"));
        Bill contoMenu=new Bill();
        double totale=0.0D;
        try{
            totale=contoMenu.getOrderPrice(menu);
        }catch(TakeAwayBillException eccezione){
            System.err.print(eccezione.getError());
        }
        assertEquals(7.0D, totale, 0.0);
    }
}
