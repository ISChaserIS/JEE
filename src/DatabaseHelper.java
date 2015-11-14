import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Olcha on 14.11.2015.
 */
public class DatabaseHelper {
    private static SessionFactory dbSessions;
    private void startWorkWithDB(){
        dbSessions = new AnnotationConfiguration()
                .configure("/resources/hibernate.cfg.xml")
                .addAnnotatedClass(MyTable.class)
                .buildSessionFactory();

        //dbSessions.close();
    }
    public String checkUser(String login, String password){
        startWorkWithDB();
        Session session = dbSessions.openSession();
//        List loginList = (List) session.createQuery("SELECT " + login + " FROM MyTable");
//        List passwordList = (List) session.createQuery("SELECT " + password + " FROM MyTable");
        try {
            List usersList = session.createQuery("FROM MyTable").list();
            for (Iterator iterator = usersList.iterator(); iterator.hasNext(); ) {
                MyTable counter = (MyTable) iterator.next();

                if (counter.getLogin().equals(login) && counter.getPassword().equals(password)){
                    return "Добро пожаловать " + counter.getFirst_name() + " " + counter.getLast_name();
                }

            }
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
            dbSessions.close();
        }
        return "Простите, но ваш логин или пароль введён неверно. Пожалуйста, введите снова пароль и логин";
    }

}
