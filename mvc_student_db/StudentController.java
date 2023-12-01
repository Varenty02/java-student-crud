/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_student_db;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hieud
 */
public class StudentController {

    private ArrayList<Student> arr;
    private StudentView frm;
    private Student st;
    private Connection con;

    public StudentController() {
        String dbClass = "com.mysql.cj.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost:3306/student-management";
        try {
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl, "root", "123456");

        } catch (Exception e) {
            e.printStackTrace();
        }

        arr = new ArrayList<Student>();
        frm = new StudentView(arr);
        frm.addCreateStudentListener(new CreateStudentListener());
        frm.addDeleteStudentListener(new DeleteStudentListener());
        frm.addEditStudentListener(new EditStudentListener());
        frm.addSortGPAStudentListener(new SortStudentByGPAListener());
        frm.addSortNameStudentListener(new SortStudentByNameListener());
        frm.setVisible(true);
    }
public void updateArray() throws SQLException{
    String sql="select * from student";
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet resultset=ps.executeQuery();
    arr.clear();
    while(resultset.next()){
        int id=resultset.getInt("id");
        String name=resultset.getString("name");
        int age=resultset.getInt("age");
        String address=resultset.getString("address");
        Float gpa=resultset.getFloat("gpa");
        arr.add(new Student(id,name,age,address,gpa));
    }
}
    public void createStudent(Student st) {
        String sql = "INSERT INTO student(id, name, age,address,  gpa) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, st.getId());
            ps.setString(2, st.getName());
            ps.setInt(3, st.getAge());
            ps.setString(4, st.getAddress());
            ps.setFloat(5, st.getGpa());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class CreateStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                st = frm.getStudent();
                createStudent(st);
                updateArray();
                frm.updateTable();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteStudent(Student st) {
        String sql="delete from student where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, st.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class DeleteStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println(arr);
                st = frm.getStudentEdit();
                deleteStudent(st);
                updateArray();
                frm.updateTable();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void editStudent(Student st) {
        String sql="update student set name=?,age=?,address=?,gpa=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, st.getName());
            ps.setInt(2, st.getAge());
            ps.setString(3, st.getAddress());
            ps.setFloat(4, st.getGpa());
            ps.setInt(5, st.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class EditStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println(arr);
                st = frm.getStudentEdit();
                editStudent(st);
                updateArray();
                frm.updateTable();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sortStudentByName() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class SortStudentByNameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                frm.updateTable();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sortStudentByGPA() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class SortStudentByGPAListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                sortStudentByGPA();
                frm.updateTable();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
