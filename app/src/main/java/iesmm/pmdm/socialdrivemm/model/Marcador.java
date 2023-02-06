package iesmm.pmdm.socialdrivemm.model;

public class Marcador {

    private String id, ubi, descripcion, icono;

    public Marcador(String id, String ubi, String descripcion, String icono) {
        this.id = id;
        this.ubi = ubi;
        this.descripcion = descripcion;
        this.icono = icono;
    }

    public String getUbi() {
        return ubi;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIcono() {
        return icono;
    }

    public String getId() {
        return id;
    }
}
