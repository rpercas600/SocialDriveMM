package iesmm.pmdm.socialdrivemm.model;

public class
Marcador {

    private int id;
    private String hora, ubi, descripcion, via, user;


    public Marcador(int id, String hora, String ubi, String descripcion, String via, String user) {
        this.id = id;
        this.hora = hora;

        this.ubi = ubi;
        this.descripcion = descripcion;
        this.via = via;
        this.user = user;
    }

    public Marcador(String hora, String ubi, String descripcion, String via, String user) {
        this.hora = hora;
        this.ubi = ubi;
        this.descripcion = descripcion;
        this.via = via;
        this.user = user;
    }

    public int getId() { return id; }

    public String getHora() { return hora; }

    public String getUbi() {
        return ubi;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getVia() {
        return via;
    }

    public String getUser() { return user; }

    //hace falta el id en el objeto?
    /*
    public String getId() {
        return id;
    } */
}
