import beans.Course;
import beans.Teacher;
import dao.CourseDaoImp;
import util.Spider;

import java.io.IOException;
import java.util.ArrayList;

public class MainServer {
    private CourseDaoImp courseDaoImp = new CourseDaoImp();
    public Course getCourse(Teacher teacher,String cookies,String verification){
        ArrayList<Course> courses = courseDaoImp.selectByTeacherName(teacher.getNumber());
        if(courses!=null) return courses.get(0);
        else {
            System.out.println("开始从网络获取数据");
            Course course = null;
            try {
                course = new Spider().getCourse(teacher,cookies,verification);
                if(course!=null&&!"".equals(course.getText())){
                    courseDaoImp.insertCourse(course);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return course;
        }
    }
}
