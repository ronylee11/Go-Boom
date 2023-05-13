import java.util.*;
//this is a testing file that used to test hashmap but now there no use for it
public class test {
    public static void main(String[] args){ //for testing if the hashmap was working or not
        Deck decky = new Deck();
        ArrayList<Integer> listy = new ArrayList<>(); // \to store the random int to choose the card
        ArrayList<String> cunny = new ArrayList<>(); // to store the choosen card
        ArrayList<String> spilting = new ArrayList<>(); //to store the split values
        ArrayList<String> ranks = new ArrayList<>(); // to store the ranks
        Random rdy = new Random();
        for(int i=0; i <= 3; i++){ // to randomize the number
            listy.add(rdy.nextInt(51));
        }
        for(int j=0; j < listy.size(); j++){ // to use the randomize number and get the card from the deck 
            cunny.add(decky.getdeck(listy.get(j)));
        }
        System.out.println(cunny);

        for(String card : cunny){ // to split the suite and rank
            String [] Arraysplit = card.split("");
            spilting.addAll(Arrays.asList(Arraysplit));
        }
        System.out.println(spilting);

        for (int i=0; i < spilting.size(); i++){
            if(i%2 != 0){
                ranks.add(spilting.get(i));
            }
        }

        String largest_card = cunny.get(0);
        int largest_value = decky.create_card_Values_HashMap().get(largest_card.substring(0,1));

        for(String cunnies : cunny){
            int cardValue = decky.create_card_Values_HashMap().get(cunnies.substring(0,1));

            if (cardValue > largest_value){
                largest_card = cunnies;
                largest_value = cardValue;
            }
        }
        System.out.println("Largest Card : " + largest_card);

        // ArrayList<Integer> ranking_compare = new ArrayList<>(); // this part is to seperate the rank from the suite
        // ArrayList<String> suite_compare = new ArrayList<>();
        // System.out.println(ranks);
        // for(HashMap.Entry<String, Integer> entry : decky.create_card_Values_HashMap().entrySet()){
        //     if(ranks.contains(entry.getKey())){
        //         ranking_compare.add(entry.getValue());
        //         suite_compare.add(entry.getKey());
        //         System.out.println(entry.getKey() + "  :  "+ entry.getValue());
                
        //     }
        // }
        // Collections.sort(suite_compare);
        // Collections.sort(ranking_compare);
        // System.out.println(suite_compare);
        // System.out.println(ranking_compare);

    }
}
