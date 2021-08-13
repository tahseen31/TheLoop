package edu.neu.madcourse.theloop;

public class User {
    public String fullName, age, email, id;
    public User(){

    }
    public User(String fullName,String age, String email, String id){
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.id = id;

    }

    public String getFullName() {
        return fullName;
    }

    public String getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
