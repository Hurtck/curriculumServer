package dao;

import beans.Course;
import util.CreateDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDaoImp implements courseDAO {
    private  static CreateDB db1=null;

    public Boolean updataCourse(Course course) {
        String sql2 = "update Course set teacher=?,teacherNum=?,text=?";
        db1=new CreateDB();
        PreparedStatement ps1;
        try {
            ps1 = db1.con.prepareStatement(sql2);
            ps1.setString(1, course.getTeacher());
            ps1.setString(2,course.getTeacherNum());
            String text = course.getText();
            String arg1 = Character.toString('\"');
            String arg2 = "\\\\"+'"';
            String ret = text.replaceAll(arg1,arg2);
            String arg3 = Character.toString('\'');
            String arg4 = "\\\\'";
            text = ret.replaceAll(arg3,arg4);
            ps1.setString(3,text);
            ps1.executeUpdate();
            ps1.close();
            db1.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean insertCourse(Course course) {
        String sql1 = "insert into Course(teacher,teacherNum,content)values(?,?,?)";
        db1=new CreateDB();
        try {
            PreparedStatement ps1=db1.con.prepareStatement(sql1);
            ps1.setString(1, course.getTeacher());
            ps1.setString(2,course.getTeacherNum());
            String text = course.getText();
            String arg1 = Character.toString('\"');
            String arg2 = "\\\\"+'"';
            String ret = text.replaceAll(arg1,arg2);
            String arg3 = Character.toString('\'');
            String arg4 = "\\\\'";
            text = ret.replaceAll(arg3,arg4);
            ps1.setString(3,text);
            ps1.executeUpdate();
            ps1.close();
            db1.con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        // TODO Auto-generated method stub
        return true;
    }

    public ArrayList<Course> selectByTeacherName(String teacherNum) {
        String sql3 = "select * from Course where teacherNum='" + teacherNum+"'";
        db1=new CreateDB();
        Course cs=new Course();
        ArrayList<Course> ls=new ArrayList<Course>();
        try {
            PreparedStatement ps=db1.con.prepareStatement(sql3);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                cs.setTeacher(rs.getString(2));
                cs.setTeacherNum(rs.getString(3));
                cs.setText(rs.getString(4));
                ls.add(cs);
            }
            rs.close();
            db1.con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        if(ls.size()==0) return null;
        return ls;
    }
}
