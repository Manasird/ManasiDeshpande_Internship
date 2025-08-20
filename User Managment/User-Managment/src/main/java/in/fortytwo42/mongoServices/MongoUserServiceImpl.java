package in.fortytwo42.mongoServices;

import in.fortytwo42.dtos.CreateResponse;
import in.fortytwo42.documents.Attribute;
import in.fortytwo42.documents.User;
import in.fortytwo42.repository.AttributeRepository;
import in.fortytwo42.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("mongo")
public class MongoUserServiceImpl implements MongoUserService {
    private final UserRepository userRepo;
    private final AttributeRepository attributeRepo;
    private final MongoTemplate mongoTemplate;

    public MongoUserServiceImpl(UserRepository userRepo, MongoTemplate mongoTemplate,AttributeRepository attributeRepo) {
        this.userRepo = userRepo;
        this.mongoTemplate = mongoTemplate;
        this.attributeRepo = attributeRepo;
    }

    public CreateResponse addUser(User user) throws Exception {
        // 1️⃣ Check for duplicate username
        Optional<User> existingUser = userRepo.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            return  new CreateResponse("User already present.");
        }
            // Just save user directly, attributes are embedded
            User savedUser = userRepo.save(user);

            return new CreateResponse("User created successfully", savedUser.getId());
        }



    public List<User> searchUser(List<Attribute> searchAttribute) {
        if (searchAttribute == null || searchAttribute.isEmpty()) {
            return new ArrayList<>();
        }
        List<Criteria> criteriaList = new ArrayList<>();
        for(Attribute attr : searchAttribute){
            criteriaList.add(Criteria.where("attributes").elemMatch(Criteria.where("attributeName").is(attr.getAttributeName())
                    .and("attributeValue").is(attr.getAttributeValue())));

        }
        Query query= new Query(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        return mongoTemplate.find(query,User.class);

    }

    public CreateResponse addAttribute(List<Attribute> searchAttributes, List<Attribute> newAttributes) {
        List<User> matchedUsers = searchUser(searchAttributes);

        if (matchedUsers.isEmpty()) {
            return  new CreateResponse("User not found");
        }

        for (User user : matchedUsers) {
            List<Attribute> savedAttributes = new ArrayList<>();

            for (Attribute attr : newAttributes) {
                boolean exists= false;
                for(Attribute existing : user.getAttributes()){
                    if (existing.getAttributeName().equals(attr.getAttributeName())&&
                    existing.getAttributeValue().equals(attr.getAttributeValue())){
                        exists=true;
                        break;
                    }
                }

               if(!exists) {
                   Attribute savedAttr = attributeRepo.save(attr);
                   savedAttributes.add(savedAttr);
               }
               else
                   return new CreateResponse("Duplicate attribute.");
            }

            // Attach saved attributes to user's DBRef list
            if (user.getAttributes() == null) {
                user.setAttributes(new ArrayList<>());
            }
            user.getAttributes().addAll(savedAttributes);

            // Save user with updated DBRef list
            userRepo.save(user);
        }
        return new CreateResponse("Attribute adeed successfully.");
    }

    public CreateResponse deleteAttribute(List<Attribute> searchAttribute, List<Attribute> deleteAttribute){
        List<User> matchedUser=searchUser(searchAttribute);
        if(matchedUser.isEmpty()){
            return  new CreateResponse("User not found");
        }
        boolean found=false;
        for(User user:matchedUser){
            List<Attribute> toDelete= new ArrayList<>();
            for (Attribute attr:user.getAttributes()){
                for(Attribute delAttr:deleteAttribute){
                    if (attr.getAttributeName().equals(delAttr.getAttributeName() )&&
                            attr.getAttributeValue().equals(delAttr.getAttributeValue())){
                        toDelete.add(attr);
                        found=true;
                    }
                }
            }
            user.getAttributes().removeAll(toDelete);
        }
        if (!found){
            return new CreateResponse("No matching attribute found.");
        }
        userRepo.saveAll(matchedUser);

        return new CreateResponse("Attribute deleted successfully.");
    }


    public CreateResponse updateAttribute(List<Attribute> searchAttribute, String attributeName, String attributeValue, String newAttributeValue) {
        List<User> matchedUser = searchUser(searchAttribute);
        if (matchedUser.isEmpty()) {
            return  new CreateResponse("User not found");
        }
        boolean update = false;
        for (User user : matchedUser) {
            //boolean update=false;
            for (Attribute attr : user.getAttributes()) {
                if (attr.getAttributeName().equals(attributeName) &&
                        attr.getAttributeValue().equals(attributeValue)) {
                    if (attr.getAttributeValue().equals(newAttributeValue)){
                        return new CreateResponse("New attribute value can not be same as old attribute value.");
                    }
                    attr.setAttributeValue(newAttributeValue);
                    update = true;
                }
            }
            if (!update) {
                return  new CreateResponse("Attribute not found.");
            }
            userRepo.save(user);
        }

        return new CreateResponse("Attribute updated successfully.");
    }
    public CreateResponse deleteUser(String userName) throws Exception {
        Optional<in.fortytwo42.documents.User> u = userRepo.findByUserName(userName);
        int count=0;
        if (u.isPresent()) {
            User user = new User();
            userRepo.delete(u.get());
            //return ;
        }
        else {
            return  new CreateResponse("User does not found");
        }
        // return count;
        return new CreateResponse("User deleted successfully.");
    }
}
