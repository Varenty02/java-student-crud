/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloworld;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hieud
 */
public class StudentForm {
     private static void addComponentToLayout(JPanel panel, GridBagConstraints constraints, JComponent component,
            int gridx, int gridy, int ipadx, int ipady, Insets insets) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.ipadx = ipadx;
        constraints.ipady = ipady;
        constraints.insets = insets;
        panel.add(component, constraints);
    }

    public static void main(String[] args) {
        ArrayList<Student> arr = new ArrayList<Student>();
        DefaultTableModel model = new DefaultTableModel();
        //create button
        JButton b_add = new JButton("ADD");
        JButton b_edit = new JButton("EDIT");
        JButton b_delete = new JButton("DELETE");
        JButton b_clear = new JButton("CLEAR");
        JButton b_gpa_sort = new JButton("SORT BY GPA");
        JButton b_name_sort = new JButton("SORT BY NAME");
        JPanel panel_btn_bot=new JPanel();
        panel_btn_bot.setLayout(new GridLayout(0,2));
        panel_btn_bot.add(b_gpa_sort );
         panel_btn_bot.add(b_name_sort );
        //create field input
        JLabel lb_id = new JLabel("Id");
        JLabel lb_name = new JLabel("Name");
        JLabel lb_age = new JLabel("Age");
        JLabel lb_address = new JLabel("Address");
        JLabel lb_gpa = new JLabel("GPA");
        JTextField text_id = new JTextField(20);
        text_id.setEnabled(false);
        JTextField text_name = new JTextField(20);
        JTextField text_age = new JTextField(20);
        JTextArea text_address = new JTextArea(2,20);
        JTextField text_gpa = new JTextField(20);
        //jpanel for student field
        JPanel jp_student = new JPanel();
        GridBagLayout layout_jp_student = new GridBagLayout();
        GridBagConstraints constraint_layout_student = new GridBagConstraints();
        jp_student.setLayout(layout_jp_student);
        addComponentToLayout(jp_student, constraint_layout_student, lb_id, 0, 0, 0, 0, new Insets(20, 20, 40, 40));
        addComponentToLayout(jp_student, constraint_layout_student, lb_name, 0, 1, 0, 0, new Insets(20, 20, 40, 40));
        addComponentToLayout(jp_student, constraint_layout_student, lb_age, 0, 2, 0, 0, new Insets(20, 20, 40, 40));
        addComponentToLayout(jp_student, constraint_layout_student, lb_address, 0, 3, 0, 0, new Insets(20, 20, 40, 40));
        addComponentToLayout(jp_student, constraint_layout_student, lb_gpa, 0, 4, 0, 0, new Insets(20, 20, 40, 40));

        addComponentToLayout(jp_student, constraint_layout_student, text_id, 1, 0, 0, 0, new Insets(20, 20, 40, 40));
        addComponentToLayout(jp_student, constraint_layout_student, text_name, 1, 1, 0, 0, new Insets(20, 20, 40, 40));
        addComponentToLayout(jp_student, constraint_layout_student, text_age, 1, 2, 0, 0, new Insets(20, 20, 40, 40));
        addComponentToLayout(jp_student, constraint_layout_student, text_address, 1, 3, 0, 0, new Insets(20, 20, 40, 40));
        addComponentToLayout(jp_student, constraint_layout_student, text_gpa, 1, 4, 0, 0, new Insets(20, 20, 40, 40));
        //bot left layout panel
        JPanel panel_button = new JPanel();
        panel_button.setLayout(new GridLayout(0, 4));
        panel_button.add(b_add);
        panel_button.add(b_edit);
        panel_button.add(b_delete);
        panel_button.add(b_clear);
        //left layout panel
        JPanel panel_left = new JPanel();
        panel_left.setLayout(new BorderLayout());
        panel_left.add(panel_button, BorderLayout.SOUTH);
        panel_left.add(jp_student, BorderLayout.NORTH);

        //create table
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Address");
        model.addColumn("Age");
        model.addColumn("GPA");
        for (Student st : arr) {
            model.addRow(new Object[]{st.id, st.name, st.age, st.address, st.gpa});
        }

        //action listener
        b_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s = new Student( text_name.getText(), Integer.parseInt(text_age.getText()), text_address.getText(), Float.parseFloat(text_gpa.getText()));
                arr.add(s);
                model.insertRow(model.getRowCount(), new Object[]{s.id, s.name, s.age, s.address, s.gpa});
                model.fireTableDataChanged();
            }
        });
        b_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text_id.setText("");
                text_name.setText("");
                text_age.setText("");
                text_address.setText("");
                text_gpa.setText("");
            }
        });
        b_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    System.out.println("true");
                    for (Student st : arr) {
                        System.out.println(st.getId()+"="+text_id.getText());
                        if (st.getId()==Integer.parseInt(text_id.getText())) {
                            System.out.println(st.getId());
                            st.setId(Integer.parseInt(text_id.getText()));
                            st.setName(text_name.getText());
                            st.setAge(Integer.parseInt(text_age.getText()));
                            st.setAddress(text_address.getText());
                            st.setGpa(Float.parseFloat(text_gpa.getText()));
                            model.setRowCount(0); // Xóa tất cả các hàng trong tableModel
                            for (Student student : arr) {
                                model.addRow(new Object[]{student.getId(), student.getName(),student.getAge(),student.getAddress(),student.getAge()});
                            }
                        }
                    }
                }
            }
        });
        b_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    System.out.println("true");
                    for (Student st : arr) {
                        System.out.println(st.getId()+"="+text_id.getText());
                        if (st.getId()==Integer.parseInt(text_id.getText())) {
                            arr.remove(st);
                            model.setRowCount(0); // Xóa tất cả các hàng trong tableModel
                            for (Student student : arr) {
                                model.addRow(new Object[]{student.getId(), student.getName(),student.getAge(),student.getAddress(),student.getAge()});
                            }

                        }
                    }

                }
            }
        });
        b_gpa_sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(arr);              
                model.setRowCount(0); // Xóa tất cả các hàng trong tableModel
                            for (Student student : arr) {
                                model.addRow(new Object[]{student.getId(), student.getName(),student.getAge(),student.getAddress(),student.getGpa()});
                            }
            }
        });
        b_name_sort.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                arr.sort(new StudentComparator());
                model.setRowCount(0); // Xóa tất cả các hàng trong tableModel
                            for (Student student : arr) {
                                model.addRow(new Object[]{student.getId(), student.getName(),student.getAge(),student.getAddress(),student.getGpa()});
                            }
            }
        });
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Sự kiện được gọi khi người dùng chọn hoặc hủy chọn một dòng
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Integer id = (Integer) table.getValueAt(selectedRow, 0);
                    String name = (String) table.getValueAt(selectedRow, 1);
                    Integer age = (Integer) table.getValueAt(selectedRow, 2);
                    String address = (String) table.getValueAt(selectedRow, 3);
                    Float gpa = (Float) table.getValueAt(selectedRow, 4);
                    System.out.println("click on");
                    text_id.setText(id+"");
                    text_name.setText(name);
                    text_age.setText(age+"");
                    text_address.setText(address);
                    text_gpa.setText(gpa+"");
                }
            }
        });
        //layout setting for frame
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.setSize(300, 300);
        f.add(panel_left, BorderLayout.WEST);
        f.add(new JScrollPane(table), BorderLayout.EAST);
        f.add(panel_btn_bot, BorderLayout.SOUTH);
        f.setSize(1000, 500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
