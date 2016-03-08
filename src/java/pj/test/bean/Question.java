package pj.test.bean;

public class Question extends Common{

    private String title;
    private String content;
    private int categoryTypeId;//问题分类ID
    private int money;//悬赏币
    private int questionerId;//提问者ID
    private String time;//提问时间
    private int teacherId=-1;//指定老师(-1代表不指定)
    private int gradeId;//对应年级ID
    private int replyCount;//回复次数

    public Question(){
        java.text.SimpleDateFormat fmt=new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        time=fmt.format(new java.util.Date(System.currentTimeMillis()));
    }


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }


    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Object time) {
        if(time==null)this.time="";
        this.time =Common.format(String.valueOf(time));
    }

    /**
     * @return the categoryTypeId
     */
    public int getCategoryTypeId() {
        return categoryTypeId;
    }

    /**
     * @param categoryTypeId the categoryTypeId to set
     */
    public void setCategoryTypeId(int categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    /**
     * @return the questionerId
     */
    public int getQuestionerId() {
        return questionerId;
    }

    /**
     * @param questionerId the questionerId to set
     */
    public void setQuestionerId(int questionerId) {
        this.questionerId = questionerId;
    }

    /**
     * @return the teacherId
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return the gradeId
     */
    public int getGradeId() {
        return gradeId;
    }

    /**
     * @param gradeId the gradeId to set
     */
    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * @return the replyCount
     */
    public int getReplyCount() {
        return replyCount;
    }

    /**
     * @param replyCount the replyCount to set
     */
    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

 }
