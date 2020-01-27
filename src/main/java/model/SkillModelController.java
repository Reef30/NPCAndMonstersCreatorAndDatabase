package model;

import model.mappings.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SkillModelController extends Thread {
    private SessionFactory factory;
    private Session session;
    private ModelController modelController;
    private Integer id=-1;
    private Integer level=-1;
    private String name;
    private ArrayList<Skills> skills=new ArrayList<>();
    private ArrayList<Skills> skillsCreate=new ArrayList<>();
    private Characters character;
    private Boolean getSkillsFromRace=false;
    private Boolean getSkillsFromCareer=false;
    private Boolean getSkillByName=false;
    private Boolean getSkillsOfCharacter=false;
    private Boolean updateSkillsOfCharacter=false;
    private Boolean createSkillsOfCharacter=false;
    public SkillModelController(SessionFactory factory, ModelController modelController){
        this.factory=factory;
        this.modelController=modelController;
    }

    public void getSkillByName(String name){
        this.name=name;
        getSkillByName=true;
    }
    private Skills getSkillByNameAsynch(){
        Skills skill;
            session=beginSession();
            Query query=session.createQuery("from Skills where skillName=:name");
            query.setParameter("name",name);
           skill=(Skills) query.list().get(0);
            endSession(session);
        return skill;
    }

    public void getSkillsFromRace(Integer raceId){
        id=raceId;
        getSkillsFromRace=true;
    }
    private ArrayList<Skills> getSkillsFromRaceAsynch(){
        ArrayList<Skills>skills=new ArrayList<>();
        skills.clear();
        ArrayList<Integer>skillsIds=this.getSkillsIdsFromRace(id);
        for(int i=0;i<skillsIds.size();i++){
            session=beginSession();
            Query query=session.createQuery("from Skills where skillId=:id");
            query.setParameter("id",skillsIds.get(i));
                skills.add(((ArrayList<Skills>)query.list()).get(0));
            endSession(session);
        }
        return skills;
    }
    private ArrayList<Integer> getSkillsIdsFromRace(Integer raceId){
        ArrayList<Integer> skillsIds=new ArrayList<>();
        ArrayList<SkillOfRace>skillsOfRace;
        session=beginSession();
        Query query=session.createQuery("from SkillOfRace where raceId=:id");
        query.setParameter("id",raceId);
        skillsOfRace= (ArrayList<SkillOfRace>)query.list();
        endSession(session);
        for(int i=0;i<skillsOfRace.size();i++){
            skillsIds.add(skillsOfRace.get(i).getSkillId());
        }
        return skillsIds;
    }

    public void getSkillsFromCareer(Integer careerId,Integer level){
        id=careerId;
        this.level=level;
        getSkillsFromCareer=true;
    }
    private ArrayList<Skills>getSkillsFromCareerAsynch(){
        ArrayList<Skills>skills=new ArrayList<>();
        ArrayList<Skills>skillsTemp;
        skills.clear();
        ArrayList<Integer>skillIds=this.getSkillsIdsFromCareer(id,level);
        for(int i=0;i<skillIds.size();i++){
            session=this.beginSession();
            Query query=session.createQuery("from Skills where skillId=:id");
            query.setParameter("id",skillIds.get(i));
            if(i==0){
                skills= (ArrayList<Skills>)query.list();
            }else{
                skillsTemp=(ArrayList<Skills>)query.list();
                skills.addAll(skillsTemp);
            }
            this.endSession(session);
        }
        return skills;
    }
    private ArrayList<Integer> getSkillsIdsFromCareer(Integer careerId,Integer level){
        session=beginSession();
        ArrayList<Integer> skillIds=new ArrayList<>();
        ArrayList<SkillOfCareer>skillsOfCareer;
        Query query=session.createQuery("from SkillOfCareer where careerId=:id");
        query.setParameter("id",careerId);
        skillsOfCareer= (ArrayList<SkillOfCareer>)query.list();
        for(int i=0;i<skillsOfCareer.size();i++){
            if(skillsOfCareer.get(i).getCareerLevel()<=level)
                skillIds.add(skillsOfCareer.get(i).getSkillId());
        }
        endSession(session);
        return skillIds;
    }

    public void getSkillsOfCharacter(Characters character){
        this.character=character;
        getSkillsOfCharacter=true;
    }
    private ArrayList<Skills>getSkillsOfCharacterAsynch(){
        ArrayList<Skills> skills=new ArrayList<>();
        ArrayList<Skills>skillsTemp;
        skills.clear();
        ArrayList<Integer>skillIds=
                getSkillsIdsOfCharacter(character.getCharacterId());
        for(int i=0;i<skillIds.size();i++){
            session=beginSession();
            Query query=session.createQuery("from Skills where skillId=:id");
            query.setParameter("id",skillIds.get(i));
            if(i==0){
                skills= (ArrayList<Skills>)query.list();
            }else{
                skillsTemp=(ArrayList<Skills>)query.list();
                skills.addAll(skillsTemp);
            }
            endSession(session);
        }
        return skills;
    }
    private ArrayList<Integer> getSkillsIdsOfCharacter(Integer characterId){
        session=beginSession();
        ArrayList<Integer> skillIds=new ArrayList<>();
        ArrayList<SkillsOfCharacter>skillsOfCharacter;
        Query query=session.createQuery("from SkillsOfCharacter where characterId=:id");
        query.setParameter("id",characterId);
        skillsOfCharacter= (ArrayList<SkillsOfCharacter>)query.list();
        for(int i=0;i<skillsOfCharacter.size();i++){
            skillIds.add(skillsOfCharacter.get(i).getSkillId());
        }
        endSession(session);
        return skillIds;
    }

    public void updateSkillsOfCharacter(Characters character, ArrayList<Skills>skills){
        this.character=character;
        id=character.getCharacterId();
        this.skills=skills;
        updateSkillsOfCharacter=true;
    }
    private void updateSkillsOfCharacterAsynch(){
        ArrayList<SkillsOfCharacter>skillsOfCharacter;
        session=this.beginSession();
        Transaction t=session.beginTransaction();
        Query query=session.createQuery("from SkillsOfCharacter where characterId=:id");
        query.setParameter("id",character.getCharacterId());
        skillsOfCharacter= (ArrayList<SkillsOfCharacter>)query.list();
        t.commit();
        endSession(session);
        Boolean remove;
        for(int i=0;i<skillsOfCharacter.size();i++){
            remove=true;
            for(int j=0;j<skills.size();j++){
                if(skillsOfCharacter.get(i).getSkillId()==skills.get(j).getSkillId()){
                    remove=false;
                }

            }
            if(remove){
                session=this.beginSession();
                t=session.beginTransaction();
                Query q=session.createQuery("delete from SkillsOfCharacter where skillsOfCharacterId=:id");
                q.setParameter("id",skillsOfCharacter.get(i).getSkillsOfCharacterId());
                q.executeUpdate();
                t.commit();
                endSession(session);
            }
        }
        for(int i=0;i<skills.size();i++){
            Boolean add=true;
            for(int j=0;j<skillsOfCharacter.size();j++){
                if(skillsOfCharacter.get(j).getSkillId()==skills.get(i).getSkillId())
                    add=false;
            }
            if(add){
                skillsCreate.clear();
                skillsCreate.add(skills.get(i));
                this.createSkillsOfCharacterAsynch();
            }
        }
    }
    public void createSkillsOfCharacter(Integer characterId,ArrayList<Skills> skills){
        id=characterId;
        this.skillsCreate=skills;
        createSkillsOfCharacter=true;
    }
    public void createSkillsOfCharacterAsynch(){
        SkillsOfCharacter skillOfCharacter=new SkillsOfCharacter();
        for(int i=0;i<skillsCreate.size();i++){
            skillOfCharacter.setCharacterId(id);
            skillOfCharacter.setSkillId(skillsCreate.get(i).getSkillId());
            skillOfCharacter.setSpecializationId(0);
            skillOfCharacter.setTimesTaken(5);
            session=beginSession();
            Transaction t = session.beginTransaction();
            session.save(skillOfCharacter);
            t.commit();
            endSession(session);
        }
    }


    private Session beginSession(){
        Session session = factory.openSession();
        return session;
    }
    private void endSession(Session session){
        session.close();
    }

    @Override
    public void run() {
        if(getSkillsFromRace){
            modelController.returnSkillsFromRace(this.getSkillsFromRaceAsynch());
        }else if(getSkillsFromCareer){
            modelController.returnSkillsFromCareer(this.getSkillsFromCareerAsynch());
        }else if(getSkillsOfCharacter){
            modelController.returnSkillsOfCharacter(this.getSkillsOfCharacterAsynch());
        }else if(getSkillByName){
            modelController.returnSkillByName(this.getSkillByNameAsynch());
        }else if(updateSkillsOfCharacter){
            updateSkillsOfCharacterAsynch();
        }else if(createSkillsOfCharacter){
            createSkillsOfCharacterAsynch();
        }
    }
}
