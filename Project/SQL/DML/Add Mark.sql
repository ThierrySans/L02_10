 CREATE OR REPLACE FUNCTION Add_Mark(Users varchar(100), assignmentID int , mark int)
 	RETURNS varchar AS $$
 	BEGIN
 		INSERT INTO UserAssignMarkMap (UTOR_Id, Assignment_Id,Mark) VALUES (Users, assignmentID,mark);
 		RETURN 'Answer Added';
 	END;
 	$$ LANGUAGE plpgsql;
