# Recuperação P1 — Análise de pedidos com JSON e Streams

Programa Java que consulta GET `https://dummyjson.com/carts?limit=0` com
`java.net.http.HttpClient`, desserializa com Jackson e gera um relatório usando
Streams e Lambdas, sem laços `for` ou `while`.

## Executar
Requisitos: JDK 17+, Maven 3.6.3+ e internet para a API.
```sh
mvn test
mvn compile exec:java
```
Fontes na raiz, com diretórios explicitamente configurados no pom.

## Estrutura
- `RespostaCarrinhos`: carts, total, skip e limit.
- `Carrinho`: id, userId, total, discountedTotal, totalProducts, totalQuantity,
  products e `getEconomia()` = total − discountedTotal.
- `ProdutoCarrinho`: id, title, price, quantity, total e discountPercentage.
- As três classes têm construtor vazio, getters, setters, `toString` e
  `@JsonIgnoreProperties(ignoreUnknown=true)`.
- `ClienteApi`: timeout, verificação de HTTP 200, desserialização e validação
  da estrutura. Falhas de conexão, HTTP ou JSON geram mensagem clara em Main;
  a interrupção preserva o estado de interrupção da thread.

## Consultas
| Operação | Implementação |
|---|---|
| filter | total estritamente maior que US$ 1.000 |
| flatMap + map | títulos de produtos com desconto estritamente maior que 15% |
| sorted | carrinhos por economia decrescente |
| reduce | soma de discountedTotal de todos os carrinhos |
| groupingBy + counting | quantidade de carrinhos para cada totalProducts |
| max + Optional | carrinho de maior total, com tratamento da lista vazia |

Resultados são impressos com `forEach`. Uma função lambda formata:
`Carrinho #[id] | Usuário: [userId] | Itens: [totalQuantity] | Total: US$ [valor]`.
`totalProducts` conta produtos distintos; `totalQuantity` soma as unidades.
Os números exibidos na execução dependem dos dados atuais da API.

## Testes
Fixture determinística verifica desserialização, campos desconhecidos, limites
exatos de 1.000 e 15%, economia, ordenação, redução, agrupamento e maior carrinho.
Também verifica lista vazia, JSON inválido/incompleto e HTTP 503 em servidor local.
