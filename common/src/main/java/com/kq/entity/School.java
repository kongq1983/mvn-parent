package com.kq.entity;

/**
 * School
 *
 * @author kq
 * @date 2019-11-27
 */
public class School {

    private Long id;
    private Teacher teacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("School{");
        sb.append("id=").append(id);
        sb.append(", teacher=").append(teacher);
        sb.append('}');
        return sb.toString();
    }
}
