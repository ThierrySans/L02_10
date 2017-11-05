ALTER TABLE Assignments ADD Visibility varchar(10);
UPDATE Assignments SET Visibility = 'on';