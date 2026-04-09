import React, { useEffect, useState } from "react";
import axios from "axios";
import AddStudent from "./AddStudent";

function StudentList() {

  const [students, setStudents] = useState([]);
  const [selectedStudent, setSelectedStudent] = useState(null);

  const API = "http://localhost:8081/students"; // ⚠️ match your backend port

  // GET (FETCH ALL)
  const fetchStudents = () => {
    axios.get(API)
      .then(res => setStudents(res.data))
      .catch(err => console.log(err));
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  // ADD (POST)
  const addStudent = (student) => {
    axios.post(API, student)
      .then(() => fetchStudents());
  };

  // DELETE
  const deleteStudent = (id) => {
    axios.delete(`${API}/${id}`)
      .then(() => fetchStudents());
  };

  // SELECT FOR UPDATE
  const editStudent = (student) => {
    setSelectedStudent(student);
  };

  // UPDATE (PUT)
  const updateStudent = (student) => {
    axios.put(`${API}/${student.id}`, student)
      .then(() => {
        fetchStudents();
        setSelectedStudent(null);
      });
  };

  return (
    <div style={styles.container}>

      <h1 style={styles.heading}>🎓 Student Management System</h1>

      <AddStudent
        addStudent={addStudent}
        selectedStudent={selectedStudent}
        updateStudent={updateStudent}
      />

      <table style={styles.table}>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {students.map((s) => (
            <tr key={s.id}>
              <td>{s.name}</td>
              <td>{s.email}</td>
              <td>{s.course}</td>
              <td>
                <button onClick={() => editStudent(s)}>Edit</button>
                <button onClick={() => deleteStudent(s.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>

      </table>

    </div>
  );
}

const styles = {
  container: {
    textAlign: "center",
    background: "linear-gradient(to right, #74ebd5, #ACB6E5)",
    minHeight: "100vh",
    padding: "20px"
  },
  heading: {
    color: "#333"
  },
  table: {
    margin: "auto",
    borderCollapse: "collapse",
    width: "70%",
    background: "white"
  }
};

export default StudentList;