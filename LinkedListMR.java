import ch02.stacks.LinkedStack;
import ch02.stacks.StackUnderflowException;

import support.LLNode;

public class LinkedListMR<T>{
   
   LLNode<T> head; //head of linked list
   LLNode<T> currNode; //node used for iteration and track current node
   LLNode<T> top; //top of linked list stack 
   
   LLNode<T> prevNode; //node keeps track of previous
   LLNode<T> nextNode; //node keeps track of next
   
   LinkedStack<T> lStack; //linked stack for performing operations 
   T temp; //store info from elements temporarily or perform operations
   
   //constructor
   public LinkedListMR(){
      head = null;
   }
   
   //isEmpty - nice to have
   public boolean isEmpty(){              
    return (head == null); 
   }
   
   //general add to the end of the list 
   public void add(T element){
      LLNode<T> newNode = new LLNode<T>(element);
      currNode = head;
      
      //if adding to an empty list 
      if(isEmpty()){
         newNode.setLink(head);
         head = newNode; 
      //get to end if necessary
      }else{
         while(currNode.getLink() != null){
            currNode = currNode.getLink();
         }
         currNode.setLink(newNode);
      }
      
   }
   
   //toString
   public String toString(){
      StringBuilder stringb = new StringBuilder(""); 
      if(isEmpty()){
         stringb.append("LinkedList is empty!");
      }else{
         stringb.append("Linked List: ");
         currNode = head;
         while(currNode.getLink() != null){
            stringb.append(currNode.getInfo() + ", ");
            currNode = currNode.getLink();
         }
         if(currNode.getLink() == null){
            stringb.append(currNode.getInfo());
         }  
      }
         
      String print = stringb.toString(); 
      return print;
   }
   
   //finding the middle of a Linked List
   public T findMiddle(){
      //if list is empty
      if(head == null){
         System.out.println("Linked List is empty!");
         return null;
      }
      
      //assign pointers
      LLNode<T> slowPointNode = head;
      LLNode<T> fastPointNode = head;
      
      //iterate
      while(fastPointNode != null && fastPointNode.getLink() != null){
         //move slow pointer 1 step
         slowPointNode = slowPointNode.getLink();
         
         //move fast pointer 2 steps
         fastPointNode = fastPointNode.getLink().getLink();
      }
      
      //return middle
      return slowPointNode.getInfo();
   }

   //reversing the linked list 
   //with a stack - taking the *data* rather than the actual node
   public void reverseStack(){
      //check if empty
      if(head == null){
         throw new StackUnderflowException("Linked List is empty.");
      }
      
      //create new stack
      lStack = new LinkedStack<T>();
      currNode = head;
      //push entire LL onto stack 
      //first pass O(N) time
      while(currNode != null){ 
         lStack.push(currNode.getInfo());
         currNode = currNode.getLink();
      }
      
      //make last node/tail the new head
      head = currNode;
      
      //pop entire LL stack and remake LL 
      //O(N) stack space
      //second pass O(N) time
      while(!lStack.isEmpty()){
         temp = lStack.top();
         add(temp);
         lStack.pop();
      }
   }
   
   //reversing the linked list 
   //"in place"
   public void reverseInPlace(){
      //set values
      currNode = head;
      prevNode = null; 
      
      //O(N) time
      //iterate through, reassigning links
      while(currNode != null){
         //assign value to next 
         nextNode = currNode.getLink();
         
         //O(1) space
         //set the pointer of the current node to the previous node
         currNode.setLink(prevNode); 

         //move forward
         prevNode = currNode;
         currNode = nextNode;      
      }
      
      //update the head
      head = prevNode;
      
   }
}
