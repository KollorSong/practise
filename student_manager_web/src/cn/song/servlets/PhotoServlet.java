package cn.song.servlets;

import cn.song.dao.StudentDao;
import cn.song.dao.TeacherDao;
import cn.song.model.Student;
import cn.song.model.Teacher;
import com.lizhou.exception.FileFormatException;
import com.lizhou.exception.NullFileException;
import com.lizhou.exception.ProtocolException;
import com.lizhou.exception.SizeException;
import com.lizhou.fileload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 *图片处理类servlet
 */
public class PhotoServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 3274927179113071465L;
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String method = request.getParameter("method");
        if("getPhoto".equals(method)){
            getPhoto(request,response);
        }else if("SetPhoto".equals(method)){
            uploadPhoto(request,response);
        }

    }
    private void uploadPhoto(HttpServletRequest request,
                             HttpServletResponse response) {
        int sid = request.getParameter("sid") == null ? 0 : Integer.parseInt(request.getParameter("sid"));
        int tid = request.getParameter("tid") == null ? 0 : Integer.parseInt(request.getParameter("tid"));
        FileUpload fileUpload = new FileUpload(request);
        fileUpload.setFileFormat("jpg");
        fileUpload.setFileFormat("png");
        fileUpload.setFileFormat("jpeg");
        fileUpload.setFileFormat("gif");
        fileUpload.setFileSize(2048);
        response.setCharacterEncoding("UTF-8");
        try {
            InputStream uploadInputStream = fileUpload.getUploadInputStream();
            File outFile = new File("D:\\picture\\123.png");
            OutputStream target = new FileOutputStream(outFile);
            byte[] buffer = new byte[2048];
            int len;

            while ((len = uploadInputStream.read(buffer)) > 0) {
                target.write(buffer, 0, len);
            }
            if(sid != 0){
                Student student = new Student();
                student.setId(sid);
                student.setPhoto(uploadInputStream);
                StudentDao studentDao = new StudentDao();
                if(studentDao.setStudentPhoto(student)){
                    response.getWriter().write("<div id='message'>上传成功！</div>");
                }else{
                    response.getWriter().write("<div id='message'>上传失败！</div>");
                }
            }
            if(tid != 0){
                Teacher teacher = new Teacher();
                teacher.setId(tid);
                teacher.setPhoto(uploadInputStream);
                TeacherDao teacherDao = new TeacherDao();
                if(teacherDao.setTeacherPhoto(teacher)){
                    response.getWriter().write("<div id='message'>上传成功！</div>");
                }else{
                    response.getWriter().write("<div id='message'>上传失败！</div>");
                }
            }
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            try {
                response.getWriter().write("<div id='message'>上传协议错误！</div>");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }catch (NullFileException e1) {
            try {
                response.getWriter().write("<div id='message'>上传的文件为空!</div>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            e1.printStackTrace();
        }
        catch (SizeException e2) {
            try {
                response.getWriter().write("<div id='message'>上传文件大小不能超过"+fileUpload.getFileSize()+"！</div>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            e2.printStackTrace();
        }
        catch (IOException e3) {
            try {
                response.getWriter().write("<div id='message'>读取文件出错！</div>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            e3.printStackTrace();
        }
        catch (FileFormatException e4) {
            // TODO: handle exception
            try {
                response.getWriter().write("<div id='message'>上传文件格式不正确，请上传 "+fileUpload.getFileFormat()+" 格式的文件！</div>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            e4.printStackTrace();
        }
        catch (FileUploadException e5) {
            // TODO: handle exception
            try {
                response.getWriter().write("<div id='message'>上传文件失败！</div>");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            e5.printStackTrace();
        }
    }
    private void getPhoto(HttpServletRequest request,
                          HttpServletResponse response) {
        // TODO Auto-generated method stub
        //File file = new File();
        int sid = request.getParameter("sid") == null ? 0 : Integer.parseInt(request.getParameter("sid"));
        int tid = request.getParameter("tid") == null ? 0 : Integer.parseInt(request.getParameter("tid"));
        if(sid != 0){
            //学生
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.getStudent(sid);
            studentDao.release();
            if(student != null){
                InputStream photo = student.getPhoto();
                if(photo != null){
                    try {
                        byte[] b = new byte[photo.available()];
                        photo.read(b);
                        response.getOutputStream().write(b,0,b.length);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
        if(tid != 0){
            //教师
            TeacherDao teacherDao = new TeacherDao();
            Teacher teacher = teacherDao.getTeacher(tid);
            teacherDao.release();
            if(teacher != null){
                InputStream photo = teacher.getPhoto();
                if(photo != null){
                    try {
                        byte[] b = new byte[photo.available()];
                        photo.read(b);
                        response.getOutputStream().write(b,0,b.length);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
        String path = request.getSession().getServletContext().getRealPath("");
        File file = new File(path+"\\file\\logo.jpg");
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            response.getOutputStream().write(b,0,b.length);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

