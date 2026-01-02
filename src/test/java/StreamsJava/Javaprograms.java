package StreamsJava;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Javaprograms {

    //count the numbers of the names starting with alphabate in k in the list

    // @Test
    public void regular(){
        ArrayList<String> names=new ArrayList<>();
        names.add("krishna");
        names.add("karna");
        names.add("arjuna");
        names.add("beema");
        names.add("krupacharya");
        int count=0;

        for(int i=0;i<names.size();i++){


            String actual = names.get(i);
            if(actual.startsWith("k")){

                count++;

            }
        }
        System.out.println(count);

    }

    @Test
    public void regular2(){
        ArrayList<String> names=new ArrayList<>();
        names.add("krishna");
        names.add("karna");
        names.add("arjuna");
        names.add("beema");
        names.add("krupacharya");

        Long countOfNames=names.stream().filter(s->s.startsWith("k")).count();
        System.out.println(countOfNames);

        Long c= Stream.of("krishna","karna", "arjuna", "beema","krupacharya").filter(s->{

            s.startsWith("k");
            return true;
        }).count();
        System.out.println(c);

        //print all the names of arrayList
        names.stream().filter(s->s.length()>3).forEach(s->System.out.println(s));

        names.stream().filter(s->s.length()>3).limit(1).forEach(s->System.out.println(s));

    }

    @Test
    public void streamMap(){

        // print names which have  letters "a" convert to upperscase and print
        Stream.of("krish","karna", "arjun", "beem","krupacharya").filter(s->s.endsWith("a")).map(s->s.toUpperCase())
                .forEach(s->System.out.println(s));

        //print names which have  letters "a" with uppercase and sorted

        List<String> names= Arrays.asList("krish", "karna", "arjun", "beem", "krupacharya");
        names.stream().filter(s->s.startsWith("a")).sorted().map(s->s.toUpperCase()).forEach(s->System.out.println(s));
    }

    @Test
    public void streamerge(){

        //merger2 different lists
        //1st list
        ArrayList<String> names=new ArrayList<>();
        names.add("krishna");
        names.add("karna");
        names.add("arjuna");
        names.add("beema");
        names.add("krupacharya");

       // 2nd list
        List<String> names1= Arrays.asList("krish", "karna", "arjun", "beem", "krupacharya");
        names1.stream().filter(s->s.startsWith("a")).sorted().map(s->s.toUpperCase()).forEach(s->System.out.println(s));
        Stream<String> namesStream= Stream.concat(names1.stream(),names1.stream());
       boolean war= namesStream.anyMatch(s->s.equalsIgnoreCase("arjun"));
        System.out.println(war);
        Assert.assertTrue(war);






    }

    @Test
    public void streacollect(){

      List<String> ls= Stream.of("krish","karna", "arjun", "beem","krupacharya").filter(s->s.endsWith("a")).map(s->s.toUpperCase())
              .collect(Collectors.toList());
      System.out.println(ls.get(0));



      List<Integer> value=Arrays.asList(5,2,1,4,3,10,6,5,5,9);
        //print unique numbers from the thi array
        //sort the array -3rd index  like 1,2,3,4,5,9,10
        //value.stream().forEach(s->System.out.println(s));
        List<Integer> li=value.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println(li.get(2));

    }
}

