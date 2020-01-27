package model;


public class CharacterSignatures {
    private Integer characterId;
    private String race;
    private String name;

    public CharacterSignatures(String race, String name){
        this.race = new String();
        this.name= new String();
    }

    public String getRace(){
        return race;
    }
    public Integer getCharacterId() {
        return characterId;
    }
    public String getName(){
        return name;
    }

    public void setRace(String race) {
        this.race = race;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }
}
