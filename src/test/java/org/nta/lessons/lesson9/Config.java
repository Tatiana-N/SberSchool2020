package org.nta.lessons.lesson9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {
   List<String> list = new ArrayList<>();
   public void readNames(){
     try( BufferedReader br = new BufferedReader(new FileReader("src/test/resources/names.txt"))){
       while (br.ready()){
         list.add(br.readLine());
       }
     } catch (FileNotFoundException exception) {
       exception.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
@Bean
@Scope("prototype")
  public Person person(){
     readNames();
     int age = (int)(Math.random()*85);
     int num = (int)(Math.random()* list.size());
  return new Person( age, list.get(num));
}


}
