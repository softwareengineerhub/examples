/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

/**
 *
 * @author Denis
 */
public class Person implements Comparable {

    private String name;
    private int age;
  //  private 

    public Person() {
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() == Person.class) {
            Person other = (Person) obj;
            boolean c1 = other.age == age;
            boolean c2 = true;
            if (other.name != null) {
                c2 = other.name.equals(name);
            } else if (name != null) {
                c2 = name.equals(other.name);
            }
            return c1 && c2;
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return name==null ? age : age+name.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", age=" + age + '}';
    }

    @Override
    public int compareTo(Object o) {
        System.out.println("compareTo");
        Person p = (Person) o;
        return p.getAge()-age;
    }
    
    

}
