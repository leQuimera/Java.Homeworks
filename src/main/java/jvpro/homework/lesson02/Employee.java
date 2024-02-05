package jvpro.homework.lesson02;

public class Employee {

    enum Job {
        ENGINEER, MANAGER, ACCOUNTANT
    }


    private String name;
    private int age;
    private Job job;

    public Employee(String name, int age, Job job) {
        this.name = name;
        this.age = age;
        this.job = job;
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return String.format("{name: %s, age: %s, job: %s}", name, age, job);
    }
}
