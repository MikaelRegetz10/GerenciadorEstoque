package model.dao;

import model.impl.RelatorioDaoJDBC;
import model.impl.CategoriaDaoJDBC;
import model.impl.ProdutoDaoJDBC;
import util.DB;

public class DaoFactory {

    public static ProdutoDao createProdutoDao(){
        return new ProdutoDaoJDBC(DB.getConnection());
    }

    public static CategoriaDao createCategoriaDao(){
        return new CategoriaDaoJDBC(DB.getConnection());
    }

    public static RelatorioDao createRelatorioDao(){
        return new RelatorioDaoJDBC(DB.getConnection());
    }

}
