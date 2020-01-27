package model;

import controller.DatabaseListener;
import controller.MasterController;
import model.mappings.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;

public class ModelController{
    private MasterController masterController;
    private SessionFactory factory;
    private Configuration cfg;
    private TalentModelController talentModelController;
    private SkillModelController skillModelController;
    private CharacterModelController characterModelController;
    private DictionariesModelController dictionariesModelController;
    private ArrayList<DatabaseListener> listeners;
    private Thread fxThread;
    public ModelController(MasterController masterController){
        fxThread=Thread.currentThread();
        this.masterController=masterController;
        this.configuration();
        listeners=new ArrayList<>();
    }

    public synchronized void getRaces(){
        newDictionariesController();
        dictionariesModelController.getRaces();
        dictionariesModelController.start();

    }
    public synchronized void returnRaces(ArrayList<Races>races){
        masterController.getExchanger().setRacesList(races);
       notifyListeners();
    }

    public synchronized void getClasses(){
        newDictionariesController();
        dictionariesModelController.getClasses();
        dictionariesModelController.start();
    }
    public synchronized void returnClasses(ArrayList<Classes> classes){
        masterController.getExchanger().setClassesList(classes);
        notifyListeners();
    }

    public synchronized void getCareersFromClass(Integer classId){
        newDictionariesController();
        dictionariesModelController.getCareersFromClass(classId);
        dictionariesModelController.start();
    }
    public synchronized void returnCareers(ArrayList<Careers>careers){
        masterController.getExchanger().setCareersList(careers);
        notifyListeners();
    }

    public synchronized void getRace(Integer raceId){
        newDictionariesController();
        dictionariesModelController.getRace(raceId);
        dictionariesModelController.start();
    }
    public synchronized void returnRace(Races race){
        masterController.getExchanger().setRace(race);
        notifyListeners();
    }

    public synchronized void getClasses(Integer classId){
        newDictionariesController();
        dictionariesModelController.getClasses(classId);
        dictionariesModelController.start();
    }
    public synchronized void returnClass(Classes classes){
        masterController.getExchanger().setClasses(classes);
        notifyListeners();
    }

    public synchronized void getRaceStats(Integer raceId){
        newDictionariesController();
        dictionariesModelController.getRaceStats(raceId);
        dictionariesModelController.start();
    }
    public synchronized void returnRaceStats(RaceStats stats){
        masterController.getExchanger().setRaceStats(stats);
        notifyListeners();
    }

    public synchronized void getCareerId(String careerName){
        newDictionariesController();
        dictionariesModelController.getCareerId(careerName);
        dictionariesModelController.start();
    }
    public synchronized void returnCareerId(Integer careerId){
        masterController.getExchanger().setCareerId(careerId);
        notifyListeners();
    }

    public synchronized void getCareer(Integer careerId){
        newDictionariesController();
       dictionariesModelController.getCareer(careerId);
       dictionariesModelController.start();
    }
    public synchronized void returnCareer(Careers career){
        masterController.getExchanger().setCareer(career);
        notifyListeners();
    }

    public synchronized void getClassOfCareer(Integer careerId){
        newDictionariesController();
        dictionariesModelController.getClassOfCareer(careerId);
        dictionariesModelController.start();
    }
    public synchronized void returnClassOfCareer(Classes classe){
        masterController.getExchanger().setClassOfCareer(classe);
        notifyListeners();
    }

    public synchronized void getRaceId(String name){
        newDictionariesController();
        dictionariesModelController.getRaceId(name);
        dictionariesModelController.start();
    }
    public synchronized void returnRaceId(Integer raceId){
        masterController.getExchanger().setRaceId(raceId);
        notifyListeners();
    }

    public synchronized void getClassId(String name){
        newDictionariesController();
        dictionariesModelController.getClassId(name);
        dictionariesModelController.start();
    }
    public synchronized void returnClassId(Integer classId){
        masterController.getExchanger().setClassId(classId);
        notifyListeners();
    }



    public synchronized void getTalentByName(String name){
        newTalentController();
        talentModelController.getTalentByName(name);
        talentModelController.start();
    }
    public synchronized void returnTalentByName(Talents talent){
        masterController.getExchanger().setTalent(talent);
        for(int i=0;i<listeners.size();i++){
            listeners.get(i).dataAcquired();
        }
    }

    public synchronized void getTalentsFromRace(Integer raceId){
       newTalentController();
        talentModelController.getTalentsFromRace(raceId);
        talentModelController.start();
    }
    public synchronized void returnTalentsFromRace(ArrayList<Talents>talents){
        masterController.getExchanger().setRaceTalentsList(talents);
        for(int i=0;i<listeners.size();i++){
            listeners.get(i).dataAcquired();
        }
    }

    public synchronized void getTalentsFromCareer(Integer careerId,Integer level){
        newTalentController();
        talentModelController.getTalentsFromCareer(careerId,level);
        talentModelController.start();
    }
    public synchronized void returnTalentsFromCareer(ArrayList<Talents> talents){
        masterController.getExchanger().setCareerTalentsList(talents);
        for(int i=0;i<listeners.size();i++){
            listeners.get(i).dataAcquired();
        }
    }

    public synchronized void getTalentsOfCharacter(Characters character){
        newTalentController();
        talentModelController.getTalentsOfCharacter(character);
        talentModelController.start();
    }
    public synchronized void returnTalentsOfCharacter(ArrayList<Talents> talents){
        masterController.getExchanger().setCharacterTalentsList(talents);
        notifyListeners();
    }



    public synchronized void getSkillByName(String name){
        newSkillController();
        skillModelController.getSkillByName(name);
        skillModelController.start();
    }
    public synchronized void returnSkillByName(Skills skill){
        masterController.getExchanger().setSkill(skill);
        notifyListeners();
    }

    public synchronized void getSkillsFromRace(Integer raceId){
        newSkillController();
        skillModelController.getSkillsFromRace(raceId);
        skillModelController.start();
    }
    public synchronized void returnSkillsFromRace(ArrayList<Skills>skills){
        masterController.getExchanger().setRaceSkillsList(skills);
        notifyListeners();
    }

    public synchronized void getSkillsFromCareer(Integer careerId,Integer level){
        newSkillController();
        skillModelController.getSkillsFromCareer(careerId,level);
        skillModelController.start();
    }
    public synchronized void returnSkillsFromCareer(ArrayList<Skills>skills){
        masterController.getExchanger().setCareerSkillsList(skills);
        notifyListeners();
    }

    public synchronized void getSkillsOfCharacter(Characters character){
        newSkillController();
        skillModelController.getSkillsOfCharacter(character);
        skillModelController.start();
    }
    public synchronized void returnSkillsOfCharacter(ArrayList<Skills>skills){
        masterController.getExchanger().setCharacterSkillsList(skills);
        notifyListeners();
    }




    public synchronized void deleteCharacter(Characters character){
        ArrayList<Talents>talents=new ArrayList<>();
        talents.clear();
        ArrayList<Skills>skills=new ArrayList<>();
        skills.clear();

        newCharacterController();
       characterModelController.deleteCharacter(character);
       characterModelController.start();

       newTalentController();
       talentModelController.updateTalentsOfCharacter(character,talents);
       talentModelController.start();

       newSkillController();
       skillModelController.updateSkillsOfCharacter(character,skills);
       skillModelController.start();
    }
    public synchronized void confirmDeleting(){
        masterController.getExchanger().setDeleted(true);
        notifyListeners();
    }

    public synchronized void updateCharacter(Characters character,ArrayList<Skills> skills,ArrayList<Talents> talents){
        newCharacterController();
        characterModelController.updateCharacter(character);
        characterModelController.start();

        newSkillController();
        skillModelController.updateSkillsOfCharacter(character,skills);
        skillModelController.start();

        newTalentController();
        talentModelController.updateTalentsOfCharacter(character,talents);
        talentModelController.start();
    }

    public synchronized void saveCharacter(Characters character,ArrayList<Skills> skills,ArrayList<Talents> talents){
        newCharacterController();
        characterModelController.saveCharacter(character,skills,talents);
        characterModelController.start();
    }
    public synchronized void saveSkillsTalentsOfCharacter(Characters character,ArrayList<Skills> skills,ArrayList<Talents> talents){
        newSkillController();
        skillModelController.createSkillsOfCharacter(character.getCharacterId(),skills);
        skillModelController.start();

        newTalentController();
        talentModelController.createTalentsOfCharacter(character.getCharacterId(),talents);
        talentModelController.start();
    }

    public synchronized void getCharacter(Integer characterId){
        newCharacterController();
       characterModelController.getCharacter(characterId);
       characterModelController.start();
    }
    public synchronized void returnCharacter(Characters character){
        masterController.getExchanger().setCharacter(character);
        notifyListeners();
    }


    public synchronized void getCharacterId(Characters character){
        newCharacterController();
      characterModelController.getCharacterId(character);
      characterModelController.start();
    }
    public synchronized void returnCharacterId(Integer characterId){
        masterController.getExchanger().setCharacterId(characterId);
        notifyListeners();
    }

    public synchronized void getCharacterSignatures() {
        newCharacterController();
        newDictionariesController();
        characterModelController.getCharacterSignatures(dictionariesModelController);
        characterModelController.start();
    }
    public synchronized void returnCharacterSignatures(ArrayList<CharacterSignatures>signatures){
        masterController.getExchanger().setSignatures(signatures);
        notifyListeners();
    }

    public synchronized void getCharacters(){
        newCharacterController();
        characterModelController.getCharacters();
        characterModelController.start();
    }
    public synchronized void returnCharacters(ArrayList<Characters> characters){
        masterController.getExchanger().setCharactersList(characters);
    }

    private synchronized void configuration(){
        cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }
    private synchronized void newTalentController(){
        talentModelController=new TalentModelController(factory,this);
    }
    private synchronized void newSkillController(){
        skillModelController=new SkillModelController(factory,this);
    }
    private synchronized void newDictionariesController(){
        dictionariesModelController=new DictionariesModelController(factory,this);
    }
    private synchronized void newCharacterController(){
        characterModelController=new CharacterModelController(factory,this);
    }
    public synchronized void addListener(DatabaseListener listener){
        listeners.add(listener);
        //System.out.println(listeners.size());
        //System.out.println(listener.toString());
    }
    public synchronized void removeListener(DatabaseListener listener){
        //System.out.println(listener.toString());
        listeners.remove(listener);
        //System.out.println(listeners.size());
    }
    public synchronized Integer getListenersSize(){
        return listeners.size();
    }
    public synchronized void removeAllListeners(){
        listeners.clear();
        //System.out.println("cleared listeners");
        try {
            dictionariesModelController.stop();
            talentModelController.stop();
            skillModelController.stop();
            characterModelController.stop();
        }catch (NullPointerException e){

        }
    }
    private synchronized void notifyListeners(){
        for(int i=0;i<listeners.size();i++){
        listeners.get(i).dataAcquired();
        }
    }
}
