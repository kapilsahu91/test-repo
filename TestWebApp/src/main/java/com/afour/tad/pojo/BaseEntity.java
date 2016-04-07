package com.afour.tad.pojo;
import org.bson.types.ObjectId;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Version;
 
public abstract class BaseEntity {
 
    @Id
    @Property("id")
    protected ObjectId _id;
 
    @Version
    @Property("version")
    private Long version;
 
    public BaseEntity() {
        super();
    }
    public ObjectId get_id() {
		return _id;
	}
    public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
 
}