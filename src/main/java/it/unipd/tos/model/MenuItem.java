////////////////////////////////////////////////////////////////////
// [Gabriele] [Garbin] [1162293]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem{
    public enum itemType{Panini, Fritti, Bevande};
    itemType tipo;
    double price;
    String name;

    public MenuItem(itemType tipo, double price, String name){
        this.tipo=tipo;
        this.price=price;
        this.name=name;
    }
    public itemType getType() {
        return tipo;
    }
    public double getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
};