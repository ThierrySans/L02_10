 CREATE OR REPLACE FUNCTION Add_Answer(Users varchar(100), assignmentID int , questionId int, answer varchar(255))
 	RETURNS varchar AS $$
 	BEGIN
	 	IF EXISTS (SELECT 1 FROM UserAssignQuesAnsMap WHERE UTOR_Id=Users AND Assignment_Id=assignmentID AND Question_Id=questionId) THEN
 			UPDATE UserAssignQuesAnsMap SET Answer = answer WHERE UTOR_Id=Users AND Assignment_Id=assignmentID AND Question_Id=questionId;
 			RETURN 'Answer Added Modified';
 		ELSE
		  	INSERT INTO UserAssignQuesAnsMap (UTOR_Id, Assignment_Id,Question_Id,Answer) VALUES (Users, assignmentID,questionId,answer);
 			RETURN 'Answer Added';
 		END IF;


 	END;
 	$$ LANGUAGE plpgsql;
