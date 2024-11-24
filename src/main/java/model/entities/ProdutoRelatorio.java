package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRelatorio implements Serializable, TableFormatRelatorio {
    private Integer id;
    private Integer produtoId;
    private String nome;
    private String descricao;
    private Double precoCompra;
    private Double precoVenda;
    private Integer quantidade;
    private String categoriaNome;
    private String data;


    public ProdutoRelatorio() {
    }

    public ProdutoRelatorio(String categoriaNome, String data, String descricao, Integer id, String nome, Double precoCompra, Double precoVenda, Integer produtoId, Integer quantidade) {
        this.categoriaNome = categoriaNome;
        this.data = data;
        this.descricao = descricao;
        this.id = id;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
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

    @Override
    public String[] getTableFormatRelatorio() {
        List<Object> list = new ArrayList<>();
        list.add(id);
        list.add(produtoId);
        list.add(nome);
        list.add(descricao);
        list.add(quantidade);
        list.add(precoCompra);
        list.add(precoVenda);
        list.add(categoriaNome);
        list.add(data);

        String[] stringArray = list.stream()
                .map(Object::toString)
                .toArray(String[]::new);

        return stringArray;
    }
}
