package br.edu.pedidos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Carrinho {
private int id;
private int userId;
private double total;
private double discountedTotal;
private int totalProducts;
private int totalQuantity;
private java.util.List<ProdutoCarrinho> products;
public Carrinho(){}
public int getId(){return id;}public void setId(int v){id=v;}
public int getUserId(){return userId;}public void setUserId(int v){userId=v;}
public double getTotal(){return total;}public void setTotal(double v){total=v;}
public double getDiscountedTotal(){return discountedTotal;}public void setDiscountedTotal(double v){discountedTotal=v;}
public int getTotalProducts(){return totalProducts;}public void setTotalProducts(int v){totalProducts=v;}
public int getTotalQuantity(){return totalQuantity;}public void setTotalQuantity(int v){totalQuantity=v;}
public java.util.List<ProdutoCarrinho> getProducts(){return products;}public void setProducts(java.util.List<ProdutoCarrinho> v){products=v;}
public double getEconomia(){return total-discountedTotal;}
public String toString(){return "Carrinho{" + "id="+id + ", " + "userId="+userId + ", " + "total="+total + ", " + "discountedTotal="+discountedTotal + ", " + "totalProducts="+totalProducts + ", " + "totalQuantity="+totalQuantity + ", " + "products="+products + "}";}
}
