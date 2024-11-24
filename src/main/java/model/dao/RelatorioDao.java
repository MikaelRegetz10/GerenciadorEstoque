package model.dao;

import model.entities.AlertaEstoque;
import model.entities.Produto;

import java.util.List;

public interface RelatorioDao {

    void insertRelatorioVenda(Produto prod, Integer quantidade, Double lucro);
    <T> List<T> findAll(String table, Class<T> clazz);
    List<String> findAllColunas(String table);
}
