import support.LLNode;

public class TestLLMR{
   public static void main(String[] args){
      //populate test LL
      LinkedListMR<String> linkedTest = new LinkedListMR<String>();
      
      linkedTest.add("Head"); 
      linkedTest.add("2nd Node");
      linkedTest.add("3rd Node");
      linkedTest.add("4th Node");
      linkedTest.add("Middle Node");
      linkedTest.add("6th Node");
      linkedTest.add("7th Node");
      linkedTest.add("8th Node");
      linkedTest.add("Tail Node");
      
      System.out.println(linkedTest);

      System.out.println(linkedTest.findMiddle());
      
      linkedTest.reverseStack();
      System.out.println("Reversed with a stack: ");
      System.out.println(linkedTest);
      
      linkedTest.reverseInPlace();
      System.out.println("Reversed in place: ");
      System.out.println(linkedTest);
      

   }
}
