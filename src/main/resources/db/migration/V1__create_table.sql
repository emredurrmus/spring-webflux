CREATE TABLE course
(
    course_id uuid NOT NULL,
    name varchar(50) NOT NULL,
    description varchar(200) NOT NULL,
    duration int4 NOT NULL,
    teacher varchar(50) NOT NULL,
    course_metadata jsonb NOT NULL,
    PRIMARY KEY (course_id)
);

CREATE TABLE student
(
    id UUID NOT NULL,
    name varchar(50) NOT NULL,
    date_of_birth date NOT NULL,
    email varchar(50) NOT NULL,
    course_id VARCHAR[] NOT NULL,
    PRIMARY KEY (id)
);