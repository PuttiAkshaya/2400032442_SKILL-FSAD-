import React, { useState, useEffect } from "react";

function AddStudent({ addStudent, selectedStudent, updateStudent }) {

  const [student, setStudent] = useState({
    name: "",
    email: "",
    course: ""
  });

  useEffect(() => {
    if (selectedStudent) {
      setStudent(selectedStudent);
    }
  }, [selectedStudent]);

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (student.id) {
      updateStudent(student);
    } else {
      addStudent(student);
    }

    setStudent({ name: "", email: "", course: "" });
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="name" value={student.name} onChange={handleChange} placeholder="Name" />
      <input name="email" value={student.email} onChange={handleChange} placeholder="Email" />
      <input name="course" value={student.course} onChange={handleChange} placeholder="Course" />
      <button type="submit">
        {student.id ? "Update" : "Add"}
      </button>
    </form>
  );
}

export default AddStudent;