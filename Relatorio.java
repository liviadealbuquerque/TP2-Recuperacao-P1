package br.edu.pedidos;
import java.util.*;import java.util.stream.Collectors;import java.util.function.Function;
public class Relatorio {
    public static List<Carrinho> acimaDeMil(List<Carrinho> carts){return carts.stream().filter(c->c.getTotal()>1000).toList();}
    public static List<String> titulosComDesconto(List<Carrinho> carts){return carts.stream().flatMap(c->c.getProducts().stream()).filter(p->p.getDiscountPercentage()>15).map(ProdutoCarrinho::getTitle).toList();}
    public static List<Carrinho> porEconomia(List<Carrinho> carts){return carts.stream().sorted(Comparator.comparingDouble(Carrinho::getEconomia).reversed()).toList();}
    public static double totalComDesconto(List<Carrinho> carts){return carts.stream().map(Carrinho::getDiscountedTotal).reduce(0.0,Double::sum);}
    public static Map<Integer,Long> porQuantidadeProdutos(List<Carrinho> carts){return carts.stream().collect(Collectors.groupingBy(Carrinho::getTotalProducts,TreeMap::new,Collectors.counting()));}
    public static Optional<Carrinho> maior(List<Carrinho> carts){return carts.stream().max(Comparator.comparingDouble(Carrinho::getTotal));}
    public static void imprimir(List<Carrinho> carts){
        Function<Carrinho,String> formatar=c->String.format(Locale.US,"Carrinho #%d | Usuário: %d | Itens: %d | Total: US$ %.2f",c.getId(),c.getUserId(),c.getTotalQuantity(),c.getTotal());
        System.out.println("Carrinhos com total acima de US$ 1.000:");acimaDeMil(carts).forEach(c->System.out.println(formatar.apply(c)));
        System.out.println("Títulos dos produtos com desconto acima de 15%:");titulosComDesconto(carts).forEach(System.out::println);
        System.out.println("Carrinhos por economia, da maior para a menor:");porEconomia(carts).forEach(c->System.out.printf(Locale.US,"%s | Economia: US$ %.2f%n",formatar.apply(c),c.getEconomia()));
        System.out.printf(Locale.US,"Soma de discountedTotal: US$ %.2f%n",totalComDesconto(carts));
        System.out.println("Quantidade de carrinhos por totalProducts:");porQuantidadeProdutos(carts).forEach((q,n)->System.out.println(q+" produtos distintos: "+n+" carrinhos"));
        maior(carts).ifPresentOrElse(c->System.out.println("Maior carrinho: "+formatar.apply(c)),()->System.out.println("Nenhum carrinho disponível."));
    }
}
