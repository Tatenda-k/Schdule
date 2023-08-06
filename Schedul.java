import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Schedul {
    public static void main(String [] args){
        
    Teacher [] profs=new Teacher[3];
    int name=0;  // the prof's name's position when arranged alphabetically
    int days=0;  // what days would they not want to teach on- zero stands for MWF, 1 for T,TH
    int time_b=9; // what time would they not want to teach before
    int time_a=0; //what time would they not want to teach after
    HashSet<Character>id=new HashSet<>(Set.of('b','c')); //the trs can teach the courses with these tags
    int limit=2; // what is the limit of how many classes they can be scheduled for
    int breakp=30; // how long is the break they would like between classes
    profs[0]=new Teacher(name, days,time_b,time_a,id,limit,breakp);
    name=1;
    days=1;
    time_b=0;
    time_a=13;
    id=new HashSet<>(Set.of('a','c'));
    limit=2;
    breakp=20;
    profs[1]=new Teacher(name, days,time_b,time_a,id,limit,breakp);
    name=2;
    days=0;
    time_b=9;
    time_a=0;
    id=new HashSet<>(Set.of('c','b'));
    limit=2;
    breakp=10;
    profs[2]=new Teacher(name, days,time_b,time_a,id,limit,breakp);
     
    int f=0;
    Course [] courses=new Course[6];
    //there are two types of rooms e,g those with computers, and those without. 
    //req- is a course required?
    courses[0]=new Course(200,'a',12,-1,true,2);
    courses[1]= new Course(400,'b',11,-2,true,2);
    courses[2]=new Course(501,'c',11,-1,true,2);
    courses[3]=new Course(450,'c',12,-3,true,3);
    courses[4]=new Course(575,'b',11,-3,true,3);
    courses[5]=new Course(505,'c',12,-3,true,3);
    //courses[6]=new Course(560,'c',12,-3,true,3);
    // courses[7]=new Course(560,'c',1,-3,true,3);
    // courses[8]=new Course(560,'c',1,-3,true,3);
    // courses[9]=new Course(560,'c',1,-3,true,3);
    // courses[10]=new Course(560,'c',1,-3,true,3);
    // courses[11]=new Course(560,'c',1,-3,true,3);

    //instead of exploring options for each room, we can simply keep track of how many of a certain room type are full
    int rm_as=3;
    int rm_bs=3;
    //determine which teachers can teach a course
        for(int i=0;i<profs.length;i++){ 
        //if the prof has this tag, the course can be taught by this prof
        for(int j=0;j<courses.length;j++){           
            if(profs[i].get_id().contains(courses[j].get_tag())){
                courses[j].add_prof(profs[i]);
            }
        }
        }

        ArrayList<ArrayList<HashSet<Integer>>> schedule = new ArrayList<>();
        schedule.add(new ArrayList<>());
        //add some courses from other departments that students must take to reduce the number of available options
        schedule.get(0).add(new HashSet<>(Set.of(221,-2,120)));
        schedule.get(0).add(new HashSet<>(Set.of(510,-3,101)));
        schedule.get(0).add(new HashSet<>(Set.of(241,-1,110)));
        schedule.get(0).add(new HashSet<>(Set.of(415,-4,120)));
        schedule.get(0).add(new HashSet<>(Set.of(516,-2,121)));
        schedule.get(0).add(new HashSet<>(Set.of(511,-3,130)));
        schedule.get(0).add(new HashSet<>(Set.of(340,-3,121)));
        schedule.get(0).add(new HashSet<>(Set.of(512,-3,131)));
        //schedule.get(0).add(new HashSet<>(Set.of(410,-4,131)));
        // schedule.get(0).add(new HashSet<>(Set.of(344,-3,110)));
        // schedule.get(0).add(new HashSet<>(Set.of(416,-1,-130)));
        ArrayList<ArrayList<HashSet<Integer>>> n_schedule = new ArrayList<>();

        //class,prof,room,year class>200,prof,room_t,year
        // courses- 200-600,time-70-161,room-0-1,year,-2to-5,profs 2 to 20
        //add first combo of 200
                
       

        // ArrayList<Integer> dummy =new ArrayList<>(profs.length);
        // tr_freq.add(dummy);
        //tr_busy is an array where each index represents a fringe, for each fringe, there is a group of sets, where each set index
        //is a tr's name, the set contains the times that the teacher is teaching
        //ArrayList<HashSet<Integer>>tr_busy=new ArrayList<>();
        //rm_a is an array of hashmaps where each index is a state, and the key is the time, value is the freq
                
        int breaks=0;
        int cur=0;
        int intv=0;
        int max_s= courses.length*6;
        
        //tr_freq
        // tr_freq is an array where each index represents a state, and for each state the index is the tr's name
        // the value at the index tells us how many classes the prof is currently teaching.
        ArrayList<ArrayList<Integer>> tr_freq=new ArrayList<>();
        for(int m=0;m<profs.length;m++){
            tr_freq.add(new ArrayList<>());
        }
        //n_freq will hold the updated tr_freq
        ArrayList<ArrayList<Integer>> n_freq= new ArrayList<>();

        //tr_sc is an array where each index represents a state , the hashset at index 2 tells us the times that that tr 2 is teaching at 
        ArrayList <ArrayList<HashSet<Integer>>> ntr_sc=new ArrayList<>();
       ArrayList <ArrayList<HashSet<Integer>>> tr_sc=new ArrayList<>();
       tr_sc.add(new ArrayList<>());
       ArrayList<HashSet<Integer>> tem =tr_sc.get(0);
       for( int m=0;m<profs.length;m++){
       tem.add(new HashSet<>());
       }
    
       //rm_a and rm_b: the index of the outer array represents the state we are in, the hashmap tells us, for each time recorded in it,
       // how many of the rooms of that type are currently in use
       ArrayList<ArrayList<HashMap<Integer,Integer>>> rm_a= new ArrayList<>();
        rm_a.add(new ArrayList<>());
        rm_a.get(0).add(new HashMap<>());
       ArrayList<ArrayList<HashMap<Integer,Integer>>>nrm_a= new ArrayList<>();
       ArrayList<ArrayList<HashMap<Integer,Integer>>> rm_b= new ArrayList<>();
       //nrm: the array for the next stage
        rm_b.add(new ArrayList<>());
        rm_b.get(0).add(new HashMap<>());
       ArrayList<ArrayList<HashMap<Integer,Integer>>>nrm_b= new ArrayList<>();
       
        //scores: the index of the outer array represents the state we are in, in the inner array, the first index is the score, 
        //and the seconde index is the scores original position before the sorting, to help us map each score to its corresponding state
        ArrayList<ArrayList<Integer>> scores=new ArrayList<>();
        scores.add(new ArrayList<>(Arrays.asList(0,0)));
        // n_scores : the scores for the next level
        ArrayList<ArrayList<Integer>>n_scores=new ArrayList<>();

        int time=0;
        boolean valid = true;
       int val=0;
       int s=0;
       int h;
       int rm=0;
       int rmt=0;
       Course visit=null;
      // loop:
        //for all the courses we want to schedule
        for(int i=0;i<6;i++){
                    //System.out.println(schedule.size());
            //clear up the arrays of the previous level
            n_scores.clear();
            nrm_a.clear();
            nrm_b.clear();
            n_schedule.clear();
            n_freq.clear();
            ntr_sc.clear();
            f=0;
             visit=courses[i];
            //sort the scores     
            h=0;
        // we have two options for days, 0 is MWF, 1 is T,TH
        for(int d=0;d<scores.size();d++){
            //we want to visit the schedule corresponding with the score
            s=scores.get(d).get(1);
        
        //for every single profesor who can teach this course
        for(int a =0;a<visit.get_profs().size();a++){ 
            Teacher prof=visit.get_profs().get(a);
            name=visit.get_profs().get(a).get_name();
            //if the professor is still available to teach other courses
            //each professor has their own index, which is their name, it tells us how many classes they are signed up for
        //to ensure we don't get out of bound exception on first round
            while (tr_freq.get(s).size()<=profs.length){
            tr_freq.get(s).add(0);
        }
        //if the prof is already teaching enough classes
           if(tr_freq.get(s).get(name)==profs[a].get_limit()){
            
            continue;
           }
           
            //for all possible times
            for(int b=10;b<14;b++){
                time=b*10;
                
                //for all possible day combinations
                for(int r =0;r<2;r++){
                    if(r==1){
                        //adding one will help us differentiate betweeen MWF-70, and T,TH-71
                        time+=1;
                    }
                  
                // if the tr is busy at this time               
              if(tr_sc.get(s).get(name).contains(time)){
                
                    continue;
              }
              //if the room is occupied 
                HashMap<Integer, Integer> hashMap = new HashMap<>();
                //if the room is already occupied to the maximum
                 rmt=visit.get_room_t();
                    if(rmt==11){
                     hashMap = rm_a.get(s).get(0);
                     rm=rm_as;
                    }
                    else{
                 hashMap = rm_b.get(s).get(0);
                  rm=rm_bs;
                    }
                    
                if(hashMap.containsKey(time) && hashMap.get(time)==rm){
                    continue;
                }

            //does this course occur at the same time as another course in the same year that is requires
            for (HashSet<Integer> crs : schedule.get(s)) {
            if (crs.contains(time) && crs.contains(visit.get_year())&& visit.is_req()==true){
            valid=false;
            }
        }
    
            //create a new schedule to update
            if(valid){
                //create a copy of the current rm_a and rm_b and update
                ArrayList<HashMap<Integer,Integer>> copidlist=new ArrayList<>();
               
                for(HashMap<Integer,Integer> hashmap: rm_a.get(s)){
                    HashMap<Integer, Integer> copiedmap= new HashMap<>(hashmap);
                    copidlist.add(copiedmap);
                }
                
                 ArrayList<HashMap<Integer,Integer>> copidlist2=new ArrayList<>();
                for(HashMap<Integer,Integer> hashmap2: rm_b.get(s)){
                    HashMap<Integer, Integer> copiedmap2= new HashMap<>(hashmap2);
                    copidlist2.add(copiedmap2);
                }
                
            if(rmt==11){
                val=copidlist.get(0).getOrDefault(time,0)+1;
                copidlist.get(0).put(time,val);
                nrm_a.add(copidlist);
                nrm_b.add(copidlist2);
            }
            else{

                val=copidlist2.get(0).getOrDefault(time,0)+1;
                copidlist2.get(0).put(time,val);
                nrm_b.add(copidlist2);
                nrm_a.add(copidlist);
            }
        
            
            //create a copy of the current schedule and update it 
            ArrayList<HashSet<Integer>> copiedInner = new ArrayList<>();
            for(HashSet<Integer> hashset: schedule.get(s)){
                HashSet<Integer> copied= new HashSet<>(hashset);
                copiedInner.add(copied);
                }
                // we want to store copied, and keep on referencing it. but we don't want to create a
                copiedInner.add(new HashSet<>(Set.of(time,visit.get_year(),visit.get_name(),visit.get_room_t(),name)));
                n_schedule.add(copiedInner);   
            
                
            
            //create a copy of the current tr_sc and update the copy
            ArrayList<HashSet<Integer>> copedInner= new ArrayList<>();
            ArrayList<HashSet<Integer>> orignalInner= tr_sc.get(s);
            
            for(HashSet<Integer> originalSet: orignalInner){
                HashSet<Integer> copiedSet= new HashSet<>(originalSet);
                copedInner.add(copiedSet);
                }
                 
                copedInner.get(name).add(time);                
                ntr_sc.add(copedInner);  

            //create a copy of the curr tr_freq and update it
            ArrayList<Integer> copiedInnr= new ArrayList<>(tr_freq.get(s));
           
            while (copiedInnr.size()<=profs.length){
                copiedInnr.add(0);
            }

            val=copiedInnr.get(name);

            copiedInnr.set(name,val+1);

            n_freq.add(copiedInnr); 
           
        
            // calculate score
            //get the time after preference of this teacher, then go to state.tr_sc and see if curr time is> than pref*10 +1
            //if this time is less than the professores after preferences  +1 for tuesday, and thursday

           //update the utility score of the current schedule
            cur=scores.get(d).get(0);
            if(1+ (prof.get_time_a())*10 >time){
                cur+=1;
            }
            if(1+ (prof.get_timeb())*10 <time){
                cur+=1;
            }
            // //day pref is even or odd
            if(time %2!=prof.get_dayp()){
                cur+=3;
            }
             breaks=prof.get_breakp();

             intv=time+breaks;
            if(tr_sc.get(s).get(name).contains(intv)){
                cur+=1;
            }
            intv=time-breaks;
            if( tr_sc.get(s).get(name).contains(intv)){
                cur+=1;
            }
            //add the updated score to the array of new scores
            n_scores.add(new ArrayList<>(Arrays.asList(cur, h)));

            if(cur==max_s){
                System.out.println(cur);
                System.out.println("top schedule" + schedule.get(s));
                
                //break loop;
             }
            h+=1;
            }
    
    
    valid=true;
           }      
    }
               
        }
}
        
        //clear the old scores,and add the new ones to the array
        scores.clear();
        ArrayList<ArrayList<Integer>> temp= new ArrayList<>(n_scores);
        for (ArrayList<Integer> m: temp){
            scores.add(m);
        }

       
        //sort the scores array to reflect the most viable schedules ordered in descending order
         Collections.sort(scores, Collections.reverseOrder(new Comparator<ArrayList<Integer>>() {
                @Override
                public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
                    return list1.get(0).compareTo(list2.get(0));
                }
                
            }));

        //clear the old tr_freq ,and add the new tr_freq  to the array
        tr_freq.clear();

        for(ArrayList<Integer> innerList: n_freq){
            ArrayList<Integer> copied=new ArrayList<>(innerList);
            tr_freq.add(copied);   
        }

        //clear the old tr_sc ,and add the new tr_sc  to the array
        tr_sc.clear();
        for(ArrayList<HashSet<Integer>> originer: ntr_sc){
                ArrayList<HashSet<Integer>> copiednner=new ArrayList<>();
            for(HashSet<Integer> origSet: originer){
                HashSet<Integer> copiedSet5=new HashSet<>(origSet);
                copiednner.add(copiedSet5);
            }
            tr_sc.add(copiednner);

        }
       
        //clear the old schedules ,and add the new ones  to the array
            schedule.clear();
            
                for(ArrayList<HashSet<Integer>> originner: n_schedule){
                    ArrayList<HashSet<Integer>> copiedinner=new ArrayList<>();
                for(HashSet<Integer> origSet: originner){
                    HashSet<Integer> copiedSet5=new HashSet<>(origSet);
                    copiedinner.add(copiedSet5);
                }
                schedule.add(copiedinner);
                

            }
        
          
        
            //clear the old rooms ,and add the new rooms  to the array
             rm_a.clear();
            //after this we want to copy all of rm_a into hash
           
            for(ArrayList<HashMap<Integer,Integer>> innerL: nrm_a){
                ArrayList<HashMap<Integer,Integer>> copiedInn = new ArrayList<>();
                for(HashMap<Integer,Integer> hashhmap : innerL){
                    HashMap<Integer, Integer> copiedHash=new HashMap<>(hashhmap);
                    copiedInn.add(copiedHash);
                }
            rm_a.add(copiedInn);
                
            }
            rm_b.clear();

            for(ArrayList<HashMap<Integer,Integer>> innerLi: nrm_b){
                ArrayList<HashMap<Integer,Integer>> copedInn = new ArrayList<>();
                for(HashMap<Integer,Integer> hashhmap : innerLi){
                    HashMap<Integer, Integer> copiedHsh=new HashMap<>(hashhmap);
                    copedInn.add(copiedHsh);
                }
                rm_b.add(copedInn);
                }
        
 
            }
         // print the top 5 generated schedules                 
        for (int p = 0; p <5 ; p++) {
            s=scores.get(p).get(1);


            //get the ordered scores and access their indexes
            System.out.println(n_schedule.get(s));
        }
       
    }
}

