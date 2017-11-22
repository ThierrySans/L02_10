DROP TABLE IF EXISTS Assignments;

Create Table Assignments(
	Id	SERIAL PRIMARY KEY,
	Course_Code varchar(100) NOT NULL REFERENCES Classes(Course_Code),
	Assignment_Name varchar(255) NOT NULL,
	Visibility boolean NOT NULL DEFAULT True,
	Max_Attempt int,
	Open_Time timestamp,
	Close_Time timestamp,
	Assignment_Type varchar(100) NOT NULL DEFAULT 'Multiple Choice',
	Automark boolean NOT NULL DEFAULT TRUE
)