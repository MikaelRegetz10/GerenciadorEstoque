package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movimentacao implements Serializable, TableFormatRelatorio {
    private Integer id;
    private Integer produtoId;
    private String tipoMovimentacao;
    private Integer quantidade;
    private String data;
    
    public Movimentacao() {
    }

    public Movimentacao(String data, Integer id, Integer produtoId, String tipoMovimentacao, Integer quantidade) {
        this.data = data;
        this.id = id;
        this.produtoId = produtoId;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
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
        list.add(tipoMovimentacao);
        list.add(quantidade);
        list.add(data);

        String[] stringArray = list.stream()
                .map(Object::toString)
                .toArray(String[]::new);

        return stringArray;
    }
    @Override
    public String toString() {
        return "Movimentacao{" +
                "data='" + data + '\'' +
                ", id=" + id +
                ", produtoId=" + produtoId +
                ", tipoMovimentacao='" + tipoMovimentacao + '\'' +
                '}';
    }


}
