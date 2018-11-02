package util;

import beans.Course;
import beans.Teacher;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {

    public Course getCourse(Teacher teacher,String cookies,String verification) throws IOException {
        FormBody formBody  = new FormBody
                .Builder()
                .add("Sel_XNXQ","20180")
//                .add("Sel_JS","0001610")
                .add("Sel_JS",teacher.getNumber())
                .add("type","1")
                .add("txt_yzm",verification)
                .build();
        String url = "http://121.248.70.120/jwweb/ZNPK/TeacherKBFB_rpt.aspx";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Referer","http://121.248.70.120/jwweb/ZNPK/TeacherKBFB.aspx")
                .addHeader("Cookie",cookies)
                .build();
        Response response = client.newCall(request).execute();
        if(response.body() != null){
            Document document = Jsoup.parse(response.body().string());
            Elements elements = document.getElementById("pageRpt").select("table[width=99%]table[border=1]");
            Course course = new Course();
            course.setTeacher(teacher.getName());
            course.setTeacherNum(teacher.getNumber());
            course.setText(elements.toString());
            return course;
        }
        return null;
    }

}
