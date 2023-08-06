import java.util.HashSet;
import java.util.ArrayList;
public class Course {
    private int name;
    // what is the course type, use this to determine teachers
    private char tag;
    //what type of room do we need- 0 for lecture, 1 for lab1, 2 for lab2
    private int room_t;
    //is this course a requirement for any year freshman/sophmore
    private int year;
    private boolean req;
    //private Teacher[] profs=new Teacher[4];
    ArrayList<Teacher> profs=new ArrayList<>();

   

    public Course(int name, char tag, int room_t,int year, boolean req,int freq){
        this.name=name;
        this.tag=tag;
        this.room_t=room_t;
        this.req=req;
        this.year=year;

        
        }
    
    public boolean is_req(){
        return req;
    }
    public int get_name(){
        return name;
    }
    public char get_tag(){
        return tag;
    }
    public ArrayList<Teacher> get_profs(){
        return profs;
    }
    public int get_year(){
        return year;
    }
    public int get_room_t(){
        return room_t;
    }
    
        
    public void add_prof(Teacher prof){
        profs.add(prof);
        //System.out.println("prof added");
    }
    
    
}
