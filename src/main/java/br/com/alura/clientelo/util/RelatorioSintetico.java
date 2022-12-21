package br.com.alura.clientelo.util;

public class RelatorioSintetico {
//    private int totalDeProdutosVendidos= 0;
//    private int totalDePedidosRealizados= 0;
//    private BigDecimal montanteDeVendas = BigDecimal.ZERO;
//    private PedidoEstatistica pedidoMaisBarato = null;
//
//    private PedidoEstatistica pedidoMaisCaro = null;
//    private int totalDeCategorias = 0;
//    public RelatorioSintetico getAll(Map<Pedido, PedidoEstatistica> mapPedidos) {
//        String[] categoriasProcessadas = new String[10];
//        List<PedidoEstatistica> pedidos = mapPedidos.values().stream().toList();
//        for (int i = 0; i < pedidos.size(); i++) {
//            PedidoEstatistica pedidoAtual = pedidos.get(i);
//
//            if (pedidoAtual == null) {
//                break;
//            }
//
//            if (isPedidoMaisBarato(pedidoAtual)) {
//                this.pedidoMaisBarato = pedidoAtual;
//            }
//
//            if (isPedidoMaisCaro(pedidoAtual)) {
//                this.pedidoMaisCaro = pedidoAtual;
//            }
//
//            this.montanteDeVendas = addToMontanteDeVendas(pedidoAtual);
//            this.totalDeProdutosVendidos += pedidoAtual.getQuantidade();
//            this.totalDePedidosRealizados++;
//
//            boolean jahProcessouCategoria = false;
//            for (int j = 0; j < categoriasProcessadas.length; j++) {
//                //TODO: refatorar para atender à nova modelagem
////                if (categoriaJaProcessada(pedidoAtual, categoriasProcessadas[j])) {
////                    jahProcessouCategoria = true;
////                }
//            }
//
//            if (!jahProcessouCategoria) {
//                categoriasProcessadas = addToCategoriasProcessadas(categoriasProcessadas, pedidoAtual);
//            }
//        }
//        return this;
//    }
//
//    //TODO: refatorar para atender à nova modelagem
//    private String[] addToCategoriasProcessadas(String[] categoriasProcessadas, PedidoEstatistica pedidoAtual) {
////        this.totalDeCategorias++;
////        String[] result = categoriasProcessadas;
////
////        if (categoriasProcessadas[categoriasProcessadas.length - 1] != null) {
////            result = getCopiaExpandidaArraydeCategoriasJaProcessadas(categoriasProcessadas);
////        } else {
////            for (int k = 0; k < categoriasProcessadas.length; k++) {
////                if (categoriasProcessadas[k] == null) {
////                    result[k] = pedidoAtual.getPedido().getCategoria();
////                    break;
////                }
////            }
////        }
////        return result;
//        return null;
//    }
//
//    private static String[] getCopiaExpandidaArraydeCategoriasJaProcessadas(String[] categoriasProcessadas) {
//        return Arrays.copyOf(categoriasProcessadas, categoriasProcessadas.length * 2);
//    }
//    //TODO: refatorar para atender à nova modelagem
////    private static boolean categoriaJaProcessada(PedidoEstatistica pedidoAtual, String categoriasProcessadas) {
////        return pedidoAtual.getPedido().getCategoria().equalsIgnoreCase(categoriasProcessadas);
////    }
//
//    private BigDecimal addToMontanteDeVendas(PedidoEstatistica pedidoAtual) {
//        return this.montanteDeVendas.add(pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())));
//    }
//
//    private boolean isPedidoMaisCaro(PedidoEstatistica pedidoAtual) {
//        return this.pedidoMaisCaro == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade()))) > 0;
//    }
//
//    private boolean isPedidoMaisBarato(PedidoEstatistica pedidoAtual) {
//        return this.pedidoMaisBarato == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade()))) < 0;
//    }
//
//    public int getTotalDeProdutosVendidos() {
//        return totalDeProdutosVendidos;
//    }
//
//    public int getTotalDePedidosRealizados() {
//        return totalDePedidosRealizados;
//    }
//
//    public BigDecimal getMontanteDeVendas() {
//        return montanteDeVendas;
//    }
//
//    public PedidoEstatistica getPedidoMaisBarato() {
//        return pedidoMaisBarato;
//    }
//
//    public PedidoEstatistica getPedidoMaisCaro() {
//        return pedidoMaisCaro;
//    }
//
//    public int getTotalDeCategorias() {
//        return totalDeCategorias;
//    }

}
