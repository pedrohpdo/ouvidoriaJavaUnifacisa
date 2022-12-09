package feedback;

public class Category extends Feedback {
    private String type;

    public Category(String description, String author, String type){ 
        super(description, author);
        this.type = type;
    }

    @Override
    public String toString(){
        return String.format("Type: %s | %s", getType(), super.toString());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
