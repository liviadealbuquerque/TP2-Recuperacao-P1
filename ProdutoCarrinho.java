package br.edu.pedidos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProdutoCarrinho {
private int id;
private String title;
private double price;
private int quantity;
private double total;
private double discountPercentage;
public ProdutoCarrinho(){}
public int getId(){return id;}public void setId(int v){id=v;}
public String getTitle(){return title;}public void setTitle(String v){title=v;}
public double getPrice(){return price;}public void setPrice(double v){price=v;}
public int getQuantity(){return quantity;}public void setQuantity(int v){quantity=v;}
public double getTotal(){return total;}public void setTotal(double v){total=v;}
public double getDiscountPercentage(){return discountPercentage;}public void setDiscountPercentage(double v){discountPercentage=v;}
public String toString(){return "ProdutoCarrinho{" + "id="+id + ", " + "title="+title + ", " + "price="+price + ", " + "quantity="+quantity + ", " + "total="+total + ", " + "discountPercentage="+discountPercentage + "}";}
}
