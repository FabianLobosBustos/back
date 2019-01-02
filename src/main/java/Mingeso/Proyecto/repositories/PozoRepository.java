package Mingeso.Proyecto.repositories;

import Mingeso.Proyecto.model.Pozo;
import org.springframework.data.repository.CrudRepository;


public interface PozoRepository extends CrudRepository<Pozo, Long> {

    Pozo findPozoById(int id);


}
