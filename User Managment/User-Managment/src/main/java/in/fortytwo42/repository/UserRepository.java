package in.fortytwo42.repository;

import in.fortytwo42.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<in.fortytwo42.documents.User> findByUserName(String userName);

    //void delete(in.fortytwo42.entities.User user);

    in.fortytwo42.documents.User save(in.fortytwo42.documents.User user);

   // void saveAll(List<in.fortytwo42.entities.User> matchedUser);

    void delete(in.fortytwo42.documents.User user);
}
