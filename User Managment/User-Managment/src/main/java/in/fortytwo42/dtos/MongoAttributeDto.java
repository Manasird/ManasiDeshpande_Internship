package in.fortytwo42.dtos;

import in.fortytwo42.documents.Attribute;

import java.util.List;

public class MongoAttributeDto {
    List<in.fortytwo42.documents.Attribute> searchAttribute;
    List<in.fortytwo42.documents.Attribute> newAttribute;
    List<Attribute> deleteAttribute;
    String attributeName;
    String oldAttributeValue;
    String newAttributeValue;
    public MongoAttributeDto(){
    }

    public List<Attribute> getSearchAttribute() {
        return searchAttribute;
    }

    public void setSearchAttribute(List<Attribute> searchAttribute) {
        this.searchAttribute = searchAttribute;
    }

    public List<Attribute> getNewAttribute() {
        return newAttribute;
    }

    public void setNewAttribute(List<Attribute> newAttribute) {
        this.newAttribute = newAttribute;
    }

    public List<Attribute> getDeleteAttribute() {
        return deleteAttribute;
    }

    public void setDeleteAttribute(List<Attribute> deleteAttribute) {
        this.deleteAttribute = deleteAttribute;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getOldAttributeValue() {
        return oldAttributeValue;
    }

    public void setOldAttributeValue(String oldAttributeValue) {
        this.oldAttributeValue = oldAttributeValue;
    }

    public String getNewAttributeValue() {
        return newAttributeValue;
    }

    public void setNewAttributeValue(String newAttributeValue) {
        this.newAttributeValue = newAttributeValue;
    }
}
