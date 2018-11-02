package beans;

public class Course {
    private String text;//课表
    private String teacherNum;//老师编号
    private String teacher;//老师名

    public Course() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherName) {
        this.teacherNum = teacherName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "text='" + text + '\'' +
                ", teacherNum='" + teacherNum + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
