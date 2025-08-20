package in.fortytwo42.services;

import in.fortytwo42.daos.AttributeDao;
import in.fortytwo42.daos.UserDao;
import in.fortytwo42.dtos.CreateResponse;
import in.fortytwo42.entities.Attribute;
import in.fortytwo42.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private  UserDao userDao;
    private AttributeDao attributeDao;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserDao userDao, AttributeDao attributeDao){
        this.userDao = userDao;
        this.attributeDao=attributeDao;
    }
    @Override
    public CreateResponse addUser(User user) throws Exception {
     System.out.println(user.getUserName());
        Optional<User> u= userDao.findByUserName(user.getUserName());
        if(u.isPresent()){
            return  new CreateResponse("User already present.");
        }
       // User user = new User();
        //user.setUserName(userName);


        //User user = new User();
        if(user.getAttributes()!= null) {
            List<Attribute> newAttributes =new ArrayList<>();
            for (Attribute a : user.getAttributes()) {
                var ua = new Attribute();
                ua.setUser(user);
                ua.setAttributeName(a.getAttributeName());
                ua.setAttributeValue(a.getAttributeValue());
                newAttributes .add(ua);
            }
            user.setAttributes(newAttributes);
        }
        User savedUser= userDao.save(user);
        return  new CreateResponse("User created successfully", savedUser.getUserId());
    }

public List<User> searchUser(List<Attribute> searchAttribute) {
    if (searchAttribute == null || searchAttribute.isEmpty()) {
        return new ArrayList<>();
    }


    StringBuilder jpql = new StringBuilder("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.attributes WHERE ");

    for (int i = 0; i < searchAttribute.size(); i++) {
        if (i > 0) jpql.append(" AND ");
        jpql.append("EXISTS (")
                .append("SELECT a FROM Attribute a ")
                .append("WHERE a.user = u ")
                .append("AND a.attributeName = :name").append(i)
                .append(" AND a.attributeValue = :value").append(i)
                .append(")");
    }


    TypedQuery<User> query = entityManager.createQuery(jpql.toString(), User.class);


    for (int i = 0; i < searchAttribute.size(); i++) {
        query.setParameter("name" + i, searchAttribute.get(i).getAttributeName());
        query.setParameter("value" + i, searchAttribute.get(i).getAttributeValue());
    }

    return query.getResultList();
}

    public CreateResponse addAttribute(List<Attribute> searchAttribute, List<Attribute> newAttribute){
        List<User> matchedUser= searchUser(searchAttribute);
        if(matchedUser.isEmpty()){
            return new CreateResponse("User does not found.");
        }
        for(User user : matchedUser){
            for(Attribute newAttr:newAttribute){
               for (Attribute existingAttr:user.getAttributes()){
                   if (existingAttr.getAttributeName().equalsIgnoreCase(newAttr.getAttributeName())&&
                   existingAttr.getAttributeValue().equalsIgnoreCase(newAttr.getAttributeValue())){
                       return new CreateResponse("Duplicate attribute found.");
                   }
               }
            }
        }
       for(User user: matchedUser) {
           for (Attribute attr : newAttribute) {
               attr.setUser(user);
               user.getAttributes().add(attr);
           }
       }
     userDao.saveAll(matchedUser);

        return new CreateResponse("Attribute adeed successfully.");
    }
    public CreateResponse deleteAttribute(List<Attribute> searchAttribute, List<Attribute> deleteAttribute){
       List<User> matchedUser=searchUser(searchAttribute);
       if(matchedUser.isEmpty()){
           return new CreateResponse("User does not found.");
       }
       boolean found =false;
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
           if (!toDelete.isEmpty()) {
               user.getAttributes().removeAll(toDelete);

           }
       }
       if (!found){
           return new CreateResponse("No matching attribute found.");
       }
       userDao.saveAll(matchedUser);

        return new CreateResponse("Attribute deleted successfully.");
    }

//    @Override
//    public Query createQuery(String string, Class<User> userClass) {
//        return null;
//    }

    public CreateResponse updateAttribute(List<Attribute> searchAttribute, String attributeName, String attributeValue, String newAttributeValue) {
        List<User> matchedUser = searchUser(searchAttribute);
        if (matchedUser.isEmpty()) {
            return new CreateResponse("User does not found.");
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
                return new CreateResponse("Attribute does not found.");
            }
            userDao.save(user);
        }

        return new CreateResponse("Attribute updated successfully.");
    }
    public CreateResponse deleteUser(String userName) throws Exception {
        Optional<in.fortytwo42.entities.User> u = userDao.findByUserName(userName);
        int count=0;
        if (u.isPresent()) {
            in.fortytwo42.entities.User user = new in.fortytwo42.entities.User();
            userDao.delete(u.get());
            //return ;
        }
        else {
            return  new CreateResponse("User does not found");
        }
        // return count;
        return new CreateResponse("User deleted successfully.");
    }



}

