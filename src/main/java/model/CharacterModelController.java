package model;

import model.mappings.Characters;
import model.mappings.Skills;
import model.mappings.Talents;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CharacterModelController extends Thread{
    private Session session;
    private SessionFactory factory;
    private ModelController modelController;
    private DictionariesModelController dictionariesModelController;
    private Characters character;
    private Integer id;
    private ArrayList<Talents>talents;
    private ArrayList<Skills> skills;

    private Boolean deleteCharacter=false;
    private Boolean updateCharacter=false;
    private Boolean saveCharacter=false;
    private Boolean getCharacterSignatures=false;
    private Boolean getCharacter=false;
    private Boolean getCharacters=false;
    private Boolean getCharacterId=false;
    public CharacterModelController(SessionFactory factory, ModelController modelController){
    this.factory=factory;
    this.modelController=modelController;
}

    public void deleteCharacter(Characters character){
        this.character=character;
        deleteCharacter=true;
    }
    private void deleteCharacterAsynch(){
        session=beginSession();
        Transaction t=session.beginTransaction();
        session.delete(character);
        t.commit();
        endSession(session);
    }

    public void updateCharacter(Characters character){
        this.character=character;
        updateCharacter=true;
    }
    private void updateCharacterAsynch(){
        session=beginSession();
        Transaction t=session.beginTransaction();
        session.update(character);
        t.commit();
        endSession(session);
    }
    public void saveCharacter(Characters character, ArrayList<Skills> skills, ArrayList<Talents>talents){
        this.character=character;
        this.skills=skills;
        this.talents=talents;
        saveCharacter=true;
    }
    private void saveCharacterAsynch(){
        character.setCareerId(character.getCareerId());
        session=beginSession();
        Transaction t = session.beginTransaction();
        session.save(character);
        t.commit();
        endSession(session);

    }

    public void getCharacterSignatures(DictionariesModelController dictionariesModelController){
        this.dictionariesModelController=dictionariesModelController;
        getCharacterSignatures=true;
    }
    private ArrayList<CharacterSignatures> getCharacterSignaturesAsynch() {
        session=beginSession();
        ArrayList<CharacterSignatures> signatures= new ArrayList<>();
        ArrayList<Characters> characters=this.getCharactersAsynch();
        CharacterSignatures tempSignature;
        for(int i=0;i<characters.size();i++){
            tempSignature=new CharacterSignatures("race","name");
            tempSignature.setCharacterId(characters.get(i).getCharacterId());
            tempSignature.setName(characters.get(i).getCharacterName());
            dictionariesModelController.getRace(characters.get(i).getRaceId());
            tempSignature.setRace(dictionariesModelController.getRaceAsynch().getRaceName());
            signatures.add(tempSignature);
        }
        endSession(session);
        return signatures;
    }

    public void getCharacters(){
        getCharacters=true;
    }
    private ArrayList<Characters> getCharactersAsynch(){
        session=beginSession();
        ArrayList<Characters> characters;
        Query query=session.createQuery("from Characters");
        characters=(ArrayList<Characters>)query.list();
        endSession(session);
        return characters;
    }
    public void getCharacterId(Characters character){
        this.character=character;
        getCharacterId=true;
    }
    public Integer getCharacterIdAsynch(){
        session=beginSession();
        Characters characterTemp;
        Query query=session.createQuery("from Characters where characterName=:name");
        query.setParameter("name",character.getCharacterName());
        characterTemp=(Characters) query.list().get(0);
        endSession(session);
        return characterTemp.getCharacterId();
    }
    public void getCharacter(Integer characterId){
        id=characterId;
        getCharacter=true;
    }
    private Characters getCharacterAsynch(){
        session=beginSession();
        Characters character;
        Query query=session.createQuery("from Characters where characterId=:id");
        query.setParameter("id",id);
        character=(Characters) query.list().get(0);
        endSession(session);
        return character;
    }

    @Override
    public void run() {
        if(deleteCharacter){
            deleteCharacterAsynch();
            modelController.confirmDeleting();
        }else if(updateCharacter){
            updateCharacterAsynch();
        }else if(saveCharacter){
            saveCharacterAsynch();
            id= getCharacterIdAsynch();
            modelController.saveSkillsTalentsOfCharacter(getCharacterAsynch(),skills,talents);
        }else if(getCharacter){
            modelController.returnCharacter(this.getCharacterAsynch());
        }else if(getCharacters){
            modelController.returnCharacters(this.getCharactersAsynch());
        }else if(getCharacterSignatures){
            modelController.returnCharacterSignatures(this.getCharacterSignaturesAsynch());
        }else if(getCharacterId){
            modelController.returnCharacterId(this.getCharacterIdAsynch());
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
