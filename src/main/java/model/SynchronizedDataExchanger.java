package model;

import model.mappings.*;

import java.util.ArrayList;

public  class SynchronizedDataExchanger {
    private Boolean deleted=false;
    public synchronized void setDeleted(Boolean value){
        deleted=value;
    }
    public synchronized Boolean getDeleted(){
        return deleted;
    }

    private Boolean signaturesUpdated=false;
    private ArrayList<CharacterSignatures> signatures;
    public synchronized Boolean getSignaturesUpdated(){
        return signaturesUpdated;
    }
    public synchronized void  setSignatures(ArrayList<CharacterSignatures> signatures){
        this.signatures=signatures;
        signaturesUpdated=true;
    }
    public synchronized ArrayList<CharacterSignatures> getSignatures(){
        signaturesUpdated=false;
        return signatures;
    }

    private Boolean characterIdUpdated=false;
    private Integer characterId;
    public synchronized Boolean getCharacterIdUpdated(){
        return signaturesUpdated;
    }
    public synchronized void  setCharacterId(Integer id){
        this.characterId=id;
        characterIdUpdated=true;
    }
    public synchronized Integer getCharacterId(){
        characterIdUpdated=false;
        return characterId;
    }

    private Boolean raceUpdated=false;
    private Races race;
    public synchronized Boolean getRaceUpdated(){ return raceUpdated; }
    public synchronized Races getRace(){ return race; }
    public synchronized void setRace(Races race){ this.race=race; }

    private Boolean statsUpdated=false;
    private RaceStats stats;
    public synchronized Boolean getRaceStatsUpdated(){
        return statsUpdated; }
    public synchronized RaceStats getRaceStats(){
        statsUpdated=false;
        return stats; }
    public synchronized void setRaceStats(RaceStats stats){
        this.stats=stats;
        statsUpdated=true;
    }

    private Boolean classesUpdated=false;
    private Classes classes;
    public synchronized Boolean getClassesUpdated(){
        return classesUpdated;
    }
    public synchronized Classes getClasses(){
        classesUpdated=false;
        return classes;
    }
    public synchronized void setClasses(Classes classes){
        this.classes=classes;
        classesUpdated=true;
    }

    private Boolean careerUpdated=false;
    private Careers career;
    public synchronized Boolean getCareerUpdated(){
        return careerUpdated;
    }
    public synchronized Careers getCareer(){
        careerUpdated=false;
        return career;
    }
    public synchronized void setCareer(Careers career){
        this.career=career;
        careerUpdated=true;
    }

    private Boolean careerIdUpdated=false;
    private Integer careerId;
    public synchronized Boolean getCareerIdUpdated(){
        return careerIdUpdated;
    }
    public synchronized void setCareerId(Integer careerId){
        this.careerId=careerId;
        careerIdUpdated=true;
    }
    public synchronized Integer getCareerId(){
        careerIdUpdated=false;
        return careerId;
    }

    private Boolean classIdUpdated=false;
    private Integer classId;
    public synchronized Boolean getClassIdUpdated(){
        return classIdUpdated;
    }
    public synchronized void setClassId(Integer classId){
        this.classId=classId;
        classIdUpdated=true;
    }
    public synchronized Integer getClassId(){
        classIdUpdated=false;
        return classId;
    }

    private Boolean raceIdUpdated=false;
    private Integer raceId;
    public synchronized Boolean getRaceIdUpdated(){
        return raceIdUpdated;
    }
    public synchronized void setRaceId(Integer raceId){
        this.raceId=raceId;
        raceIdUpdated=true;
    }
    public synchronized Integer getRaceId(){
        raceIdUpdated=false;
        return raceId;
    }

    private Boolean classOfCareerUpdated=false;
    private Classes classOfCareer;
    public synchronized Boolean getClassOfCareerUpdated(){
        return classOfCareerUpdated;
    }
    public synchronized void setClassOfCareer(Classes classOfCareer){
        this.classOfCareer=classOfCareer;
        classOfCareerUpdated=true;
    }
    public synchronized Classes getClassOfCareer(){
        classOfCareerUpdated=false;
        return classOfCareer;
    }


    private Boolean talentUpdated=false;
    private Talents talent;
    public synchronized Boolean getTalentUpdated(){
        return talentUpdated;
    }
    public synchronized Talents getTalent(){
        talentUpdated=false;
        return talent;
    }
    public synchronized void setTalent(Talents talent){
        this.talent=talent;
        talentUpdated=true;
    }
    private Boolean skillUpdated=false;
    private Skills skill;
    public synchronized Boolean getSkillUpdated(){
        return skillUpdated;
    }
    public synchronized Skills getSkill(){
        skillUpdated=false;
        return skill;
    }
    public synchronized void setSkill(Skills skill){
        this.skill=skill;
        skillUpdated=true;
    }

    private Boolean racesListUpdated= false;
    private ArrayList<Races> racesList;
    public synchronized Boolean getRacesListUpdated(){
        return racesListUpdated;
    }
    public synchronized ArrayList<Races> getRacesList(){
        racesListUpdated=false;
        return racesList;
    }
    public synchronized void setRacesList(ArrayList<Races> racesList){
        this.racesList=racesList;
        racesListUpdated=true;
    }

    private Boolean classesListUpdated=false;
    private ArrayList<Classes> classesList;
    public synchronized Boolean getClassesListUpdated(){
        return classesListUpdated;
    }
    public synchronized ArrayList<Classes> getClassesList(){
        classesListUpdated=false;
        return classesList;
    }
    public synchronized void setClassesList(ArrayList<Classes> classesList){
        this.classesList=classesList;
        classesListUpdated=true;
    }

    private Boolean careersListUpdated=false;
    private ArrayList<Careers> careersList;
    public synchronized Boolean getCareersListUpdated(){
        return careersListUpdated;
    }
    public synchronized ArrayList<Careers> getCareersList(){
        careersListUpdated=false;
        return careersList;
    }
    public synchronized void setCareersList(ArrayList<Careers> careersList){
        this.careersList=careersList;
        careersListUpdated=true;
    }

    private Boolean raceTalentsListUpdated=false;
    private ArrayList<Talents> raceTalentsList;
    public synchronized Boolean getRaceTalentsListUpdated(){return raceTalentsListUpdated;}
    public synchronized ArrayList<Talents> getRaceTalentsList(){
        raceTalentsListUpdated=false;
        return raceTalentsList;
    }
    public synchronized void setRaceTalentsList(ArrayList<Talents> talents){
        raceTalentsList=talents;
        raceTalentsListUpdated=true;
    }
    private Boolean raceSkillsListUpdated=false;
    private ArrayList<Skills> raceSkillsList;
    public synchronized Boolean getRaceSkillsListUpdated(){return raceSkillsListUpdated;}
    public synchronized ArrayList<Skills> getRaceSkillsList(){
        raceSkillsListUpdated=false;
        return raceSkillsList;
    }
    public synchronized void setRaceSkillsList(ArrayList<Skills> skills){
        raceSkillsList=skills;
        raceSkillsListUpdated=true;
    }

    private Boolean careerTalentsListUpdated=false;
    private ArrayList<Talents> careerTalentsList;
    public synchronized Boolean getCareerTalentsListUpdated(){return careerTalentsListUpdated;}
    public synchronized ArrayList<Talents> getCareerTalentsList(){
        careerTalentsListUpdated=false;
        return careerTalentsList;
    }
    public synchronized void setCareerTalentsList(ArrayList<Talents> talents){
        careerTalentsList=talents;
        careerTalentsListUpdated=true;
    }

    private Boolean careerSkillsListUpdated=false;
    private ArrayList<Skills> careerSkillsList;
    public synchronized Boolean getCareerSkillsListUpdated(){return careerSkillsListUpdated;}
    public synchronized ArrayList<Skills> getCareerSkillsList(){
        careerSkillsListUpdated=false;
        return careerSkillsList;
    }
    public synchronized void setCareerSkillsList(ArrayList<Skills> skills){
        careerSkillsList=skills;
        careerSkillsListUpdated=true;
    }

    private Boolean characterTalentsListUpdated=false;
    private ArrayList<Talents> characterTalentsList;
    public synchronized Boolean getCharacterTalentsListUpdated(){return characterTalentsListUpdated;}
    public synchronized ArrayList<Talents> getCharacterTalentsList(){
        characterTalentsListUpdated=false;
        return characterTalentsList;
    }
    public synchronized void setCharacterTalentsList(ArrayList<Talents> talents){
        characterTalentsList=talents;
        characterTalentsListUpdated=true;
    }

    private Boolean characterSkillsListUpdated=false;
    private ArrayList<Skills> characterSkillsList;
    public synchronized Boolean getCharacterSkillsListUpdated(){return characterSkillsListUpdated;}
    public synchronized ArrayList<Skills> getCharacterSkillsList(){
        characterSkillsListUpdated=false;
        return characterSkillsList;
    }
    public synchronized void setCharacterSkillsList(ArrayList<Skills> skills){
        characterSkillsList=skills;
        characterSkillsListUpdated=true;
    }

    private Boolean charactersListUpdated;
    private ArrayList<Characters> charactersList;
    public synchronized Boolean getCharactersListUpdated(){
        return charactersListUpdated;
    }
    public synchronized void setCharactersList(ArrayList<Characters>characters){
        charactersList=characters;
        charactersListUpdated=true;
    }
    public synchronized ArrayList<Characters> getCharactersList(){
        charactersListUpdated=false;
        return charactersList;
    }

    private Boolean characterUpdated=false;
    private Characters character;
    public synchronized Boolean getCharacterUpdated(){
        return characterUpdated;
    }
    public synchronized void setCharacter(Characters character){
        this.character=character;
        characterUpdated=true;
    }
    public synchronized Characters getCharacter(){
        characterUpdated=false;
        return character;
    }
}