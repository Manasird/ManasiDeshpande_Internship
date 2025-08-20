package in.fortytwo42.daos;

import in.fortytwo42.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeDao extends JpaRepository<Attribute,Integer> {
}
