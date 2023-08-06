import java.util.HashSet;
class Teacher{
    private int name;
    //how many courses can this teacher teach alltogether, maybe 3
    private int limit;
    //what are the times of the day not preferred// a hashset of non desired times
    //what days preferred? // mwf or t, th
    private int dayp;

    // how long of a break between classes//e.g if we want to add  10, we must check if they are already teaching a course at 9 or at 11
    private int  breakp;
    // what course tags does he have, that he can teach, maybe this should be an array or set
    
    private HashSet<Character>id;
    private int time_b;
    private int time_a;

    //private set containing the tags the teacher has
    //what are the teacher's name, timeb, dayp,breakp, profeciencies
    public Teacher(int name , int  dayp, int time_b,int time_a, HashSet<Character> id, int limit, int breakp) {
        this.limit=limit;
        this.time_b=time_b;
        this.time_a=time_a;
        this.dayp=dayp;
        this.name=name;
        this.id=id;
        this.limit=limit;
        this.breakp=breakp;

    }
    public int get_breakp(){
        return breakp;
    }
    public int get_dayp(){
        return dayp;
    }
    public int get_timeb(){
        return time_b;
    }
    public int get_time_a(){
        return time_a;
    }

    public HashSet<Character> get_id(){
        return id;
    }
    
    public int get_name(){
        return name;
    }
    public int get_limit(){
        return limit;
    }
    
}