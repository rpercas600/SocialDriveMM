package iesmm.pmdm.socialdrivemm.dao;


import java.util.List;

import iesmm.pmdm.socialdrivemm.model.Marcador;

public interface DAOMarcador {


    List<Marcador> listarMacadores();

    boolean insert(Marcador marcador);

    boolean update(Marcador marcador);

    boolean delete(String idMarc);



}
