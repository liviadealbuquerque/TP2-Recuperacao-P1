package br.edu.pedidos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class RespostaCarrinhos {
private java.util.List<Carrinho> carts;
private int total;
private int skip;
private int limit;
public RespostaCarrinhos(){}
public java.util.List<Carrinho> getCarts(){return carts;}public void setCarts(java.util.List<Carrinho> v){carts=v;}
public int getTotal(){return total;}public void setTotal(int v){total=v;}
public int getSkip(){return skip;}public void setSkip(int v){skip=v;}
public int getLimit(){return limit;}public void setLimit(int v){limit=v;}
public String toString(){return "RespostaCarrinhos{" + "carts="+carts + ", " + "total="+total + ", " + "skip="+skip + ", " + "limit="+limit + "}";}
}
