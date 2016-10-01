package be.ghostwritertje.dao.repository;

import be.ghostwritertje.domain.Car;
import be.ghostwritertje.domain.Refueling;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jorandeboever
 * Date: 01-Oct-16.
 */
@Repository
public interface RefuelingDao extends CrudRepository<Refueling, Integer> {

    List<Refueling> findByCar(Car car);
}