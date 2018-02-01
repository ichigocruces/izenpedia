package eus.arabyte.android.izenpedia.bd;

/**
 * Created by ichigo on 11/01/18.
 */

public interface Constants {
    String SELECT = " SELECT ";
    String SELECT_ALL = " SELECT * ";
    String FROM = " FROM ";
    String WHERE = " WHERE ";
    String ORDER_BY = " ORDER BY ";

    String UPDATE = " UPDATE ";
    String SET = " SET ";

    String DELETE = " DELETE ";

    String CREATE_TABLE = " CREATE TABLE ";

    String NOT_NULL = " NOT NULL ";
    String UNIQUE = " UNIQUE ";

    String PRIMARY_KEY = " PRIMARY KEY ";
    String AUTOINCREMENT = " AUTOINCREMENT ";
    String FOREIGN_KEY = " FOREIGN KEY ";
    String REFERENCES = " REFERENCES ";
    String ON_DELETE_CASCADE = " ON DELETE CASCADE ";

    String PARENTESIS_ABRIR = " ( ";
    String PARENTESIS_CERRAR = " ) ";
    String COMA = " , ";
    String ESPACIO = " ";
    String IGUAL = " = ";
    String MAYOR_IGUAL = " >= ";
    String MENOR_IGUAL = " <= ";
    String INTERROGACION = " ? ";
    String COMILLA_SIMPLE = "'";

    int FAV_SI = 1;
    int FAV_NO = 0;

    enum ColumnType{
        TEXT("TEXT"),NUMERIC("NUMERIC"),INTEGER("INTEGER"),REAL("REAL");

        private final String valor;

        ColumnType(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }

}
