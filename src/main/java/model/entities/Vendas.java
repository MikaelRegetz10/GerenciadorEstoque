package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vendas implements Serializable, TableFormatRelatorio {
    private Integer id;
    private Integer produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private Double lucroTotal;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLucroTotal() {
        return lucroTotal;
    }

    public void setLucroTotal(Double lucroTotal) {
        this.lucroTotal = lucroTotal;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Vendas() {
    }

    public Vendas(Integer id, Double lucroTotal, String nomeProduto, Integer produtoId, Integer quantidade) {
        this.id = id;
        this.lucroTotal = lucroTotal;
        this.nomeProduto = nomeProduto;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    @Override
    public String[] getTableFormatRelatorio() {
        List<Object> list = new ArrayList<>();
        list.add(id);
        list.add(produtoId);
        list.add(nomeProduto);
        list.add(quantidade);
        list.add(lucroTotal);

        String[] stringArray = list.stream()
                .map(Object::toString)
                .toArray(String[]::new);

        return stringArray;
    }
}
