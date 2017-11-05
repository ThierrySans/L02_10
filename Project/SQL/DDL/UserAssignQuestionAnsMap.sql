DROP TABLE IF EXISTS UserAssignQuesAnsMap;

Create Table UserAssignQuesAnsMap(
    UTOR_Id varchar(100) NOT NULL REFERENCES Users(UTOR_Id),
	Assignment_Id int NOT NULL REFERENCES Assignments(Id),
	Question_Id int NOT NULL REFERENCES Questions(Id),
    Answer varchar(255)
)