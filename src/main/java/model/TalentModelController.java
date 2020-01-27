package model;
import model.mappings.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;


public class TalentModelController extends Thread {
    private SessionFactory factory;
    private Session session;
    private ModelController modelController;
    private Integer id=-1;
    private Integer level=-1;
    private String name;
    private ArrayList<Talents> talents=new ArrayList<>();
    private ArrayList<Talents> talentsCreate =new ArrayList<>();
    private Characters character;
    private Boolean getTalentsFromRace=false;
    private Boolean getTalentsFromCareer=false;
    private Boolean getTalentByName=false;
    private Boolean getTalentsOfCharacter=false;
    private Boolean updateTalentsOfCharacter=false;
    private Boolean createTalentsOfCharacter=false;
    public TalentModelController(SessionFactory factory,ModelController modelController){
        this.factory=factory;
        this.modelController=modelController;
    }

    public void getTalentByName(String name){
        this.name=name;
        getTalentByName=true;
    }
    public Talents getTalentByNameAsynch(){
        Talents talent;
        session=beginSession();
        Query query=session.createQuery("from Talents where talentName=:name");
        query.setParameter("name",name);
        talent=(Talents) query.list().get(0);
        endSession(session);
        return talent;
    }

    public void getTalentsFromRace(Integer raceId){
        id=raceId;
        getTalentsFromRace=true;
    }
    private ArrayList<Talents> getTalentsFromRaceAsynch(){
        ArrayList<Talents>talents=new ArrayList<>();
        ArrayList<Talents>talentsTemp;
        talents.clear();
        ArrayList<Integer>talentIds=this.getTalentsIdsFromRace(id);
        for(int i=0;i<talentIds.size();i++){
            session=beginSession();
            Query query=session.createQuery("from Talents where talentId=:id");
            query.setParameter("id",talentIds.get(i));
            talentsTemp=(ArrayList<Talents>)query.list();
            endSession(session);
            talents.addAll(talentsTemp);

        }
        return talents;
    }
    private ArrayList<Integer> getTalentsIdsFromRace(Integer raceId){
        session=beginSession();
        ArrayList<Integer> talentIds=new ArrayList<>();
        ArrayList<TalentsOfRace>talentsOfRace;
        Query query=session.createQuery("from TalentsOfRace where raceId=:id");
        query.setParameter("id",raceId);
        talentsOfRace= (ArrayList<TalentsOfRace>)query.list();
        endSession(session);
        for(int i=0;i<talentsOfRace.size();i++){
            talentIds.add(talentsOfRace.get(i).getTalentId());
        }
        return talentIds;
    }

    public void getTalentsFromCareer(Integer careerId,Integer level){
        getTalentsFromCareer=true;
        id=careerId;
        this.level=level;
    }
    private ArrayList<Talents>getTalentsFromCareerAsynch(){
        ArrayList<Talents>talents=new ArrayList<>();
        ArrayList<Talents>talentsTemp;
        talents.clear();
        ArrayList<Integer>talentIds=this.getTalentsIdsFromCareer(id,level);
        for(int i=0;i<talentIds.size();i++){
            session=this.beginSession();
            Query query=session.createQuery("from Talents where talentId=:id");
            query.setParameter("id",talentIds.get(i));
            if(i==0){
                talents= (ArrayList<Talents>)query.list();
            }else{
                talentsTemp=(ArrayList<Talents>)query.list();
                talents.addAll(talentsTemp);
            }
            this.endSession(session);
        }
        return talents;
    }
    private ArrayList<Integer> getTalentsIdsFromCareer(Integer careerId,Integer level){
        session=beginSession();
        ArrayList<Integer> talentIds=new ArrayList<>();
        ArrayList<TalentsOfCareer>talentsOfCareer;
        Query query=session.createQuery("from TalentsOfCareer where careerId=:id");
        query.setParameter("id",careerId);
        talentsOfCareer= (ArrayList<TalentsOfCareer>)query.list();
        for(int i=0;i<talentsOfCareer.size();i++){
            if(talentsOfCareer.get(i).getCareerLevel()<=level)
                talentIds.add(talentsOfCareer.get(i).getTalentId());
        }
        endSession(session);
        return talentIds;
    }

    public void getTalentsOfCharacter(Characters character){
        this.character=character;
        getTalentsOfCharacter=true;
    }
    private ArrayList<Talents>getTalentsOfCharacterAsynch(){
        ArrayList<Talents> talents=new ArrayList<>();
        ArrayList<Talents>talentsTemp;
        ArrayList<Integer>talentIds=this.getTalentsIdsOfCharacter(character.getCharacterId());
        for(int i=0;i<talentIds.size();i++){
            session=beginSession();
            Query query=session.createQuery("from Talents where talentId=:id");
            query.setParameter("id",talentIds.get(i));
            if(i==0){
                talents= (ArrayList<Talents>)query.list();
            }else{
                talentsTemp=(ArrayList<Talents>)query.list();
                talents.addAll(talentsTemp);
            }
            endSession(session);
        }
        return talents;
    }
    private ArrayList<Integer> getTalentsIdsOfCharacter(Integer characterId){
        session=beginSession();
        ArrayList<Integer> talentIds=new ArrayList<>();
        ArrayList<TalentsOfCharacter>talentsOfCharacter;
        Query query=session.createQuery("from TalentsOfCharacter where characterId=:id");
        query.setParameter("id",characterId);
        talentsOfCharacter= (ArrayList<TalentsOfCharacter>)query.list();
        for(int i=0;i<talentsOfCharacter.size();i++){
            talentIds.add(talentsOfCharacter.get(i).getTalentId());
        }
        endSession(session);
        return talentIds;
    }

    public void updateTalentsOfCharacter(Characters character,ArrayList<Talents> talents){
        this.character=character;
        id=character.getCharacterId();
        this.talents=talents;
        updateTalentsOfCharacter=true;
    }
    private void updateTalentsOfCharacterAsynch(){
        ArrayList<TalentsOfCharacter>talentsOfCharacter;
        session=this.beginSession();
        Transaction t=session.beginTransaction();
        Query query=session.createQuery("from TalentsOfCharacter where characterId=:id");
        query.setParameter("id",character.getCharacterId());
        talentsOfCharacter= (ArrayList<TalentsOfCharacter>)query.list();
        t.commit();
        endSession(session);
        Boolean remove;
        for(int i=0;i<talentsOfCharacter.size();i++){
            remove=true;
            for(int j=0;j<talents.size();j++){
                if(talentsOfCharacter.get(i).getTalentId()==talents.get(j).getTalentId()){
                    remove=false;
                }

            }
            if(remove){
                session=this.beginSession();
                t=session.beginTransaction();
                Query q=session.createQuery("delete from TalentsOfCharacter where talentOfCharacterId=:id");
                q.setParameter("id",talentsOfCharacter.get(i).getTalentOfCharacterId());
                q.executeUpdate();
                t.commit();
                endSession(session);
            }
        }
        for(int i=0;i<talents.size();i++){
            Boolean add=true;
            for(int j=0;j<talentsOfCharacter.size();j++){
                if(talentsOfCharacter.get(j).getTalentId()==talents.get(i).getTalentId())
                    add=false;
            }
            if(add){
                talentsCreate.clear();
                talentsCreate.add(talents.get(i));
                this.createTalentsOfCharacterAsynch();
            }
        }
    }
    public void createTalentsOfCharacter(Integer characterId,ArrayList<Talents> talents){
        id=characterId;
        talentsCreate=talents;
        createTalentsOfCharacter=true;
    }
    private void createTalentsOfCharacterAsynch(){
        TalentsOfCharacter talentOfCharacter=new TalentsOfCharacter();
        for(int i=0;i<talentsCreate.size();i++){
            talentOfCharacter.setCharacterId(id);
            talentOfCharacter.setTalentId(talentsCreate.get(i).getTalentId());
            talentOfCharacter.setTimesTaken(1);
            session=beginSession();
            Transaction t = session.beginTransaction();
            session.save(talentOfCharacter);
            t.commit();
            endSession(session);
        }
    }

    @Override
    public void run() {
        if(getTalentsFromRace){
            modelController.returnTalentsFromRace(getTalentsFromRaceAsynch());
        }else if(getTalentsFromCareer){
            modelController.returnTalentsFromCareer(getTalentsFromCareerAsynch());
        }else if(getTalentByName){
            modelController.returnTalentByName(getTalentByNameAsynch());
        }else if(getTalentsOfCharacter){
            modelController.returnTalentsOfCharacter(this.getTalentsOfCharacterAsynch());
        }else if(updateTalentsOfCharacter){
            this.updateTalentsOfCharacterAsynch();
        }else if(createTalentsOfCharacter){
            this.createTalentsOfCharacterAsynch();
        }
    }
    private Session beginSession(){
        Session session = factory.openSession();
        return session;
    }
    private void endSession(Session session){
        session.close();
    }

}
