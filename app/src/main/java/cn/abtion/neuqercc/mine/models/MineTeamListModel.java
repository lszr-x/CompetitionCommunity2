package cn.abtion.neuqercc.mine.models;

/**
 * @author fhyPayaso
 * @since 2017/11/21 13:28
 * email fhyPayaso@qq.com
 */

public class MineTeamListModel {

    private String title;
    private String number;
    private String position;
    private String species;

    public MineTeamListModel(String title, String number, String position, String species) {
        this.title = title;
        this.number = number;
        this.position = position;
        this.species = species;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

}
