package cat.dam.ivan.itv;

public class Cotxe
{
    private int id;
    private String matricula;
    private String model;
    private String color;
    private String anyItv;

    public Cotxe(int id, String matricula, String model, String color, String anyItv)
    {
        this.id = id;
        this.matricula = matricula;
        this.model = model;
        this.color = color;
        this.anyItv = anyItv;
    }

    public Cotxe(String matricula, String model, String color, String anyItv)
    {
        this.matricula = matricula;
        this.model = model;
        this.color = color;
        this.anyItv = anyItv;
    }

    public int getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAnyItv() {
        return anyItv;
    }

    public void setAnyItv(String anyItv) {
        this.anyItv = anyItv;
    }
}
