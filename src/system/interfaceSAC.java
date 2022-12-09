package system;

public interface interfaceSAC {
    void setFeedback(String text, String type);

    String listFeedbacks(String type);

    boolean removeFeedback(String type, int id);

    boolean updateFeedback(String type, int id, String text);

    void deleteAllfeedbacks();
}
