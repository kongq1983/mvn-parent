package com.kq.entity;

/**
 * Teacher
 *
 * @author kq
 * @date 2019-11-27
 */
public class Teacher {
    private Long id;
    private String username;
    private School school;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Teacher{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", school=").append(school);
        sb.append('}');
        return sb.toString();
    }
}
