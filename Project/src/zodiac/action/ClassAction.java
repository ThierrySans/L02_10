package zodiac.action;

import zodiac.dao.ClassDao;

public class ClassAction {

    private  ClassDao  classDao;

    /**
     * Add API to create a Class object
     * @param courseCode
     * @param className
     * @return
     */
    public  String addClass(String courseCode,String className){
        return classDao.addEditClass(courseCode, className);
    }


}
