package com.infosys.springbootdatamongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "employee")
public class Employee {
 
	@Id
    private String id;
    private String name;
    private String designation;
 
    public Employee() { }    
    
    


	public Employee(String name, String designation) {
		super();
		this.name = name;
		this.designation = designation;
	}




	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}





	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
 
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + "]";
    }
}