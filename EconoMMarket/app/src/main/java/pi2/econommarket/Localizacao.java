package pi2.econommarket;

import java.io.Serializable;

public class Localizacao implements Serializable {
    public String endereco;
    public String estado;
    public String latitude;
    public String longitude;
    public String tipo;
/*
    @Override
    public String toString() {
        return "[" + endereco + ", " + estado + ", " + latitude + ", " + longitude + ", " + tipo + "]";
    }*/
}
