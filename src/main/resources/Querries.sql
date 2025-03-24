--drop all tables
DROP TABLE student_teacher_link;
DROP TABLE student;
DROP TABLE teacher;


--create tables
CREATE TABLE student(
	student_id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL
);

CREATE TABLE teacher(
	teacher_id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL
);

CREATE TABLE student_teacher_link(
	student_teacher_link_id BIGSERIAL PRIMARY KEY,
	student_id BIGSERIAL NOT NULL,
	teacher_id BIGSERIAL NOT NULL,
	FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
	FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id) ON DELETE CASCADE
);



--create index
--foreign key index
CREATE INDEX student_teacher_link_student_id
ON student_teacher_link(student_id);

CREATE INDEX student_teacher_link_teacher_id
ON student_teacher_link(teacher_id);

--query driven index
CREATE INDEX student_last_name
ON student(last_name);

CREATE INDEX teacher_last_name
ON teacher(last_name);


--insert data
INSERT INTO student(first_name, last_name)
VALUES ('Jack', 'Adams');

INSERT INTO student(first_name, last_name)
VALUES ('Roger', 'Allen');

INSERT INTO student(first_name, last_name)
VALUES ('Jess', 'Barton');

INSERT INTO student(first_name, last_name)
VALUES ('Dan', 'Bell');

INSERT INTO student(first_name, last_name)
VALUES ('Jane', 'Clark');


INSERT INTO teacher(first_name, last_name)
VALUES ('Jessica', 'Curry');

INSERT INTO teacher(first_name, last_name)
VALUES ('Taylor', 'Davis');


INSERT INTO student_teacher_link(student_id, teacher_id)
VALUES(1, 1);

INSERT INTO student_teacher_link(student_id, teacher_id)
VALUES(1, 2);

INSERT INTO student_teacher_link(student_id, teacher_id)
VALUES(2, 1);

INSERT INTO student_teacher_link(student_id, teacher_id)
VALUES(3, 1);

INSERT INTO student_teacher_link(student_id, teacher_id)
VALUES(3, 2);

INSERT INTO student_teacher_link(student_id, teacher_id)
VALUES(4, 2);

INSERT INTO student_teacher_link(student_id, teacher_id)
VALUES(5, 1);

INSERT INTO student_teacher_link(student_id, teacher_id)
VALUES(5, 2);