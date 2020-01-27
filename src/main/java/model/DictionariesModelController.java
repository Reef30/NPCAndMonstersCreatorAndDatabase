package model;

import model.mappings.Careers;
import model.mappings.Classes;
import model.mappings.RaceStats;
import model.mappings.Races;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class DictionariesModelController extends Thread {
    private Session session;
    private SessionFactory factory;
    private ModelController modelController;
    Boolean getRaces=false;
    Boolean getClasses=false;
    Boolean getCareers=false;
    Boolean getClass=false;
    Boolean getRace=false;
    Boolean getRaceStats=false;
    Boolean getCareerId=false;
    Boolean getCareer=false;
    Boolean getClassOfCareer=false;
    Boolean getRaceId=false;
    Boolean getClassId=false;
    Integer id=-1;
    String name="";
    public DictionariesModelController(SessionFactory factory,ModelController modelController){
        this.factory=factory;
        this.modelController=modelController;
    }

    public void getRaces(){
       getRaces=true;
    }
    private ArrayList<Races> getRacesAsynch(){
        session=beginSession();
        Query query =session.createQuery("from Races");
        ArrayList<Races> raceListTemp = (ArrayList<Races>) query.list();
        endSession(session);
        getRaces=false;
        return raceListTemp;
    }

    public void getClasses(){
        getClasses=true;
    }
    private ArrayList<Classes> getClassesAsynch(){
        session=beginSession();
        Session session= this.beginSession();
        Query query =session.createQuery("from Classes");
        ArrayList<Classes> classList = (ArrayList<Classes>) query.list();
        this.endSession(session);
        endSession(session);
        return classList;
    }

    public void getClasses(Integer classId){
        id=classId;
        getClass=true;
    }
    public Classes getClassAsynch(){
        session=beginSession();
        Classes classes;
        Query query=session.createQuery("from Classes where classId=:id");
        query.setParameter("id",id);
        classes=(Classes)query.list().get(0);
        endSession(session);
        return classes;
    }

    public void getCareersFromClass(Integer classId){
        getCareers=true;
        id=classId;
    }
    public ArrayList<Careers> getCareersAsynch(){
        session=beginSession();
        Query query =session.createQuery("from Careers where classId =:id");
        query.setParameter("id",id);
        ArrayList<Careers> careerList = (ArrayList<Careers>) query.list();
        endSession(session);
        return careerList;
    }

    public void getRace(Integer raceId){
        id=raceId;
        getRace=true;
    }
    public Races getRaceAsynch(){
        session=beginSession();
        Query query =session.createQuery("from Races where raceId=:id");
        query.setParameter("id",id);
        Races raceTemp = (Races) query.list().get(0);
        endSession(session);
        return raceTemp;
    }

    public void getRaceStats(Integer raceId){
        id=raceId;
        getRaceStats=true;
    }
    private RaceStats getRaceStatsAsynch(){
        session=beginSession();
        RaceStats raceStats;
        Query query=session.createQuery("from RaceStats where raceId =: id");
        query.setParameter("id",id);
        raceStats=(RaceStats)query.list().get(0);
        endSession(session);
        return raceStats;
    }

    public void getCareerId(String careerName){
        name=careerName;
        getCareerId=true;
    }
    private Integer getCareerIdAsynch(){
        session=beginSession();
        Careers career;
        Query query=session.createQuery("from Careers where careerName=:name");
        query.setParameter("name",name);
        career=(Careers) query.list().get(0);
        endSession(session);
        return career.getCareerId();
    }

    public void getCareer(Integer careerId){
        id=careerId;
        getCareer=true;
    }
    private Careers getCareerAsynch(){
        session=beginSession();
        Careers career;
        Query query=session.createQuery("from Careers where careerId=:id");
        query.setParameter("id",id);
        career=(Careers) query.list().get(0);
        endSession(session);
        return career;
    }

    public void getClassOfCareer(Integer careerId){
        id=careerId;
        getClassOfCareer=true;
    }
    private Classes getClassOfCareerAsynch(){
        Classes classes;
        Careers career=this.getCareerAsynch();
        endSession(session);
        session=beginSession();
        Query query=session.createQuery("from Classes where classId=:id");
        query.setParameter("id",career.getClassId());
        classes=(Classes) query.list().get(0);
        endSession(session);
        return classes;
    }

    public void getRaceId(String raceName){
        name=raceName;
        getRaceId=true;
    }
    private Integer getRaceIdAsynch(){
        session=beginSession();
        Races race;
        Query query=session.createQuery("from Races where raceName=:name");
        query.setParameter("name",name);
        race=(Races) query.list().get(0);
        endSession(session);
        return race.getRaceId();
    }

    public void getClassId(String className){
        name=className;
        getClassId=true;
    }
    private Integer getClassIdAsynch(){
        session=beginSession();
        Classes classes;
        Query query=session.createQuery("from Classes where className=:name");
        query.setParameter("name",name);
        classes=(Classes) query.list().get(0);
        endSession(session);
        return classes.getClassId();
    }
    @Override
    public void run() {
        //sleep();
        if(getRaces){
            modelController.returnRaces(this.getRacesAsynch());
        }else if(getClasses){
            modelController.returnClasses(this.getClassesAsynch());
        }else if(getCareers){
            modelController.returnCareers(this.getCareersAsynch());
        }else if(getClass){
            modelController.returnClass(this.getClassAsynch());
        }else if(getRace){
            modelController.returnRace(this.getRaceAsynch());
        }else if(getRaceStats){
            modelController.returnRaceStats(this.getRaceStatsAsynch());
        }else if(getCareerId){
            modelController.returnCareerId(this.getCareerIdAsynch());
        }else if(getCareer){
            modelController.returnCareer(this.getCareerAsynch());
        } else if(getClassOfCareer){
            modelController.returnClassOfCareer(this.getClassOfCareerAsynch());
        }else if(getRaceId){
            modelController.returnRaceId(this.getRaceIdAsynch());
        }else if(getClassId){
            modelController.returnClassId(this.getClassIdAsynch());
        }
    }

    private Session beginSession(){
        Session session = factory.openSession();
        return session;
    }
    private void endSession(Session session){
        session.close();
    }
    private void sleep(){
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
