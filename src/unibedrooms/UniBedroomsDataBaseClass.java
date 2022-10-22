package unibedrooms;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import exceptions.UserAlreadyExistsException;


/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class UniBedroomsDataBaseClass implements UniBedroomsDataBase {

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;

    /**
     * The users in the database
     */
    private DoubleList<User> users;

    public UniBedroomsDataBaseClass(){
        users = new DoubleList<>();
    }


    @Override
    public void addStudent(String login, String name, int age, String local, String university) throws UserAlreadyExistsException {
        if(searchUser(login)!=null)
            throw new UserAlreadyExistsException();
        else{
            users.addLast(new StudentClass(login,name,university,age,name));
        }
    }





    private User searchUser(String login){
        User user = null;
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            user = it.next();
            if(user.getLogin().equals(login))
                break;
        }
        return user;
    }
}
