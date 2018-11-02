package dao;

import beans.Course;

import java.util.ArrayList;

public interface courseDAO {
    /**
     * 更新数据库
     * @param course
     * @return 返回结果 成功或者失败
     */
    public Boolean updataCourse(Course course);

    /**
     * 插入新数据
     * @param course
     * @return 返回结果 成功或者失败
     */
    public Boolean insertCourse(Course course);

    /**
     * 根据teacherNum 查找课程，teacherNum可能不完整
     * @param teacherNum
     * @return 课程列表
     */
    public ArrayList<Course> selectByTeacherName(String teacherNum);

}
