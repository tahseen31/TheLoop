package edu.neu.madcourse.theloop;

public class User {
    public String fullName, email, id, phone, gender, height, age,weight;
    public int stepGoal;
    public User(){

    }
    public User(String fullName, String email, String id,String phone,String gender, String height,String age,int stepGoal, String weight){
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.id = id;
        this.phone=phone;
        this.gender=gender;
        this.height=height;
        this.stepGoal=stepGoal;
        this.weight=weight;


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getStepGoal() {
        return stepGoal;
    }

    public void setStepGoal(int stepGoal) {
        this.stepGoal = stepGoal;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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
