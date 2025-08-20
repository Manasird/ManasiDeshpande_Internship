package in.fortytwo42.repository;

import in.fortytwo42.documents.Attribute;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttributeRepository extends MongoRepository<Attribute,Integer> {
}
